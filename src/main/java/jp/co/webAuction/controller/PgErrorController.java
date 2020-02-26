package jp.co.webAuction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Web アプリケーション全体のエラーコントローラー。
 */

@ControllerAdvice
public class PgErrorController implements ErrorController {

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public String ArrayIndexOutOfBoundsExceptionHandler(HttpServletRequest req) {
		req.setAttribute("errorMessage", "存在しない商品です<br>");
		return "/error";
	}

	@ExceptionHandler(Throwable.class)
	public String ThrowableHandler(HttpServletRequest req) {
		req.setAttribute("errorMessage", "エラーが発生しました<br>"
				+ "管理者に問い合わせてください");
		return "/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
