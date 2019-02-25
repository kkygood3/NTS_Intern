package com.nts.reservation.comment.dto;

import javax.validation.constraints.Positive;

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
	// TODO annotation 추가 
	private String comment;
	private String fileName;
	private String contentType;
	private MultipartFile file;
}
