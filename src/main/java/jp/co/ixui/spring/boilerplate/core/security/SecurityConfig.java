package jp.co.ixui.spring.boilerplate.core.security;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.co.ixui.spring.boilerplate.core.security.provider.AuthorizationProvider;
import jp.co.ixui.spring.boilerplate.core.security.service.AuthorizationService;

/**
 * Spring Securityに関する設定クラス
 * @author t.kawasaki
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	public static final String AUTH_LOGIN_URL = "/auth/login";
	public static final String AUTH_LOGOUT_URL = "/auth/logout";
	public static final String AUTH_ENTRY_URL = "/auth/login";
	public static final String AUTH_NOT_AUTHLIZED_URL = "/auth/error/not_authlized";
	public static final String AUTH_ACCESS_DENIED_URL = "/auth/error/access_denied";

	/**
	 * 権限の種類.
	 */
	public static enum AuhorityTypes {
		/** 一般 */
		NORMAL("0"),
		/** 管理者 */
		ADMIN("9");

		private final String code;

		private AuhorityTypes(final String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}

		public String getName() {
			return AuhorityTypes.getType(this.code).toString();
		}

		public String getRoleName() {
			return "ROLE_" + this.getName();
		}

	    public static AuhorityTypes getType(String code) {
			if (code == null) {
				return null;
			}

			return Arrays.stream(values())
					.filter(s -> StringUtils.equals(s.getCode(), code))
					.findFirst().get();
	    }
	}

	/**
	 * 設定処理
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// 認可に関する設定
			.authorizeRequests()
				// Static配下のリソース系は除外
				.antMatchers("/css/**",
							"/fonts/**",
							"/images/**",
							"/js/**",
							"/libs/**").permitAll()

				// ログイン画面は除外
				.antMatchers(AUTH_LOGIN_URL).permitAll()
				// エラー画面は除外
				.antMatchers("/auth/error/**").permitAll()

				// ホーム画面は除外
				.antMatchers("/").permitAll()
				// /admin 以下は管理者権限が必要
				.antMatchers("/admin/**")
					.hasAnyRole(AuhorityTypes.ADMIN.toString())

				.anyRequest().authenticated()  //上記にマッチしなければ未認証の場合エラー


		.and()
			 // ログインに関する設定
			.csrf().disable()  							// CSRFを使用しない場合の設定(使用する場合は不要)
			.formLogin()								// フォームからのログインがある場合の設定(利用しない場合は不要)
				.loginPage(AUTH_LOGIN_URL)
				.loginProcessingUrl(AUTH_ENTRY_URL) 		// ログインを行う場合のURLを設定する
				.usernameParameter("username") 				// ユーザ名として使用するパラメータのName
				.passwordParameter("password")				// パスワードとして使用するパラメータのName
				.successHandler(new SecurityAuthenticationSuccessHandler())		// 認証成功時に呼び出すハンドラー
				.failureHandler(new SecurityAuthenticationFailureHandler())		// 認証失敗時に呼び出すハンドラー

		.and()
			// 認可例外時に関する設定
			.exceptionHandling()
				.accessDeniedHandler(new SecurityAccessDeniedHandler())		// 認可されていないURLへのアクセスがあった際に呼び出すハンドラー

		.and()
			// ログアウトに関する設定
			.logout()
				// ログアウトがパス(GET)の場合設定する（CSRF対応）
	            .logoutRequestMatcher(new AntPathRequestMatcher(AUTH_LOGOUT_URL))
	            // ログアウトがPOSTの場合設定する
	            //.logoutUrl(AUTH_LOGOUT_URL)
				.logoutSuccessUrl("/")
				// セッションを破棄する
	            .invalidateHttpSession(true)
	            // ログアウト時に削除するクッキー名
	            .deleteCookies("JSESSIONID")
	            .permitAll();



	}


	/**
	 * 認証を行うサービスを設定する
	 * @author t.kawasaki
	 *
	 */
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Autowired
		AuthorizationProvider authorizationProvider;

		@Autowired
		AuthorizationService authorizationService;

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authorizationProvider)
				.userDetailsService(authorizationService);
		}

	}

}
