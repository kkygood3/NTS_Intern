/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.api;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;
import static com.nts.reservation.dto.request.regex.RegexPattern.IMAGE_CONTENT_TYPE;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.nts.reservation.dto.FileDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationRequestDto;
import com.nts.reservation.dto.request.ReservationUserCommentRequestDto;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.exception.InvalidParamException;
import com.nts.reservation.service.FileIoService;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */

@RestController
@RequestMapping(path = "/api/reservations")
@PropertySource("classpath:application.properties")
public class ReservationApiController {

	private final ReservationService reservationService;

	private final FileIoService fileIoService;

	@Value("${imageDefaultPath}")
	private String imageDefaultPath;

	private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

	private final Pattern imageContentTypePattern = Pattern.compile(IMAGE_CONTENT_TYPE);

	@Autowired
	public ReservationApiController(ReservationService reservationService, FileIoService fileIoService) {
		this.reservationService = reservationService;
		this.fileIoService = fileIoService;
	}

	/**
	 * @desc 예약 추가.
	 * @param requestParams
	 * @param bindingResult
	 * @param uriBuilder
	 * @return
	 * @throws BindException
	 */
	@PostMapping(produces = "application/json")
	public ResponseEntity<Map<String, String>> postReservation(@Valid @RequestBody ReservationRequestDto requestParams,
		BindingResult bindingResult, UriComponentsBuilder uriBuilder)
		throws BindException {

		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}

		URI redirectUri = uriBuilder.path("/")
			.build()
			.toUri();

		reservationService.addReservation(requestParams);
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("redirectUri", redirectUri.toString()),
			HttpStatus.OK);
	}

	/**
	 * @desc 이메일에 해당하는 예약 정보 조회
	 * @param reservationEmail
	 * @return
	 * @throws InValidPatternException
	 */
	@GetMapping
	public MyReservationResponseDto getReservationResponse(
		@RequestParam String reservationEmail) throws InvalidParamException {

		if (!emailPattern.matcher(reservationEmail).find()) {
			throw new InvalidParamException("reservaionEmail", reservationEmail);
		}

		List<ReservationInfoDto> list = reservationService.getReservationList(reservationEmail);
		int count = reservationService.getReservationCount(reservationEmail);
		return new MyReservationResponseDto(list, count);
	}

	/**
	 * @desc 예약 취소.
	 * @param reservationId
	 * @return
	 */
	@PutMapping(path = "/{reservationId}")
	public Long putCancelReservation(@PathVariable Long reservationId) {

		reservationService.cancelReservation(reservationId);
		return reservationId;
	}

	/**
	 * @desc 전시물에 대한 댓글 추가. 
	 * @param reservationInfoId
	 * @param requestDto
	 * @param bindingResult
	 * @param uriBuilder
	 * @return
	 * @throws BindException
	 * @throws InvalidParamException
	 * @throws IOException
	 * @throws SQLException
	 */
	@PostMapping(path = "/{reservationInfoId}/comments")
	public ResponseEntity<Map<String, String>> postComment(@PathVariable Long reservationInfoId,
		@Valid @ModelAttribute ReservationUserCommentRequestDto requestDto,
		BindingResult bindingResult, UriComponentsBuilder uriBuilder)
		throws BindException, InvalidParamException, IOException, SQLException {

		if (bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}

		List<MultipartFile> images = requestDto.getAttachedImages();
		List<FileDto> files = new ArrayList();
		try {
			if (images != null) {

				for (MultipartFile image : requestDto.getAttachedImages()) {

					Matcher matcher = imageContentTypePattern.matcher(image.getContentType());
					if (!matcher.find()) {
						throw new InvalidParamException("Content-Type", image.getContentType());
					}
					files.add(fileIoService.writeMultipartFile(imageDefaultPath, image));
				}
			}
			reservationService.addReservationUserComment(requestDto, files, reservationInfoId);
		} catch (InvalidParamException | IOException | SQLException exception) {
			fileIoService.removeFilesForRollback(files);

			if (exception instanceof InvalidParamException) {
				throw (InvalidParamException)exception;
			} else if (exception instanceof IOException) {
				throw (IOException)exception;
			} else {
				throw (SQLException)exception;
			}
		}

		URI redirectUri = uriBuilder.path("/")
			.build()
			.toUri();
		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("redirectUri", redirectUri.toString()),
			HttpStatus.OK);
	}
}
