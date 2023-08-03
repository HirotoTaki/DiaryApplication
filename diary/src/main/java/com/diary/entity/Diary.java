package com.diary.entity;

import java.sql.Timestamp;

public class Diary {

	private int id;
	private String category;
	private String title;
	private String content;
	private String date;
	private Timestamp update_datetime;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Timestamp getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Timestamp update_datetime) {
		this.update_datetime = update_datetime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
