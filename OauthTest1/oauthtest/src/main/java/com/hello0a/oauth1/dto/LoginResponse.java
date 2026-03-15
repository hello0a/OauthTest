package com.hello0a.oauth1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    
    private String uuid;
    private String id;
    private String nickname;
    private String username;
    private String email;

    private String provider;
    private Boolean enabled;
}
