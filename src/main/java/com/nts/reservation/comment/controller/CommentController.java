/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.common.annotation.MustLogin;
import com.nts.reservation.common.exception.InternalServerErrorException;
import com.nts.reservation.common.exception.NotFoundDataException;
import com.nts.reservation.common.exception.UnauthenticateException;
import com.nts.reservation.file.service.FileService;
import com.nts.reservation.reservation.service.ReservationService;

@Controller
public class CommentController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private FileService fileService;

	@MustLogin
	@GetMapping(value = {"/my-reservation/comment/write"})
	public ModelAndView writeComment(ModelAndView mv, @RequestParam(required = true) int reservationId,
		HttpSession httpSession) {

		String email = (String)httpSession.getAttribute("email");
		try {
			String productDescription = reservationService.getReservedProductDescription(email, reservationId);
			mv.addObject("productDescription", productDescription);
		} catch (NotFoundDataException e) {
			throw new UnauthenticateException("not found data on your request");
		}
		mv.setViewName("writecomment");
		mv.addObject("reservationId", reservationId);
		return mv;
	}

	@GetMapping(value = {"/comment/img/{commentImageId}"})
	public void getCommentImage(@PathVariable int commentImageId, HttpServletResponse response) {
		String saveFileName = commentService.getCommentImageSaveFileName(commentImageId);

		try (FileInputStream fileInputStream = new FileInputStream(
			fileService.getFile(saveFileName));
			OutputStream outputStream = response.getOutputStream()) {

			byte[] buffer = new byte[1024];
			while (fileInputStream.read(buffer) > 0) {
				outputStream.write(buffer);
			}

		} catch (FileNotFoundException e) {
			throw new NotFoundDataException("file is not found");
		} catch (IOException e) {
			throw new InternalServerErrorException("file io fail!!");
		}

	}
}
