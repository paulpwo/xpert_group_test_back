package com.xpertgroup.demo.config;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.xpertgroup.demo.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    // private final UserDetailsService userDetailsService; // Comentado temporalmente

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        
        // Skip JWT processing for public endpoints
        String requestURI = request.getRequestURI();
        log.debug("Processing request: {}", requestURI);
        
        if (isPublicEndpoint(requestURI)) {
            log.debug("Skipping JWT processing for public endpoint: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }
        
        // Only process JWT for protected endpoints
        log.debug("Processing JWT for protected endpoint: {}", requestURI);
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("No JWT token provided for protected endpoint: {}", requestURI);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        jwt = authHeader.substring(7);
        try {
            userEmail = jwtService.extractEmail(jwt);
            
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Temporalmente sin UserDetailsService
                if (jwtService.validateToken(jwt, userEmail)) {
                    // Crear una autenticación con autoridades
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userEmail,
                            null,
                            Collections.singletonList(new SimpleGrantedAuthority("USER"))
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    log.debug("JWT token validated for user: {} with authorities", userEmail);
                } else {
                    log.warn("Invalid JWT token for user: {}", userEmail);
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        
        filterChain.doFilter(request, response);
    }

    private boolean isPublicEndpoint(String requestURI) {
        boolean isPublic = requestURI.startsWith("/api/users/login") ||
               requestURI.startsWith("/api/users/register") ||
               requestURI.startsWith("/swagger-ui") ||
               requestURI.startsWith("/api-docs") ||
               requestURI.startsWith("/h2-console") ||
               requestURI.equals("/") ||
               requestURI.equals("/test") ||
               requestURI.equals("/health-simple") ||
               requestURI.equals("/test-simple");
        
        log.debug("Request URI: {}, isPublic: {}", requestURI, isPublic);
        return isPublic;
    }
}