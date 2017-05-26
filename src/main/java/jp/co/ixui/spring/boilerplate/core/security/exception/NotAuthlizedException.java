package jp.co.ixui.spring.boilerplate.core.security.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 認証エラー時に発生させる例外クラス
 * @author t.kawasaki
 *
 */
public class NotAuthlizedException extends UsernameNotFoundException {

	public NotAuthlizedException(String msg) {
		super(msg);
	}

}
