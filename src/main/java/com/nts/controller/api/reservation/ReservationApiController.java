/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.controller.api.reservation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nts.dto.commentdto.Comment;
import com.nts.dto.commentdto.CommentImage;
import com.nts.dto.displayinfodto.DisplayInfo;
import com.nts.dto.productdto.ProductPrice;
import com.nts.dto.reservationdto.ReservationParam;
import com.nts.dto.reservationinfodto.ReservationDisplayInfo;
import com.nts.dto.reservationinfodto.ReservationInfo;
import com.nts.dto.reservationinfodto.ReservationInfoResponse;
import com.nts.exception.ExceptionValue;
import com.nts.exception.InvalidParameterException;
import com.nts.service.CommentService;
import com.nts.service.DisplayInfoService;
import com.nts.service.ProductService;
import com.nts.service.ReservationService;
import com.nts.util.Validator;

/**
 *
 * @description : Reservation API Controller
 * @package : com.nts.controller.api.reservation
 * @filename : ProductApiController.java
 * @author : 최석현
 * @method : ProductResponse products(int categoryId, int start)
 * 
 */
@RestController
@RequestMapping(path = "/api/reservations")
@PropertySource({"classpath:application.properties"})
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	@Autowired
	private DisplayInfoService displayInfoService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ProductService productService;

	@Value("${imagepath}")
	private String imagePath;

	private static final DateTimeFormatter YYYYMMDDHHMMSS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private static final int MAX_SESSION_INACTIVE_INTERVAL_30MINS = 1800;

	/**
	 * @description : Session에 있는 email을 이용해 ReservationInfo 가져온 후 return
	 */
	@GetMapping
	public ReservationInfoResponse reservationInfo(HttpSession session) {

		String reservationEmail = (String) session.getAttribute("reservationEmail");

		if (!Validator.emailValidation(reservationEmail)) {
			throw new InvalidParameterException("email", new ExceptionValue<String>(reservationEmail));
		}

		List<ReservationInfo> reservationInfos = reservationService
				.getReservationInfosByReservationEmail(reservationEmail);

		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		reservationInfoResponse.setReservations(reservationInfos);

		return reservationInfoResponse;
	}

	@GetMapping("/{displayInfoId}")
	public ReservationDisplayInfo reservationDisplayInfo(@PathVariable(name = "displayInfoId") int displayInfoId) {

		if (displayInfoId <= 0) {
			throw new InvalidParameterException("displayInfoId", new ExceptionValue<Integer>(displayInfoId));
		}

		DisplayInfo displayInfo = displayInfoService.getDisplayInfoByDisplayInfoId(displayInfoId);
		List<ProductPrice> reservationPrices = productService.getProductPricesByDisplayInfoId(displayInfoId);
		String productImageUrl = displayInfoService.getProductImageUrlByDisplayInfoId(displayInfoId);

		ReservationDisplayInfo reservationDisplayInfo = new ReservationDisplayInfo();
		reservationDisplayInfo.setDisplayInfo(displayInfo);
		reservationDisplayInfo.setReservationPrices(reservationPrices);
		reservationDisplayInfo.setProductImageUrl(productImageUrl);

		return reservationDisplayInfo;
	}

	/**
	 * @description : Session에 Email 정보가 없을 시 bookinglogin.js을 통해 접근됨
	 */
	@GetMapping("/login")
	public String login(@RequestParam(name = "reservationEmail") String reservationEmail, HttpSession session) {

		if (!Validator.emailValidation(reservationEmail)) {
			throw new InvalidParameterException("email", new ExceptionValue<String>(reservationEmail));
		}

		session.setAttribute("reservationEmail", reservationEmail);
		session.setMaxInactiveInterval(MAX_SESSION_INACTIVE_INTERVAL_30MINS);
		return reservationEmail;

	}

	@PostMapping("/{reservationInfoId}/comments")
	public void registerComment(
			@PathVariable(name = "reservationInfoId") int reservationInfoId,
			@RequestParam(name = "productId") int productId, @RequestParam(name = "comment") String comment,
			@RequestParam(name = "score") double score,
			@RequestParam(name = "reservationImage") MultipartFile reservationImage)
			throws FileNotFoundException, IOException {

		Comment commentObject = new Comment();
		commentObject.setReservationInfoId(reservationInfoId);
		commentObject.setProductId(productId);
		commentObject.setComment(comment);
		commentObject.setScore(score);

		int reservationUserCommentId = commentService.addComment(commentObject);

		if (!reservationImage.isEmpty()) {

			String fileName = reservationImage.getOriginalFilename();
			File file = new File(imagePath + "img/", fileName);
			IOUtils.copy(reservationImage.getInputStream(), new FileOutputStream(file));
			
			CommentImage commentImage = new CommentImage();
			commentImage.setReservationInfoId(reservationInfoId);
			commentImage.setReservationUserCommentId(reservationUserCommentId);
			commentImage.setFileName(fileName);
			commentImage.setProductImageUrl(imagePath + "img/" + fileName);
			commentImage.setContentType(reservationImage.getContentType());
			commentImage.setDeleteFlag(false);
			
		}

		
	}

	/**
	 * @description : JSON 형태의 RequestBody를 Insert
	 */
	@PostMapping("/reservation")
	public int reservation(@RequestBody ReservationParam reservationParam) {

		if (!Validator.reservationValidation(reservationParam.getReservation())) {
			throw new InvalidParameterException("email",
					new ExceptionValue<String>(reservationParam.getReservation().toString()));
		}
		
		reservationParam.getReservation().setReservationDate(LocalDateTime.now()
														.plusDays((long) (Math.random()*5)).format(YYYYMMDDHHMMSS));

		return reservationService.addReservation(reservationParam);
	}

	@PutMapping("/{reservationInfoId}")
	public void reservationCancel(@PathVariable(name = "reservationInfoId") int reservationInfoId) {

		if (reservationInfoId <= 0) {
			throw new InvalidParameterException("reservationInfoId", new ExceptionValue<Integer>(reservationInfoId));
		}

		reservationService.modifyCancelFlag(reservationInfoId, true);
	}
}