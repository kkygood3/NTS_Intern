/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.common.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@WebAppConfiguration
public class CommentServiceTest {

	@Autowired
	private CommentService commentService;

	@Test
	public void getProductDisplayCommentsInfoTest() {
		CommentListInfo commentsInfo = commentService.getCommentListInfo(1, CommentService.COUNT_LIMITED);
		System.out.println(commentsInfo);
	}

}
