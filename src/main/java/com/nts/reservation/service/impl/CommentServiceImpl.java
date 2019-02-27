package com.nts.reservation.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dao.CommentMapper;
import com.nts.reservation.dao.FileInfoMapper;
import com.nts.reservation.dto.CommentDisplayItem;
import com.nts.reservation.dto.CommentPageInfo;
import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.dto.ReservationUserComment;
import com.nts.reservation.dto.ReservationUserCommentImage;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.util.FileHandler;
import com.nts.reservation.util.Utils;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentDao;
	@Autowired
	private FileInfoMapper fileInfoDao;
	
	@Value("${spring.datasource.comment-image-save-directory}")
	private String commentImageSaveDirectory;
	
	@Override
	public List<CommentDisplayItem> getCommentsByProductIdWithPaging(long productId, int startRow, int limit) {
		return commentDao.selectFromTheProductWithPageing(productId, startRow, limit);
	}

	@Override
	public CommentPageInfo getCommentPageInfoByProductId(long productId) {
		return commentDao.selectCommentPageInfoByProductId(productId);
	}

	@Override
	@Transactional(readOnly = false)
	public ReservationUserComment addReservationUserComment(ReservationUserComment comment, MultipartFile image) {
		commentDao.insertReservationUserComment(comment);
		if (existsImage(image)) {
			FileInfo fileInfo = createFileInfo(comment.getReservationInfoId(), image);
			fileInfoDao.insertFileInfo(fileInfo);
			commentDao.insertReservationUserCommentImage(new ReservationUserCommentImage(comment, fileInfo));
			FileHandler.saveFile(image, fileInfo.getSaveFileName());
		}
		return comment;
	}

	private String getFileName(long reservationInfoId, String type) {
		return reservationInfoId + "_" + new Date().getTime() + "." + type.split("/")[1];
	}

	private boolean existsImage(MultipartFile image) {
		return !Utils.isEmpty(image.getOriginalFilename());
	}

	private FileInfo createFileInfo(long reservationInfoId, MultipartFile file) {
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		FileInfo fileInfo = new FileInfo();
		String path = commentImageSaveDirectory + today;
		
		if (!FileHandler.createDirectoryIfNotExist(path)) {
			throw new RuntimeException("Check Permission." + path);
		}
		fileInfo.setFileName(getFileName(reservationInfoId, file.getContentType()));
		fileInfo.setSaveFileName(path + "/" + fileInfo.getFileName());
		fileInfo.setContentType(file.getContentType());
		return fileInfo;
	}
}
