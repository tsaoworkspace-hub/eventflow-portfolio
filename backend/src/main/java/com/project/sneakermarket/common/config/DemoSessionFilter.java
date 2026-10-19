package com.project.sneakermarket.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Creates a local demo session so reviewers can exercise member-only flows
 * without copying the team project's authentication module.
 */
@Component
@ConditionalOnProperty(name = "app.demo-mode", havingValue = "true")
public class DemoSessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        if (request.getSession().getAttribute("memberId") == null) {
            request.getSession().setAttribute("memberId", 1L);
        }
        filterChain.doFilter(request, response);
    }
}
