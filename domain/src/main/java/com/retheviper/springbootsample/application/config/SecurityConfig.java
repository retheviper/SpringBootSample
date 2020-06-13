package com.retheviper.springbootsample.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.retheviper.springbootsample.application.security.JWTAccessDeniedHandler;
import com.retheviper.springbootsample.application.security.JWTAuthenticationEntryPoint;
import com.retheviper.springbootsample.application.security.JWTAuthenticationFailureHandler;
import com.retheviper.springbootsample.application.security.JWTAuthenticationFilter;
import com.retheviper.springbootsample.application.security.JWTAuthenticationSuccessHandler;
import com.retheviper.springbootsample.application.security.JsonUsernamePasswordAuthenticationFilter;
import com.retheviper.springbootsample.common.constant.MemberRole;

import lombok.RequiredArgsConstructor;

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
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/web/member").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/exception/**", "/resources/static/**").permitAll()
                .antMatchers("/api/v1/web/admin/**").hasRole(MemberRole.ADMIN.getRoleName())
                .anyRequest().hasAnyRole(MemberRole.USER.getRoleName(), MemberRole.ADMIN.getRoleName())
                .and()
                .exceptionHandling().accessDeniedHandler(this.accessDeniedHandler)
                .and()
                .exceptionHandling().authenticationEntryPoint(this.authenticationEntryPoint)
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
