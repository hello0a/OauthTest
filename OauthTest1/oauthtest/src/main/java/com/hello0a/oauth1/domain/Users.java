package com.hello0a.oauth1.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Users {
    
    private Long no;
    private String uuid;
    // 일반 로그인
    private String id;
    private String password;
    private String nickname;
    private String username;
    private String email;
    // 소셜 로그인
    private String provider;
    private String providerId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean enabled;
    // Security 권한
    private List<UserAuth> authList;

    public Users() {
        this.uuid = UUID.randomUUID().toString();
    }
}
