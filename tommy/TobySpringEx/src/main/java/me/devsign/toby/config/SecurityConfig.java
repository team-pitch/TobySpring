package me.devsign.toby.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                .authorizeRequests()
                // 해당 path로 들어오는 요청은 인증없이 접근을 허용
                .antMatchers("/**").permitAll()
                // 나머지 요청은 모두 인증되어야 함
                .anyRequest().authenticated();

        super.configure(http);
    }
}
