package jp.co.ixui.spring.boilerplate.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 認証失敗時に呼び出されるハンドラー
 * @author t.kawasaki
 *
 */
public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler  {

	@Override
	public void onAuthenticationFailure(HttpServletRequest req,
										 HttpServletResponse res,
										 AuthenticationException ex)throws IOException, ServletException {

        res.sendRedirect(SecurityConfig.AUTH_NOT_AUTHLIZED_URL);

	}
}
