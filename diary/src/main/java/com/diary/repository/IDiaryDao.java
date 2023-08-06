package com.diary.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.diary.entity.Diary;

@Mapper
public interface IDiaryDao {
	
	// 検索条件：　無
	@Select({
		"SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name",
		"FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd",
		"WHERE c.group_cd = '01'"
	})
	List<Map<String, Object>> findListNoConditions();
	
	// 検索条件：　分類
	@Select({
		"SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name",
		"FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd",
		"WHERE c.group_cd = '01'",
		"AND c.cd = #{c}"
	})
	List<Map<String, Object>> findListAndCategory(String c);
	
	// 検索条件：　年月
	@Select({
		"SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name",
		"FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd",
		"WHERE c.group_cd = '01'",
		"AND TO_CHAR(d.date, 'YYYY/MM') = #{d}"
	})
	List<Map<String, Object>> findLisAndDate(String d);
	
	// 検索条件：　分類と年月
	@Select({
		"SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name",
		"FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd",
		"WHERE c.group_cd = '01'",
		"AND c.cd = #{c}",
		"AND TO_CHAR(d.date, 'YYYY/MM') = #{d}"
	})
	List<Map<String, Object>> findListAndCategoryDate(String c, String d);
	
	// 日記を登録する
	@Insert({
		"INSERT INTO diary(category, title, content, date , update_datetime)",
		"VALUES(#{category}, #{title}, #{content}, #{date}, #{up_datetime})"
	})
	public int insert(String category, String title, String content, Date date, Timestamp up_datetime);
	
	// idを指定して日記を1件取得
	@Select({
		"SELECT d.id, d.category, d.title, d.content, TO_CHAR(d.date, 'YYYY/MM/DD') AS date, d.update_datetime, c.name",
		"FROM diary AS d INNER JOIN category_code AS c ON d.category = c.cd",
		"WHERE d.id = #{id}"
	})
	Diary findById(int id);
	
	// 日記を更新する
	@Update({
		"UPDATE diary",
		"SET category= #{category}, title= #{title}, content= #{content}, date= #{date}, update_datetime= #{up_datetime}",
		"WHERE id= #{id}"
	})
	int update(String category, String title, String content, Date date, Timestamp up_datetime, int id);
	
	// 日記を削除する
	@Delete({
		"DELETE FROM diary",
		"WHERE id = #{id}"
	})
	int delete(int id);
}
