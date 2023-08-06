package com.diary.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diary.entity.Diary;
import com.diary.form.GetForm;
import com.diary.form.PostForm;
import com.diary.form.PutForm;
import com.diary.repository.IDiaryDao;

@Service // サービスクラスに付与するアノテーション
@Transactional
// これを記載するとクラス内のメソッド全てが例外時に
// 自動でロールバックしてくれる。メソッド単位で指定
// することも可能。
public class DiaryService {

	private final IDiaryDao dao;

	@Autowired
	// 引数にインターフェースを指定していることで
	// IDiaryDaoインターフェースをimplementsしている
	// クラスであれば、受け取ることが可能になる
	// このクラスがnewされたら自信を呼び出す。
	public DiaryService(IDiaryDao dao) {
		this.dao = dao;
	}

	
	public List<Diary> findList(GetForm form) {
		List<Map<String, Object>> resultList = null;
		
		// 検索条件：　無
		if (form.getCategory() == "" && form.getDate() == ""
			|| form.getCategory() == null && form.getDate() == null) {
			resultList = dao.findListNoConditions();
			System.out.println("無");
			
		// 検索条件：　分類
		} else if (form.getCategory() != "" && form.getDate() == "") {
			resultList = dao.findListAndCategory(form.getCategory());
			System.out.println("分類");
		// 検索条件：　年月
		} else if (form.getDate() != "" && form.getCategory() == "") {
			resultList = dao.findLisAndDate(form.getDate());
			System.out.println("年月");
		// 検索条件：　分類と年月
		} else {
			resultList = dao.findListAndCategoryDate(form.getCategory(), form.getDate());
			System.out.println("分類と年月");
		}
		
		List<Diary> list = new ArrayList<Diary>();
		
		for (Map<String, Object> result : resultList) {
			// diaryエンティティにデータを詰めている。
			Diary diary = new Diary();
			diary.setId((int) result.get("id"));
			diary.setCategory((String) result.get("category"));
			diary.setTitle((String) result.get("title"));
			diary.setContent((String) result.get("content"));
			diary.setDate((String) result.get("date"));
			diary.setUpdate_datetime((Timestamp) result.get("update_datetime"));
			diary.setName((String) result.get("name"));
			list.add(diary);
		}
		return list;
	}
	
	
	public Diary findById(int id) {
		try {
			return dao.findById(id);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	
	public int insert(PostForm form) {
		int count = 0;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		count = dao.insert(form.getCategoryForm(), form.getTitleForm(), form.getContentForm(), form.getDateForm(), timestamp);
	    return count;
	}
	
	public int update(PutForm form) {
		int count = 0;
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		count = dao.update(form.getCategoryForm(), form.getTitleForm(), form.getContentForm(), form.getDateForm(), timestamp, form.getId());
		return count;
	}
	
	
	public int delete(int id) {
		int count = 0;
		count = dao.delete(id);
	    return count;
	}
}

