package jp.co.webAuction.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.entity.UserDao;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/loginCheck", params = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserForm userForm, Model model, HttpServletRequest request) {

		System.out.println("ログイン");

		model.addAttribute("product", new ProductForm());

		HttpSession session = request.getSession(true);

		if (userDao.loginCheck(userForm.getUserId(), userForm.getPassWord())) {
			session.setAttribute("user", userDao.getUser());
			return "home/homePage";
		} else {
			return "login/loginError";
		}

	}

	@RequestMapping(value = "/loginCheck", params = "logout", method = RequestMethod.POST)
	public String logout(@ModelAttribute("user") UserForm userForm, Model model, HttpServletRequest request) {

		System.out.println("ログアウト");

		model.addAttribute("product", new ProductForm());
		HttpSession session = request.getSession(true);
		session.removeAttribute("user");

		return "home/homePage";

	}

	@RequestMapping(value = "/loginCheck", params = "UserRegister", method = RequestMethod.POST)
	public String UserRegister(@ModelAttribute("user") UserForm userForm, Model model) {

		System.out.println("新規登録");

		model.addAttribute("user", new UserForm());

		return "login/UserRegister";

	}

	@RequestMapping(value = "/UserConfirmation", method = RequestMethod.POST)
	public String UserConfirmation(@ModelAttribute("user") UserForm userForm, Model model) {

		System.out.println("確認画面");

		model.addAttribute("user", userForm);

		return "login/UserConfirmation";

	}


	@RequestMapping(value = "/registration", params = "edit", method = RequestMethod.POST)
	public String Edit(@ModelAttribute("user") UserForm userForm, Model model) {

		System.out.println("編集画面");

		model.addAttribute("user", userForm);

		return "login/UserRegister";

	}


	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("user") UserForm userForm, Model model) {

		System.out.println("登録完了画面");
		userDao.register(userForm);

		return "login/registration";

	}

}
