package com.example.service;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import com.example.repository.AdministratorRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 従業員関連機能の業務処理を行うサービスクラス.
 *
 * @author io.yamanaka
 *
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository = new EmployeeRepository();

    /**
     * 従業員情報を全件取得するメソッド.
     *
     * @return 従業員オブジェクトが入ったリスト
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    /**
     * 主キーと一致する従業員情報を1件取得するメソッド.
     *
     * @return 主キーと一致した従業員オブジェクト
     */
    public Employee showDetail(Integer id) {
        return employeeRepository.load(id);
    }

    /**
     * 従業員情報を更新するメソッド.
     *
     * @param employee 従業員オブジェクト
     */
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }
}
