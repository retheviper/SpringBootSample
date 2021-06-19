package com.retheviper.springbootsample.api.v1.config;

import com.retheviper.springbootsample.api.v1.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security.
 *
 * @author retheviper
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Custom Authentication filter
     */
    private final JWTAuthenticationFilter filter;

    /**
     * Custom Authentication success handler
     */
    private final JWTAuthenticationSuccessHandler successHandler;

    /**
     * Custom Authentication failure handler
     */
    private final JWTAuthenticationFailureHandler failureHandler;

    /**
     * Custom Access denied handler
     */
    private final JWTAccessDeniedHandler accessDeniedHandler;

    /**
     * Custom Authentication Entry point
     */
    private final JWTAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Custom Logout success handler
     */
    private final JWTLogoutSuccessHandler logoutSuccessHandler;

    /**
     * {@inheritDoc}
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/test_db/**", "/resources/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/web/members").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/web/members/csv").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/web/board/**").permitAll()
                .antMatchers(HttpMethod.GET, "/download").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(this.accessDeniedHandler)
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .logout()
                .logoutUrl("/api/v1/web/logout")
                .logoutSuccessHandler(this.logoutSuccessHandler)
                .and()
                .addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(getJsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Create custom UsernamePasswordAuthenticationFilter for filtering credentials with JSON.
     *
     * @return JsonUsernamePasswordAuthenticationFilter
     */
    private JsonUsernamePasswordAuthenticationFilter getJsonUsernamePasswordAuthenticationFilter() {
        final JsonUsernamePasswordAuthenticationFilter jsonFilter = new JsonUsernamePasswordAuthenticationFilter();
        try {
            jsonFilter.setFilterProcessesUrl("/api/v1/web/login");
            jsonFilter.setAuthenticationManager(this.authenticationManagerBean());
            jsonFilter.setAuthenticationSuccessHandler(this.successHandler);
            jsonFilter.setAuthenticationFailureHandler(this.failureHandler);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonFilter;
    }
}
