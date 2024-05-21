package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.repository.AdministratorRepository;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者関連機能の処理の制御を行うコントローラ.
 *
 * @author io.yamanaka
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService = new AdministratorService();

    @Autowired
    private HttpSession session;

    /**
     * 管理者登録画面を表示させるメソッド.
     *
     * @param form 登録する管理者情報のフォーム
     * @return 管理者登録画面へフォワード
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form){
        return "administrator/insert";
    }


    /**
     * 管理者登録画面に入力された管理者情報を登録するメソッド.
     *
     * @param form 登録する管理者情報のフォーム
     * @return ログイン画面へリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form){
        Administrator administrator = new Administrator();
        BeanUtils.copyProperties(form, administrator);
        administratorService.insert(administrator);
        return "redirect:/";
    }


    /**
     * ログインページへフォワードするメソッド.
     *
     * @param form ログイン時に入力されたフォーム
     * @return ログイン画面へフォワード
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }

    /**
     * ログインの判定をするメソッド.
     *
     * @param form ログイン時に入力されたフォーム
     * @param model リクエストスコープ
     * @return 従業員情報一覧ページへリダイレクト
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
        if (administrator == null){
            model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
            return "administrator/login";
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }

    /**
     * ログアウト処理をするメソッド.
     *
     * @param form ログイン時に入力されたフォーム
     * @return ログイン画面へリダイレクト
     */
    @GetMapping("/logout")
    public String logout(LoginForm form){
        session.invalidate();
        return "redirect:/";
    }
}
