package com.fileblocker.flow.config;// CORS 전역 설정
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 프런트 개발 서버들(필요한 것만 등록)
        config.setAllowedOrigins(List.of(
                "http://localhost:5173", // Vite
                "http://localhost:8081"  // Vue CLI / webpack dev server
        ));
        // 허용 메서드
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        // 허용 헤더
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept", "X-Requested-With"));
        // 응답에서 노출할 헤더(필요 시)
        config.setExposedHeaders(List.of("Location", "Content-Disposition"));
        // 쿠키/세션/인증정보를 보낼 필요가 있으면 true (axios withCredentials와 세트)
        config.setAllowCredentials(false);
        // preflight 캐시(초)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 모든 경로에 CORS 정책 적용
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
