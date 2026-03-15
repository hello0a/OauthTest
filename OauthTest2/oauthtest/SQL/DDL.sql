-- Active: 1770210803187@@127.0.0.1@3306@aloha

DROP TABLE IF EXISTS `user_auth`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `no` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "PK",
    `uuid` VARCHAR(50) NOT NULL UNIQUE COMMENT "UK",
    `id` VARCHAR(100) NOT NULL UNIQUE COMMENT "아이디",
    `password` VARCHAR(255) NOT NULL COMMENT "비밀번호",
    `nickname` VARCHAR(100) NOT NULL COMMENT "닉네임",
    `username` VARCHAR(100) COMMENT "이름",
    `email` VARCHAR(100) COMMENT "이메일",
    `provider` ENUM('local', 'kakao', 'naver', 'google') DEFAULT 'local' COMMENT "로그인 종류",
    `provider_id` VARCHAR(100) COMMENT "소셜 사용자 고유ID",
    UNIQUE KEY uk_provider (provider, provider_id) COMMENT "중복방지",
    `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT "등록일자",
    `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT "수정일자",
    enabled BOOLEAN NULL DEFAULT TRUE COMMENT "활성화여부"
) COMMENT '회원';


CREATE TABLE `user_auth` (
    `no` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT "PK",
    `uuid` VARCHAR(100) NOT NULL COMMENT "uuid",
    `auth` VARCHAR(100) NOT NULL DEFAULT 'user' COMMENT "권한",
    FOREIGN KEY (uuid) REFERENCES users(uuid) ON DELETE CASCADE
) COMMENT '회원권한';

-- UUID 생성
SET @uuid = UUID();

INSERT INTO `users` ( uuid, id, password, nickname, email )
VALUES ( @uuid, 'admin', '$2a$10$CNcBaLcB7YOpNNCL8pyipOgtbDGBjC02JKVuKiPWGNXXqwdfZy/Qu', '관리자', 'admin@kakao.com');
INSERT INTO `user_auth` ( uuid, auth )
VALUES ( @uuid, 'ROLE_ADMIN');