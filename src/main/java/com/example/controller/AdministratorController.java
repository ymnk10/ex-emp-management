package com.example.controller;

import com.example.form.InsertAdministratorForm;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者登録時に使用するフォームクラス.
 *
 * @author io.yamanaka
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService = new AdministratorService();

    /**
     * 管理者登録画面に入力された管理者情報を登録するメソッド
     *
     * @param form 登録する管理者情報のフォーム
     * @return "administrator/insert" フォワード先
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }
}
