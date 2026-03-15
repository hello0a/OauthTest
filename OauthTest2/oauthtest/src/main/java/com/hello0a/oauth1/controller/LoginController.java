package com.hello0a.oauth1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hello0a.oauth1.domain.Users;
import com.hello0a.oauth1.dto.LoginRequest;
import com.hello0a.oauth1.dto.LoginResponse;
import com.hello0a.oauth1.dto.SignupRequest;
import com.hello0a.oauth1.service.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin("*")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
    
    private final LoginService loginService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody SignupRequest request) {
        Users user = new Users();

        log.info("회원가입 요청 id = {}", request.getId());

        user.setId(request.getId());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        boolean result = loginService.join(user);

        if (result) {
            log.info("회원가입 성공 id = {}", request.getId());
            return ResponseEntity.ok("SUCCESS");
        }
        return ResponseEntity.badRequest().body("FAIL");
    }
    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        
        try {
            Users user = loginService.login(request.getId(), request.getPassword());

            LoginResponse response = new LoginResponse(
                user.getUuid(),
                user.getId(),
                user.getNickname(),
                user.getUsername(),
                user.getEmail(),
                user.getProvider(),
                user.getEnabled()
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }
    // 회원 조회
    @GetMapping("/userInfo/{id}")
    public LoginResponse userInfo(@PathVariable("id") String id) {
        Users user = loginService.select(id);
        return new LoginResponse(
            user.getUuid(),
            user.getId(),
            user.getNickname(),
            user.getUsername(),
            user.getEmail(),
            user.getProvider(),
            user.getEnabled()
        );
    }
    
    
}
