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


}
