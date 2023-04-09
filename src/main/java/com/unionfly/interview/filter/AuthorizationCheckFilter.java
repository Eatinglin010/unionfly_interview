package com.unionfly.interview.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.unionfly.interview.dao.UserDao;
import com.unionfly.interview.service.UserService;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class AuthorizationCheckFilter extends OncePerRequestFilter{

    @Autowired
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        if(!req.getServletPath().equals("/user/login")&&!req.getServletPath().equals("/user/register")){
            String authorHeader =  req.getHeader(AUTHORIZATION);
            if(authorHeader!= null){
                try{
                    Claims claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor("unionflyunionflyunionflyunionflyunionflyunionflyunionflyunionflyunionfly".getBytes()))
                            .parseClaimsJws(authorHeader).getBody();
                    System.out.println("JWT payload:"+claims.toString());
                    userService.isVerify(authorHeader);
                    chain.doFilter(req, res);

                }catch(MalformedJwtException e){
                    System.err.println("Error : "+e);
                    res.setStatus(FORBIDDEN.value());

                    Map<String, String> err = new HashMap<>();
                    err.put("錯誤", "未通過身分驗證");
                    res.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(res.getOutputStream(), err);
                }
            }else{
                res.setStatus(UNAUTHORIZED.value());
                Map<String, String> err = new HashMap<>();
                err.put("錯誤","您尚未登入");
                res.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(res.getOutputStream(), err);
            }
        }else{
            chain.doFilter(req, res);
        }

    }

}