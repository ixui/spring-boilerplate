package jp.co.ixui.spring.boilerplate.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 認証成功時に呼び出されるハンドラー
 * @author t.kawasaki
 *
 */
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


	@Override
    public void onAuthenticationSuccess(HttpServletRequest req,
    									 HttpServletResponse res,
    									 Authentication authentication) throws IOException, ServletException {

		// 認証結果を取得する場合はこのように取得することができる
        //AuthUserEntity user = (AuthUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        res.setStatus(HttpServletResponse.SC_OK);
        res.sendRedirect("/");
    }

}
