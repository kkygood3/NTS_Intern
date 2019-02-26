package com.nts.reservation.comment.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentParam {
	@Positive
	private int reservationInfoId;
	@Positive
	private int productId;
	@Positive
	private double score;
	@Size(min = 5, max = 400)
	private String comment;
	private String fileName;
	private String contentType;
	private MultipartFile file;
}
