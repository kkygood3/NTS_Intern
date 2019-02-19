/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CommentResponse {
	private List<Comment> items;

	public List<Comment> getItems() {
		return items;
	}

	public void setItems(List<Comment> items) {
		this.items = items;
	}
}
