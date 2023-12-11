package com.fastcampus.mini9.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastcampus.mini9.domain.member.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByClientIpAndUserAgent(String clientIp, String userAgent);

	Optional<RefreshToken> findByTokenValue(String tokenValue);
}
