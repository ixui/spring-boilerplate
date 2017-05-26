package jp.co.ixui.spring.boilerplate.core.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jp.co.ixui.spring.boilerplate.core.security.SecurityConfig;
import jp.co.ixui.spring.boilerplate.core.security.exception.AccessDeniedException;
import jp.co.ixui.spring.boilerplate.core.security.exception.NotAuthlizedException;

/**
 * 認証用コントローラ
 * @author t.kawasaki
 *
 */
@Controller
public class AuthenticationController {

	@GetMapping(SecurityConfig.AUTH_LOGIN_URL)
	public String login(){
		return "auth/login";
	}

	@GetMapping(SecurityConfig.AUTH_NOT_AUTHLIZED_URL)
	public void handleNotAuthlized(){
		throw new NotAuthlizedException("認証エラーです");
	}

	@GetMapping(SecurityConfig.AUTH_ACCESS_DENIED_URL)
	public void handleAccessDenied(){
		throw new AccessDeniedException("対象機能はご利用いただけません");
	}

}
