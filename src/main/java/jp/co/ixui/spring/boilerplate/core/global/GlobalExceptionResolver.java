package jp.co.ixui.spring.boilerplate.core.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 例外処理全般を行うクラス
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	public static Log log = LogFactory.getLog(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest req,
										  HttpServletResponse res,
										  Object handler,
										  Exception ex) {

        log.error("例外を検知しました", ex);


        ModelAndView mav = new ModelAndView();

        switch(ex.getClass().getName() ){
        case "jp.co.ixui.spring.boilerplate.core.security.exception.AccessDeniedException":
            mav.setViewName("error");
            mav.addObject("errorMessage", "対象機能へのアクセスが拒否されました");
        	break;

        case "jp.co.ixui.spring.boilerplate.core.security.exception.NotAuthlizedException":
            mav.setViewName("error");
            mav.addObject("errorMessage", "ログインできませんでした");
        	break;

        default:
            mav.setViewName("error");
            mav.addObject("errorMessage", "システムエラーが発生しました。" + " 詳細をご確認ください：【" + ex + "】");
        	break;

        }

        return mav;
	}

}
