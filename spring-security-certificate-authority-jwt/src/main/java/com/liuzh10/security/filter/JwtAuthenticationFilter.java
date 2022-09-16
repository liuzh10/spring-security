package com.liuzh10.security.filter;

import com.liuzh10.security.service.UserDetailsServiceImpl;
import com.liuzh10.utli.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private JwtUtils jwtUtils;

    @Resource
    private UserDetailsServiceImpl userDetailsService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * In this method, the token in the client request header is verified,
     * If it exists and is legal, encapsulate the information in the token into an object of type Authentication.
     * Finally using  SecurityContextHolder.getContext().setAuthentication(authentication); Change or delete the currently validated pricipal
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(jwtUtils.getHeader());

        //Check whether there is a token
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }
        // The account information is obtained through a token and stored in the context where the identity information is stored in a secure system.
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // pass
        chain.doFilter(request, response);
    }

    /**
     * Parse the information in the token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = jwtUtils.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        }
        return null;
    }
}
