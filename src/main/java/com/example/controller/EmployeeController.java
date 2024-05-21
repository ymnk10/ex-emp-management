package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }

    /**
     * 従業員詳細画面を表示するメソッド.
     *
     * @param id 従業員ID
     * @param model  リクエストスコープ
     * @param form 更新する従業員情報が入ったフォーム
     * @return 従業員詳細画面へフォワード
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.valueOf(id));
        model.addAttribute("employee", employee);
        return "employee/detail";
    }

    /**
     * 扶養人数を更新するメソッド.
     *
     * @param form 更新する従業員情報が入ったフォーム
     * @return 従業員一覧画面へリダイレクト
     */
    @PostMapping("/update")
    public String update(UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.valueOf(form.getId()));
        employee.setDependentsCount(Integer.valueOf(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }
}
