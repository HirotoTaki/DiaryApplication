package com.diary.form;

import lombok.Data;

// 画面からの検索条件を格納する
@Data
public class GetForm {
	private String category;
	private String date;
}
