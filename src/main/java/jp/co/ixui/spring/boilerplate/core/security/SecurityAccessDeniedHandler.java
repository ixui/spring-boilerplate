package jp.co.ixui.spring.boilerplate.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * アクセスしてはいけないURLにアクセスした場合に呼び出されるハンドラー
 * @author t.kawasaki
 *
 */
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest req,
						HttpServletResponse res,
						AccessDeniedException accessDeniedException) throws IOException, ServletException {

        res.sendRedirect(SecurityConfig.AUTH_ACCESS_DENIED_URL);

	}

}
