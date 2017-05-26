package jp.co.ixui.spring.boilerplate.core.security.provider;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import jp.co.ixui.spring.boilerplate.core.security.SecurityConfig;
import jp.co.ixui.spring.boilerplate.core.security.entity.AuthUserEntity;
import jp.co.ixui.spring.boilerplate.core.security.exception.NotAuthlizedException;

@Component
public class AuthorizationProvider implements AuthenticationProvider  {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String id = authentication.getName();
        String password = authentication.getCredentials().toString();

        // サンプルなのでここではパスワードに値が入っていればOKとする
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(password)) {
            throw new NotAuthlizedException("ログイン情報に不備があります。");
        }

        // ここでデータベースなどに接続し認証のチェックなどを行う
        // また、ここで取得したユーザ情報を元に AuhUserEntity を生成する
		// 認証情報テーブルとユーザ情報テーブルが分かれている場合に認証情報テーブルへアクセスするイメージ

		AuthUserEntity user = new AuthUserEntity();
		user.setUserName(id);
		user.setPassword(password);

		if (StringUtils.equals(id, "admin")) {

			// サンプルなので ユーザIDが admin なら管理者として扱う
			user.setAuthType(SecurityConfig.AuhorityTypes.ADMIN.getCode());

		} else {

			user.setAuthType(SecurityConfig.AuhorityTypes.NORMAL.getCode());

		}


        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
