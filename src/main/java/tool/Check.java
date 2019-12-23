/*package user;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import servlet.login.CheckDate;

public class Check {

	private boolean errorFlag = false;
	HttpServletRequest request;

	private String nameError;
	private String birthdayError;
	private String manOrWomanError;
	private String mailError;
	private String passWordError;
	private String idError;

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public String errorCheck(User user) {

		boolean passWordCheck = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])[0-9a-z\\-]{8,16}$", user.getPassWord());
		boolean mailCheck = Pattern.matches(".*@.*", user.getMail());
		boolean birthday = CheckDate.checkDate(user.getYear() + "/" + user.getMonth() + "/" + user.getDay());

		if (user.getName().isEmpty()) {
			nameError = "���O����͂��Ă�������";
			errorFlag = true;
		}

		if (!(birthday)) {
			birthdayError = "���N�������s���ł�";
			errorFlag = true;
		}

		if (user.getManOrWoman() == null) {
			manOrWomanError = "���ʂ�I�����Ă�������";
			errorFlag = true;
		}

		if (!(mailCheck)) {
			mailError = "���[���A�h���X�ł͂���܂���";
			errorFlag = true;
		}

		if (!(passWordCheck)) {
			passWordError = "�p�X���[�h�͔��p�p�������Ɛ�����g�ݍ��킹��8�����ȏ���̂ł��˂������܂�";
			errorFlag = true;
		}

		if (user.getId().isEmpty()) {
			idError = "ID����͂��Ă�������";
			errorFlag = true;
		}

		return "";

	}

}
*/

package tool;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import jp.co.webAuction.db.dto.User;
import servlet.login.CheckDate;

public class Check {

	private boolean errorFlag = false;

	public boolean isErrorFlag() {
		return errorFlag;
	}

	public void errorCheck(HttpServletRequest request, User user) {

		boolean passWordCheck = Pattern.matches("^(?=.*[0-9])(?=.*[a-z])[0-9a-z\\-]{8,16}$", user.getPassWord());
		boolean mailCheck = Pattern.matches(".*@.*", user.getMail());
		boolean birthday = CheckDate.checkDate(user.getYear() + "/" + user.getMonth() + "/" + user.getDay());

		if (user.getUserName().isEmpty()) {
			request.setAttribute("nameError", "���O����͂��Ă�������");
			errorFlag = true;
		}

		if (!(birthday)) {
			request.setAttribute("birthdayError", "���N�������s���ł�");
			errorFlag = true;
		}

		if (user.getManOrWoman() == null) {
			request.setAttribute("manOrWomanError", "���ʂ�I�����Ă�������");
			errorFlag = true;
		}

		if (!(mailCheck)) {
			request.setAttribute("mailError", "���[���A�h���X�ł͂���܂���");
			errorFlag = true;
		}

		if (!(passWordCheck)) {
			request.setAttribute("passWordError", "�p�X���[�h�͔��p�p�������Ɛ�����g�ݍ��킹��8�����ȏ���̂ł��˂������܂�");
			errorFlag = true;
		}

		if (user.getId().isEmpty()) {
			request.setAttribute("idError", "ID����͂��Ă�������");
			errorFlag = true;
		}

	}

}
