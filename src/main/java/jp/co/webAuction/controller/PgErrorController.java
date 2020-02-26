package jp.co.webAuction.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Web �A�v���P�[�V�����S�̂̃G���[�R���g���[���[�B
 */

@ControllerAdvice
public class PgErrorController implements ErrorController {

	@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	public String ArrayIndexOutOfBoundsExceptionHandler(HttpServletRequest req) {
		req.setAttribute("errorMessage", "���݂��Ȃ����i�ł�<br>");
		return "/error";
	}

	@ExceptionHandler(Throwable.class)
	public String ThrowableHandler(HttpServletRequest req) {
		req.setAttribute("errorMessage", "�G���[���������܂���<br>"
				+ "�Ǘ��҂ɖ₢���킹�Ă�������");
		return "/error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
