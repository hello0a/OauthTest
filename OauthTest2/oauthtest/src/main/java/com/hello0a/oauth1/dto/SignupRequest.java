package com.hello0a.oauth1.dto;

import lombok.Data;

@Data
public class SignupRequest {
    
    private String id;
    private String password;
    private String nickname;
    private String username;
    private String email;
}
/**
 * SignupRequest
        ↓
    Users 생성
        ↓
    UserAuth 생성
        ↓
    ROLE_USER 저장

 */