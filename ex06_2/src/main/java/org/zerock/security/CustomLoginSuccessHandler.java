package org.zerock.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j

// 로그인성공후 특정한동적을 제어할경우 상속 구현
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler{
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	
	log.warn("login success");
	
	List<String> roleNames = new ArrayList<>();
	
	authentication.getAuthorities().forEach(authority ->{
		
		roleNames.add(authority.getAuthority());
		
	});
	
	log.warn("Role Names :" + roleNames);

	if(roleNames.contains("ROLE_ADMIN")) {
		
		response.sendRedirect("/sample/admin");
		return;
	}

	if(roleNames.contains("ROLE_MEMBER")) {
		
		response.sendRedirect("/sample/member");
		return;
	}
	
	response.sendRedirect("/");
}
	

}
