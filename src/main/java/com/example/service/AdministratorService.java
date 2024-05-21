package com.example.service;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者関連機能の業務処理を行うサービスクラス.
 *
 * @author io.yamanaka
 *
 */
@Service
@Transactional
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository = new AdministratorRepository();

    /**
     * 管理者情報を挿入するメソッド.
     *
     * @param administrator 管理者オブジェクト
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

    /**
     * ログイン処理をするメソッド.
     *
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return メールアドレスとパスワードに合致した管理者オブジェクト
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }

}
