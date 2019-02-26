/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto.request;

import static com.nts.reservation.dto.request.regex.RegexPattern.WORD_MAX_400_REGEX;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 육성렬
 */
public class ReservationUserCommentRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Pattern(regexp = WORD_MAX_400_REGEX)
	private String comment;

	List<MultipartFile> attachedImages;

	@NotNull
	@Min(0)
	private Long productId;

	@NotNull
	@Min(1)
	@Max(5)
	private Integer score;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<MultipartFile> getAttachedImages() {
		return attachedImages;
	}

	public void setAttachedImages(List<MultipartFile> attachedImages) {
		this.attachedImages = attachedImages;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "AddCommentRequestDto [comment=" + comment + ", attachedImages=" + attachedImages + ", productId="
			+ productId + ", score=" + score + "]";
	}

}
