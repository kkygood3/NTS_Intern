/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.Reservation;
import com.nts.reservation.dto.ReservedItem;
import com.nts.reservation.service.CommentService;
import com.nts.reservation.service.ReservationService;

/**
* @author  : 이승수
*/
@RestController
@RequestMapping(path = "/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private CommentService commentService;

	@GetMapping
	public Map<String, Object> getReservations(@RequestParam String reservationEmail) {
		List<ReservedItem> reservedItems = reservationService.getReservedItems(reservationEmail);

		Map<String, Object> map = new HashMap<>();
		map.put("reservations", reservedItems);
		map.put("size", reservedItems.size());
		return map;
	}

	@PostMapping
	public boolean makeReservation(HttpSession session, @RequestBody Reservation reservationInfo) {
		session.setAttribute("userEmail", reservationInfo.getReservationEmail());

		reservationService.makeReservation(reservationInfo);

		return true;
	}

	@PutMapping(path = "/{reservaionInfoId}")
	public boolean cancelReservation(@PathVariable("reservaionInfoId") int reservationInfoId) {
		reservationService.cancelReservation(reservationInfoId);

		return true;
	}

	@PostMapping(path = "/{reservaionInfoId}/comments")
	public String writeComment(@ModelAttribute Comment comment,
		@RequestParam(name = "files", required = false) List<MultipartFile> files) throws IOException {
		commentService.addComment(comment, files);

		return "/reservation/history";
	}
}