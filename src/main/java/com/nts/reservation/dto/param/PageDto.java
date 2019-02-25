/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.param;

/**
 * 페이징 처리용 DTO
 * @author jinwoo.bae
 */
public class PageDto {
	private int start; // 페이지 시작번호
	private int limit; // 페이지 제한개수

	public PageDto(int start, int limit) {
		setStart(start);
		setLimit(limit);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "PageDto [start=" + start + ", limit=" + limit + "]";
	}

}
