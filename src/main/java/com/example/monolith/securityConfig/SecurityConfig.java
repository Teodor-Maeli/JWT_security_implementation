package com.example.monolith.securityConfig;

import com.example.monolith.filter.CustomAuthenticationFilter;
import com.example.monolith.filter.CustomAuthorizationFilter;
import com.example.monolith.utility.TokenGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.concurrent.TimeUnit;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
@CrossOrigin("http://localhost:4200/*")
public class SecurityConfig {


    AuthenticationConfiguration auth;
    TokenGenerator tokenGenerator;


    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors();
        http.sessionManagement()
                .sessionCreationPolicy(STATELESS);
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll();
        http.authorizeRequests()
                .antMatchers("/**")
                .authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManager(auth), tokenGenerator));
        http.addFilterBefore(new CustomAuthorizationFilter(tokenGenerator), UsernamePasswordAuthenticationFilter.class);
//        http.formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .defaultSuccessUrl("/index");
//        http.rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.HOURS.toHours(1));
//        http.logout().logoutUrl("/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");



        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/resources/**", "/**/*.css", "/**/*.js");
    }


    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        log.info("Attempting to authenticate - ");
        return auth.getAuthenticationManager();
    }


}
