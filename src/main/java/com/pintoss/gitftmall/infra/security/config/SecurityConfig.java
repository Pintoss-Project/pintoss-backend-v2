package com.pintoss.gitftmall.infra.security.config;

<<<<<<< HEAD:src/main/java/com/pintoss/gitftmall/infra/security/SecurityConfig.java
=======
import com.pintoss.gitftmall.common.utils.HttpServletUtils;
import com.pintoss.gitftmall.infra.security.service.SecurityService;
import com.pintoss.gitftmall.infra.security.filter.jwt.JwtFilter;
import com.pintoss.gitftmall.infra.security.filter.jwt.TokenProvider;
>>>>>>> develop:src/main/java/com/pintoss/gitftmall/infra/security/config/SecurityConfig.java
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

<<<<<<< HEAD:src/main/java/com/pintoss/gitftmall/infra/security/SecurityConfig.java
    private final CustomOAuth2UserService customOAuth2UserService;
=======
    private final TokenProvider tokenProvider;
    private final HttpServletUtils servletUtils;
    private final SecurityService securityService;
>>>>>>> develop:src/main/java/com/pintoss/gitftmall/infra/security/config/SecurityConfig.java

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/h2-console/**",
                        "/resources/**",
                        "/static/**"
                );
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .formLogin(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .anonymous(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(new JwtFilter(tokenProvider,servletUtils, securityService), UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(request ->
                request.requestMatchers("/**").permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo
                        -> userInfo.userService(customOAuth2UserService)) // AccessToken 발급시 loadUser 실행.
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/success");
                })
                .failureHandler((request, response, exception) -> {
//                    System.out.println("OAuth2 로그인 실패: " + exception.getMessage());
                    response.sendRedirect("/failure");
                })
            )
            .build();
    }
}
