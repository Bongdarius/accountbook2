package com.accountbook.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


@Component
public class AuditorAwareImpl implements AuditorAware<Integer> {
    
    @Override
    public Optional<Integer> getCurrentAuditor() {
        // 예시로 현재 사용자 ID를 반환. 실제로는 SecurityContextHolder에서 인증 정보를 가져오거나
        // Session 또는 다른 방법으로 현재 사용자 정보를 가져와야 합니다.
        // 예시: return Optional.of(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getId());
        return Optional.of(1); // 예시로 1을 반환
    }
}