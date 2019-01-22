package com.nts.todo.dto;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

/** TodoDto implementation 
 * Author : Jaewon Lee, lee.jaewon@nts-corp.com
 */
public class TodoDto {

	private Long id;
	private String title;
	private String name;
	private int sequence;
	private String regdate;
	private String type;

	private TodoDto(Builder builder) {
		this.id = builder.id;
		this.title = builder.title;
		this.name = builder.name;
		this.sequence = builder.sequence;
		this.type = builder.type;
		this.regdate = builder.regdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public static class Builder {
		private Long id;
		private String title;
		private String name;
		private int sequence;
		private String type;
		private String regdate;

		public Builder id(Long id) {
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

		public Builder regdate(String regdate) {
			this.regdate = regdate;
			return this;
		}

		public TodoDto build() {
			return new TodoDto(this);
		}
	}

	public static enum TodoType {
		TODO,
		DOING,
		DONE;

		@Override
		public String toString() {
			switch (this) {
				case TODO:
					return "TODO";
				case DOING:
					return "DOING";
				case DONE:
					return "DONE";
			}
			return null;
		}

		public boolean equals(String type) {
			switch (this) {
				case TODO:
					return "TODO".equals(type);
				case DOING:
					return "DOING".equals(type);
				case DONE:
					return "DONE".equals(type);
			}
			return false;
		}

		public static String getNextType(String type) {
			switch (type) {
				case "TODO":
					return "DOING";
				case "DOING":
					return "DONE";
				case "DONE":
					return "DONE";
			}
			return type;
		}
	}
}
