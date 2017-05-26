package jp.co.ixui.spring.boilerplate.core.security.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.ixui.spring.boilerplate.core.security.SecurityConfig;
import jp.co.ixui.spring.boilerplate.core.security.entity.AuthUserEntity;

/**
 * 認証用サービスクラス
 * @author t.kawasaki
 *
 */
@Service
public class AuthorizationService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// 本来はここでデータベースなどを参照しユーザ情報の詳細な情報を取得する
		// 認証情報テーブルとユーザ情報テーブルが分かれている場合にユーザ情報テーブルへアクセスするイメージ

		AuthUserEntity user = new AuthUserEntity();
		user.setUserName(username);

		if (StringUtils.equals(username, "admin")) {

			user.setAuthType(SecurityConfig.AuhorityTypes.ADMIN.getCode());

		} else {

			user.setAuthType(SecurityConfig.AuhorityTypes.NORMAL.getCode());

		}

		return user;
	}

}
