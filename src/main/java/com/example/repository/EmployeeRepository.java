package com.example.repository;

import com.example.domain.Administrator;
import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * employeesテーブルを操作するリポジトリ.
 *
 * @author io.yamanaka
 *
 */
@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Employeeオブジェクトを生成するローマッパー.
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = new BeanPropertyRowMapper<>(Employee.class);


    /**
     * 従業員一覧情報を年齢順で取得します.
     *
     * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
     */
    public List<Employee> findAll() {
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics FROM employees ORDER BY hire_date DESC";

        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER); // ←ここに実行の処理を書く

        System.out.println("findAll()呼ばれた");

        return employeeList;
    }

    /**
     * 主キー検索を行います.
     *
     * @param id 検索したい主キーの値
     * @return 従業員情報(検索されなかった場合は非検査例外が発生する)
     */
    public Employee load(Integer id) {
        String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics FROM employees WHERE id=:id";

        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

        System.out.println("load()呼ばれた");

        return employee;
    }

    /**
     * 従業員情報を登録or更新します.
     *
     * @param employee 従業員情報
     */
    public void update(Employee employee) {
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String updateSql = "UPDATE employees SET name=:name,image=:image,gender=:gender,hire_date=:hireDate,mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,salary=:,salary,characteristics=:characteristics WHERE id=:id;";

        // ここに実行処理を書く
        template.update(updateSql, param);
        System.out.println("update()呼ばれた");
    }

}
