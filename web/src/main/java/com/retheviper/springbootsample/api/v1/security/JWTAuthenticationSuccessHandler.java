package com.retheviper.springbootsample.api.v1.security;

import com.retheviper.springbootsample.common.constant.type.HeaderType;
import com.retheviper.springbootsample.domain.entity.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom AuthenticationSuccessHandler.
 *
 * @author retheviper
 */
@Component
@RequiredArgsConstructor
public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * JWT Token Provider
     */
    private final JWTProvider provider;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request,
                                        final HttpServletResponse response,
                                        final Authentication auth) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        final Member member = (Member) auth.getPrincipal();
        response.setHeader(HeaderType.AUTH_TOKEN.getValue(),
                this.provider.createToken(member.getUserId(), member.getRoles()));
        response.setStatus(HttpStatus.OK.value());
    }
}
