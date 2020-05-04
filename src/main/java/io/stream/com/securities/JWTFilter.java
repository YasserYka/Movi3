package io.stream.com.securities;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.stream.com.models.User;
import io.stream.com.services.UserService;

@Component
public class JWTFilter extends OncePerRequestFilter{
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
        String jwt, username;
        final String authHeader = request.getHeader("Authorization");
        
        jwt = username = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			jwt = authHeader.substring(7);
			username = jwtService.getUsername(jwt);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User user = userService.loadUserByUsername(username);
			if(jwtService.validateToken(jwt, user)) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(token);
			}
		}
		filterChain.doFilter(request, response);
	}
}