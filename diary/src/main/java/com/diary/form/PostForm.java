package com.diary.form;


import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostForm {

	private int id;

	private String categoryForm;

	@NotNull(message = "日付を入力してください。")
	private Date dateForm;

	@NotNull(message = "題名を入力してください。")
	@Size(min = 1, max = 25, message = "25文字以内で入力してください。")
	private String titleForm;

	private String contentForm;
}