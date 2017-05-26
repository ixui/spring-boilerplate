package jp.co.ixui.spring.boilerplate.core.security.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jp.co.ixui.spring.boilerplate.core.security.SecurityConfig;
import lombok.Data;

/**
 * 認証ユーザに関するエンティティ
 * @author t.kawasaki
 */
@Component
@Data
public class AuthUserEntity implements Serializable, UserDetails {

	private String userName;
	private String password;
	private String authType;

	/**
	 * ユーザのもつ権限一覧
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority(SecurityConfig.AuhorityTypes.getType(authType).getRoleName()));
		return authorityList;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	/**
	 * SpringSecurity上必要な値 (必要に応じて変更する)
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * SpringSecurity上必要な値 (必要に応じて変更する)
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * SpringSecurity上必要な値 (必要に応じて変更する)
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * SpringSecurity上必要な値 (必要に応じて変更する)
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}
}
