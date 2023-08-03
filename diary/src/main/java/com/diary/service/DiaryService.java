package com.diary.service;

import java.util.List;

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
		return dao.findList(form);
		
	}
	
	
	public Diary findById(int id) {
		try {
			return dao.findById(id);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	
	public int insert(PostForm form) {
	    return dao.insert(form);
	}
	
	public int update(PutForm form) {
		return dao.update(form);
	}
	
	
	public int delete(int id) {
	    return dao.delete(id);
	}
}

