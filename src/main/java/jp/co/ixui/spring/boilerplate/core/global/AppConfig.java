package jp.co.ixui.spring.boilerplate.core.global;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class AppConfig {

//	/**
//	 * DIコンテナにセッション管理してもらうための設定.
//	 *
//	 * @return
//	 */
//	@Bean
//	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//	UserSession userSession() {
//		return new UserSession();
//	}

//	/**
//	 * Tymeleafで使用する独自ヘルパーを登録する.
//	 *
//	 * @return ThymeleafHelperDialect
//	 */
//	@Bean
//	ThymeleafHelperDialect thymeleafHelperDialect(){
//		return new ThymeleafHelperDialect();
//	}

	/**
	 * アップロードファイルの最大サイズを設定.
	 *
	 * @return MultipartConfigElement
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("2000MB");
		factory.setMaxRequestSize("2000MB");
		return factory.createMultipartConfig();
	}

}
