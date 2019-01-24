/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.enums;

/**
 * @author 임상현, life4lord93@nts-corp.com
 * Todo의 Type
 */
public enum TodoType {

	TODO {
		@Override
		public String getNextType() {
			return "DOING";
		}
	},
	DOING {
		@Override
		public String getNextType() {
			return "DONE";
		}
	},
	DONE {
		@Override
		public String getNextType() {
			return "DONE";
		}
	};

	public abstract String getNextType();

}
