package com.binar.finalproject.BEFlightTicket.config;


import com.binar.finalproject.BEFlightTicket.security.AuthEntryPointJwt;
import com.binar.finalproject.BEFlightTicket.security.AuthTokenFilter;
import com.binar.finalproject.BEFlightTicket.security.oauth2.CustomOAuth2UserService;
import com.binar.finalproject.BEFlightTicket.security.oauth2.OAuthLoginSuccessHandler;
import com.binar.finalproject.BEFlightTicket.service.impl.security.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SpringSecurityConfig {

    final UserDetailsServiceImpl userDetailsService;

    private final AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
        http.authorizeRequests().antMatchers("/user/**").permitAll()
                .antMatchers("/role/**").permitAll()
                .antMatchers("/airplane/**").permitAll()
                .antMatchers("/airports/**").permitAll()
                .antMatchers("/cities/**").permitAll()
                .antMatchers("/countries/**").permitAll()
                .antMatchers("/gates/**").permitAll()
                .antMatchers("/id-card/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/passport/**").permitAll()
                .antMatchers("/payment/**").permitAll()
                .antMatchers("/route/**").permitAll()
                .antMatchers("/schedule/**").permitAll()
                .antMatchers("/seat/**").permitAll()
                .antMatchers("/terminals/**").permitAll()
                .antMatchers("/ticket/**").permitAll()
                .antMatchers("/traveler-list/**").permitAll()
                .antMatchers("/oauth/**").permitAll()
                .anyRequest().permitAll();
        http.oauth2Login()
                .userInfoEndpoint()
                .userService(oAuth2UserService)
                .and()
                .successHandler(oAuthLoginSuccessHandler);;

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Autowired
    CustomOAuth2UserService oAuth2UserService;
    @Autowired
    OAuthLoginSuccessHandler oAuthLoginSuccessHandler;
}
