package kr.co.dbcs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.ContentSecurityPolicyHeaderWriter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}admin").roles("ADMIN", "USER");
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM MEMBER WHERE USERNAME = ?")
                .authoritiesByUsernameQuery("SELECT USERNAME, AUTHORITY FROM AUTH WHERE USERNAME = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .headers()
                .addHeaderWriter(
                        // 모든 URL에 대한 접근 허용
                        new ContentSecurityPolicyHeaderWriter(
                                "default-src *; script-src * 'unsafe-inline'; img-src * 'unsafe-inline'; style-src * 'unsafe-inline'; connect-src * 'unsafe-inline';"
                        )
                )
                .and()

                .csrf().disable()       // POST 요청 허용
                .authorizeRequests()    // 요청에 권한 설정을 적용
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll() // 정적 리소스에 모든 사용자 접근 허용
                .antMatchers("/member/join").permitAll() // 회원가입 페이지에 모든 사용자 접근 허용
                .antMatchers("/member/check").permitAll() // 중복체크 접근 허용
                .antMatchers("/member/chat").permitAll()
                .antMatchers("/movie/**").permitAll()
                .antMatchers("/ticket/**").permitAll()
                .antMatchers("/board/list").permitAll()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()

                // 모든 사용자가 폼 로그인 페이지에 접근할 수 있도록 허용
                .formLogin()
                .loginPage("/login").permitAll()
                .and()

                //모든 사용자가 로그아웃 기능을 사용할 수 있도록 허용
                .logout()
                .logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
