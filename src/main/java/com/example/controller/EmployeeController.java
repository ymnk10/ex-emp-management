package com.example.controller;

import com.example.domain.Employee;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;
import com.example.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 従業員関連機能の処理の制御を行うコントローラ.
 *
 * @author io.yamanaka
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService = new EmployeeService();

    @Autowired
    private HttpSession session;

    /**
     * 従業員一覧を出力するメソッド.
     *
     * @param model リクエストスコープ
     * @return 従業員一覧画面へフォワード
     */
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();
        System.out.println(employeeList);
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }
}
