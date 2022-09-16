package com.liuzh10.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuzh10.response.Result;
import com.liuzh10.utli.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    @Resource
    private JwtUtils jwtUtils;

    /**
     * default
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO. Here you can filter as needed, according to the source code to modify the extension is very convenient
        super.doFilter(request, response, chain);
    }

    /**
     * If login authentication is required, it will be preprocessed here
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        // TODO .In the login authentication, you can do some other verification operations, such as verification code
        return super.attemptAuthentication(request, response);
    }


    /**
     * Login successfully called, return token
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException {
        String token = jwtUtils.generateToken(authResult.getName());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        Result result = Result.ok().data("token", token);
        response.getWriter().print(new ObjectMapper().writeValueAsString(result));
    }


}
