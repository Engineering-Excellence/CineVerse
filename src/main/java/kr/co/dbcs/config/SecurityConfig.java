package kr.co.dbcs.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .inMemoryAuthentication()
                .withUser("user").password("{noop}user").roles("USER")
                .and()
                .withUser("admin").password("{noop}admin").roles("ADMIN", "USER");
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(encoder())
//                .usersByUsernameQuery("SELECT username, password, enabled FROM USERS WHERE username = ?")
//                .authoritiesByUsernameQuery("SELECT username, authorities FROM USERS WHERE username = ?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()       // POST 요청 허용
                .authorizeRequests()    // 요청에 권한 설정을 적용
                .antMatchers("/join").permitAll() // 회원가입 페이지에 모든 사용자 접근 허용
                .and()

                // 모든 사용자가 폼 로그인 페이지에 접근할 수 있도록 허용
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

                //모든 사용자가 로그아웃 기능을 사용할 수 있도록 허용
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }

    /*@Bean
    public BCryptPasswordEncoder encoder() {

        return new BCryptPasswordEncoder();
    }*/
}
