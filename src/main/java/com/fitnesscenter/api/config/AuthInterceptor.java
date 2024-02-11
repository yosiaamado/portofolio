package com.fitnesscenter.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fitnesscenter.api.IService.IAuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	private IAuthenticationService eService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("Authorization");

		if (token != null && eService.getToken(token)) {
			return true;
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
	}
}
