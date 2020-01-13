package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.UserDao;
import jp.co.webAuction.tool.CheckDate;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MenuDao menuDao;

	@RequestMapping(value = "/loginCheck", params = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserForm userForm, Model model, HttpServletRequest request) {

		System.out.println("ログイン");

		model.addAttribute("product", new ProductForm());

		HttpSession session = request.getSession(true);

		if (userDao.loginCheck(userForm.getUserId(), userForm.getPassWord())) {
			session.setAttribute("user", userDao.getUser());
			List<Category> categoryList = menuDao.categorySearch();
			model.addAttribute("categoryList" , categoryList);
			return "home/homePage";
		} else {
			request.setAttribute("loginError", "IDまたはPASSWORDが違います");
			return "home/homePage";
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
	public String UserRegister(@ModelAttribute("user") UserForm userForm,
			Model model) {

		System.out.println("新規登録");
		model.addAttribute("user", userForm);

		return "login/userRegister";

	}

	@RequestMapping(value = "/UserConfirmation", method = RequestMethod.POST)
	public String UserConfirmation(@Validated @ModelAttribute("user") UserForm userForm, BindingResult bindingResult,
			Model model , HttpServletRequest request) {

		System.out.println("確認画面");

		if (bindingResult.hasErrors()) {

			if(!(CheckDate.checkDate(userForm.getYear() + "/" + userForm.getMonth() + "/" + userForm.getDay()))) {
				request.setAttribute("birthdayError", "生年月日が不正です");
			}

			return "login/UserRegister";
		}
		return "login/userConfirmation";
	}

	@RequestMapping(value = "/registration", params = "edit", method = RequestMethod.POST)
	public String Edit(@ModelAttribute("user") UserForm userForm, Model model) {

		System.out.println("編集画面");

		model.addAttribute("user", userForm);

		return "login/userRegister";

	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("user") UserForm userForm, Model model) {

		System.out.println("登録完了画面");
		userDao.register(userForm);

		return "login/registration";

	}

}
