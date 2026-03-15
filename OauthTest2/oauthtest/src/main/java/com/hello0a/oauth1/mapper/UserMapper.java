package com.hello0a.oauth1.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hello0a.oauth1.domain.UserAuth;
import com.hello0a.oauth1.domain.Users;

@Mapper
public interface UserMapper {
    
    // 회원 조회 - id
    public Users select (String id);
    // 회원 가입
    public int join(Users user);
    // 권한 등록
    public int insertAuth(UserAuth userAuth);
    // 권한 리스트 조회
    List<UserAuth> authList(String uuid);
    // 소셜 로그인 조회
    public Users selectBySocial (@Param("provider") String provider, @Param("providerId") String providerId);

}
