package com.hello0a.oauth1.service;

import org.apache.ibatis.annotations.Param;

import com.hello0a.oauth1.domain.Users;

public interface LoginService {
    
    // 회원 조회 - id
    public Users select (String id);
    // 회원 가입
    public boolean join(Users user);
    // 로그인
    Users login(String id, String password);
    // 소셜 로그인 조회
    public Users selectBySocial (@Param("provider") String provider, @Param("providerId") String providerId);

}
