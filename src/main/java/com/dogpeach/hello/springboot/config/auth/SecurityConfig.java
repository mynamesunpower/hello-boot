package com.dogpeach.hello.springboot.config.auth;

import com.dogpeach.hello.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // csrf ~ disable() : h2-console 을 사용하기 위해 해당 옵션들을 disable
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                // authorizeRequests : URL 별 권한 관리를 설정하는 옵션, 이것을 선언해야 antMatchers 옵션 사용 가능
                .authorizeRequests()
                // antMatchers : 권한 관리 대상 지정 옵션, URL, HTTP 메소드별 관리가 가능함.
                // permitAll() : 전체 열람 권한을 줌.
                // /api/v1/** 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록.
                .antMatchers("/", "css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // anyRequest() 설정값 이외의 나머지 URL
                // authenticated() : 나머지 URL들은 모두 인증된 사용자만 허용, (로그인한 사용자)
                .anyRequest().authenticated()
                .and()
                // 로그아웃 기능에 대한 여러 설정의 진입점
                .logout()
                .logoutSuccessUrl("/")
                .and()
                // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들 담당
                .userInfoEndpoint()
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                // 소셜 서비스들에서 사용자 정보를 가져온 상태에서, 추가로 진행하고자 하는 기능.
                .userService(customOAuth2UserService);
    }
}
