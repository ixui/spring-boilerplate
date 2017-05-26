package jp.co.ixui.spring.boilerplate.core.security.exception;

/**
 * 認可エラー時に発生させる例外クラス
 * @author t.kawasaki
 *
 */
public class AccessDeniedException extends org.springframework.security.access.AccessDeniedException {

	public AccessDeniedException(String msg) {
		super(msg);
	}

}
