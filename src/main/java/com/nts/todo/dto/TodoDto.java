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

	public enum TodoType {
		TODO("TODO", "DOING"), DOING("DOING", "DONE"), DONE("DONE", "DONE");

		String nextType;
		String inString;

		TodoType(String inString, String nextValue) {
			this.inString = inString;
			this.nextType = nextValue;
		}

		public boolean equals(String type) {
			return this.toString().equals(type);
		}

		public static String getNextType(String type) {
			return TodoType.valueOf(type).nextType;
		}
	}
}
