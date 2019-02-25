package com.nts.reservation.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.FileInfo;
import com.nts.reservation.dto.ReservationUserComment;
import com.nts.reservation.dto.ReservationUserCommentImage;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ProductService;
import com.nts.reservation.util.Utils;

@Controller
@RequestMapping("/reservation")
public class ReservationController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CommentService commentService;

	private static final String PROJECT_ROOT = "C:/Users/USER/git/pjt05";
	private static final String DIRECTORY = "/src/main/webapp/img/comment";
	/**
	 * 예약 확인페이지 url 맵핑
	 * 
	 * @return
	 */
	@GetMapping
	public String getReservation() {
		return "myreservation";
	}

	/**
	 * 리뷰작성 페이지에 필요한 정보 담아서 url 맵핑한다
	 * 
	 * @param productId 리뷰쓸 상품
	 * @param model     표시할 정보
	 * @return 뷰이름 리턴
	 */
	@GetMapping("/{reservationInfoId}/comment")
	public String getComment(ModelMap model,
			@PathVariable(name = "reservationInfoId", required = true) long reservationInfoId) {
		model.addAttribute("productInfo", productService.getProductInfoByReservationInfoId(reservationInfoId));
		return "reviewWrite";
	}

	/**
	 * 리뷰 등록한다
	 * 
	 * @return
	 */
	@PostMapping("/{reservationInfoId}/comment")
	public String postComment(@PathVariable(name = "productId", required = true) long reservationInfoId,
			@RequestParam("content") String comment,
			@RequestParam("score") double score,
			@RequestParam("file") MultipartFile image) {
		ReservationUserComment reservationUserComment = new ReservationUserComment(reservationInfoId, comment, score);
		FileInfo fileInfo = new FileInfo();
		
		if (Utils.isNull(image)) {
			fileInfo.setFileName(getFileName(reservationInfoId, image.getContentType()));
			fileInfo.setSaveFileName(getDirectory() + "/" + fileInfo.getFileName());
		}

		reservationUserComment = commentService.addReservationUserComment(reservationUserComment, fileInfo);
		
		if (existsFilesToSave(image, reservationUserComment)) {
			saveFile(image, fileInfo);
		}
//		return "redirect:detail/{displayInfoId}/review";
		return "redirect:/";
	}
	
	private boolean existsFilesToSave(MultipartFile image, ReservationUserComment reservationUserComment) {
		return !Utils.isNull(image) & (reservationUserComment.getId() > 0);
	}
	private void saveFile(MultipartFile image, FileInfo fileInfo) {
		try {
			image.transferTo(new File(fileInfo.getSaveFileName()));
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private String getDirectory() {
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String path = PROJECT_ROOT + DIRECTORY + "/" + today;
		File dir = new File(path);
		dir.isDirectory();
		dir.mkdir();
		return path;
	}
	
	private String getFileName(long reservationInfoId, String type) {
		return reservationInfoId + "_" + new Date().getTime() + "." + type.split("/")[1];
	}
}
