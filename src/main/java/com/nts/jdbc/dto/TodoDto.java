/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.jdbc.dto;

/**
 * @author 육성렬
 */
public class TodoDto {
	private int id;
	private String title;
	private String name;
	private int sequence;
	private String type;
	private String regDate;

	private TodoDto(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.name = builder.name;
		this.sequence = builder.sequence;
		this.type = builder.type;
		this.regDate = builder.regDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public static class Builder {
		private int id;
		private String title;
		private String name;
		private int sequence;
		private String type;
		private String regDate;

		public Builder() {}

		public Builder id(int id) {
			this.id = id;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder sequence(int sequence) {
			this.sequence = sequence;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder regDate(String regDate) {
			this.regDate = regDate;
			return this;
		}

		public TodoDto build() {
			return new TodoDto(this);
		}
	}
}
