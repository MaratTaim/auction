package com.auction.commons.openid;

import com.nimbusds.oauth2.sdk.ParseException;
import com.okta.jwt.JoseException;
import com.okta.jwt.Jwt;
import com.okta.jwt.JwtHelper;
import com.okta.jwt.JwtVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "jwtFilter")
public class JwtFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    private JwtVerifier jwtVerifier;

    @Override
    public void init(FilterConfig filterConfig) {
        //may be add login page and take this creds from user and insert were?
        //TODO move oktaDomain and clientId to properties
        try {
            jwtVerifier = new JwtHelper()
                    .setIssuerUrl("https://dev-645009.okta.com/oauth2/default")
                    .setClientId("0oa27h0yu7SOOU0SY4x6")
                    .build();
        } catch (IOException | ParseException e) {
            LOGGER.error("Configuring JWT Verifier failed!", e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        LOGGER.info("In JwtFilter, path: " + request.getRequestURI());
        // Get access token from authorization header
        String authHeader = request.getHeader("authorization");
        if (authHeader == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied.");
            return;
        } else {
            LOGGER.info("authHeader is " + authHeader);
            String accessToken = authHeader.substring(authHeader.indexOf("Bearer ") + 7);
            try {
                Jwt jwt = jwtVerifier.decodeAccessToken(accessToken);
                LOGGER.info("Hello, " + jwt.getClaims().get("sub"));
            } catch (JoseException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access denied.");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
