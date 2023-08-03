package com.diary.form;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PutForm {
	private boolean updateFlag;
	
	private int id;
	
	private String categoryForm;
	
	@NotNull(message = "日付を入力してください。")
	private Date dateForm;
	
	@NotNull(message = "題名を入力してください。")
	@Size(min = 1, max = 25, message = "25文字以内で入力してください。")
	private String titleForm;
	
	private String contentForm;
	
	public boolean getUpdateFlag() {
		return updateFlag;
	}
	
	public void setUpdateFlag(boolean updateFlag) {
		this.updateFlag = updateFlag;
	}
	
	public String getCategoryForm() {
		return categoryForm;
	}
	
	public void setCategoryForm(String categoryForm) {
		this.categoryForm = categoryForm;
	}

	public Date getDateForm() {
		return dateForm;
	}

	public void setDateForm(Date dateForm) {
		this.dateForm = dateForm;
	}

	public String getTitleForm() {
		return titleForm;
	}

	public void setTitleForm(String titleForm) {
		this.titleForm = titleForm;
	}

	public String getContentForm() {
		return contentForm;
	}

	public void setContentForm(String contentForm) {
		this.contentForm = contentForm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "bbbb";
	}
}
