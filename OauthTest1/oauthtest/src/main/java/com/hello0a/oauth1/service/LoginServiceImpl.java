package com.hello0a.oauth1.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hello0a.oauth1.domain.UserAuth;
import com.hello0a.oauth1.domain.Users;
import com.hello0a.oauth1.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users select(String id) {
        return userMapper.select(id);
    }

    @Override
    public boolean join(Users user) {
        
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        int result = userMapper.join(user);

        if (result > 0) {
            UserAuth auth = new UserAuth();
            auth.setUuid(user.getUuid());
            auth.setAuth("ROLE_USER");

            userMapper.insertAuth(auth);

            return true;
        }
        return false;
    }

    @Override
    public Users login(String id, String password) {
        Users user = userMapper.select(id);
        
        if (user == null) {
            throw new RuntimeException("존재하지 않는 사용자");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호 불일치");
        }
        return user;
    }

    @Override
    public Users selectBySocial(String provider, String providerId) {
        return userMapper.selectBySocial(provider, providerId);
    }
    
}
