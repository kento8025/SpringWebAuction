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
			nameError = "名前を入力してください";
			errorFlag = true;
		}

		if (!(birthday)) {
			birthdayError = "生年月日が不正です";
			errorFlag = true;
		}

		if (user.getManOrWoman() == null) {
			manOrWomanError = "性別を選択してください";
			errorFlag = true;
		}

		if (!(mailCheck)) {
			mailError = "メールアドレスではありません";
			errorFlag = true;
		}

		if (!(passWordCheck)) {
			passWordError = "パスワードは半角英小文字と数字を組み合わせた8文字以上ものでおねがいします";
			errorFlag = true;
		}

		if (user.getId().isEmpty()) {
			idError = "IDを入力してください";
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
			request.setAttribute("nameError", "名前を入力してください");
			errorFlag = true;
		}

		if (!(birthday)) {
			request.setAttribute("birthdayError", "生年月日が不正です");
			errorFlag = true;
		}

		if (user.getManOrWoman() == null) {
			request.setAttribute("manOrWomanError", "性別を選択してください");
			errorFlag = true;
		}

		if (!(mailCheck)) {
			request.setAttribute("mailError", "メールアドレスではありません");
			errorFlag = true;
		}

		if (!(passWordCheck)) {
			request.setAttribute("passWordError", "パスワードは半角英小文字と数字を組み合わせた8文字以上ものでおねがいします");
			errorFlag = true;
		}

		if (user.getId().isEmpty()) {
			request.setAttribute("idError", "IDを入力してください");
			errorFlag = true;
		}

	}

}
