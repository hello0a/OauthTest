package com.hello0a.oauth1.dto;

import lombok.Data;

@Data
public class LoginRequest {
    
    private String id;
    private String password;
}
/**
 * LoginRequest
        ↓
    Users 조회
        ↓
    password 비교
        ↓
    LoginResponse 반환
 */
