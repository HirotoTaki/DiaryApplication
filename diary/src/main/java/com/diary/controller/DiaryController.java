package com.diary.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diary.entity.Diary;
import com.diary.form.GetForm;
import com.diary.form.PostForm;
import com.diary.form.PutForm;
import com.diary.service.DiaryService;

@Controller
public class DiaryController {

	private final DiaryService diaryservice;

	@Autowired
	// 利用してデータを取得するため、依存性の注入を行なっている
	public DiaryController(DiaryService diaryservice) {
		this.diaryservice = diaryservice;
	}

	
	/**
	 * 日記アプリの一覧画面を表示
	 * @param model
	 * @return resources/templates/list.html
	 */
	@GetMapping(path = {"/", "/diary"})
	public String diaryList(
			// 画面からのパラメータをformクラスにバインドすることが可能
			@ModelAttribute GetForm form,
			// 設定することで画面側にデータを渡すことができる。
			Model model) {
		// Serviceクラスを実行し、日記一覧のデータを取得
		// 日記一覧のデータを「list」という名前で渡している。
		List<Diary> list = diaryservice.findList(form);
		model.addAttribute("list", list);
		// これによって検索後の画面でも検索条件のデータを設定したままにすることが可能
		model.addAttribute("getForm", form);
		return "list";
	}

//	
//	/**
//	  * 新規登録へ遷移
//	  * @param model
//	  * @return resources/templates/form.html
//	  */
//	@GetMapping("/form")
//	public String formPage(
//			Model model) {
//		return "form";
//	}

	
	/**
	 * 「一覧へ」選択時、一覧画面へ遷移
	 * @param model
	 * @return resources/templates/list.html
	 */
	@PostMapping(path = { "/insert", "/form", "/update" }, params = "back")
	public String backPage(
			Model model
	) {
		return "redirect:/diary";
	}

	
	/**
	 * 日記を新規登録
	 * @param postForm
	 * @param model
	 * @return
	 */
	@PostMapping(path = "/insert", params = "insert")
	public String insert(
			@Validated @ModelAttribute PostForm form,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("putForm", form);
			model.addAttribute("error", "パラメータエラーが発生しました。");
			return "form";
		}
		
		int count = diaryservice.insert(form);
		model.addAttribute("postForm", form);
		return "redirect:/diary";
	}
	
	
	/**
	 * 日記のテーブルからレコードを1つだけ取り出したエンティティから
	 * ビュー(detail.html)に値を埋め込む。
	 * @param id
	 * @param model
	 * @return resources/templates/detail.html
	 */
	@GetMapping("/{id}")
	public String showUpdate(
			@PathVariable int id,
			Model model) {
		//Diaryを取得
		Optional<Diary> diaryOpl = Optional.ofNullable(diaryservice.findById(id));

		//NULLかどうかのチェック
		if (diaryOpl.isPresent()) {
			model.addAttribute("diary", diaryOpl.get());
			return "detail";
		} else {
			model.addAttribute("error", "対象データが存在しません");
			return "detail";
		}
	}
	
	
	/**
	  * 日記を編集
	  * @param putForm
	  * @param model
	  * @return
	  */
	@PostMapping(path = "/update", params = "update")
	public String update(
			@Validated @ModelAttribute PutForm form,
			BindingResult result,
			Model model
	) {
		if (result.hasErrors()) {
			model.addAttribute("error", "パラメータエラーが発生しました。");
			return "form";
		}
		int count = diaryservice.update(form);
		return "redirect:/diary";
	}


	/**
	 * 新規登録へ遷移
	 * @param model
	 * @return resources/templates/form.html
	 */
	@PostMapping("/form")
	public String formPage(
			@ModelAttribute PutForm form,
			Model model) {
		model.addAttribute("putForm", form);
		//新規登録か編集で分岐
		if (form.getUpdateFlag()) {
			model.addAttribute("update", true);
		} else {
			model.addAttribute("update", false);
		}
		return "form";
	}
	
	
	/**
	 * 日記を削除
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/delete")
	public String delete(
			@RequestParam int id,
			Model model) {
		int count = diaryservice.delete(id);
		return "redirect:/diary";
	}
}
