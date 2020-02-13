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
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.UserDao;
import jp.co.webAuction.tool.CheckDate;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private FavoriteDao favoriteDao;

	/*ログイン*/
	@RequestMapping(value = "/loginCheck", params = "login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") UserForm userForm, Model model, HttpServletRequest request) {

		model.addAttribute("product", new ProductForm());

		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		HttpSession session = request.getSession(true);

		if (userDao.loginCheck(userForm.getUserId(), userForm.getPassWord())) {
			session.setAttribute("user", userDao.getUser());
			User user = (User) session.getAttribute("user");
			favoriteDao.favoriteSearch(user, request);

			return "home/homePage";

		} else {
			request.setAttribute("loginError", "IDまたはPASSWORDが違います");
			User user = (User) session.getAttribute("user");
			favoriteDao.favoriteSearch(user, request);

			return "home/homePage";

		}
	}

	/*ログアウト*/
	@RequestMapping(value = "/loginCheck", params = "logout", method = RequestMethod.POST)
	public String logout(@ModelAttribute("user") UserForm userForm, Model model, HttpServletRequest request) {

		model.addAttribute("product", new ProductForm());
		HttpSession session = request.getSession(true);
		session.removeAttribute("user");

		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		User user = (User) session.getAttribute("user");
		favoriteDao.favoriteSearch(user, request);

		return "home/homePage";

	}

	/*新規登録*/
	@RequestMapping(value = "/loginCheck", params = "UserRegister", method = RequestMethod.POST)
	public String userRegister(@ModelAttribute("user") UserForm userForm,
			Model model) {

		model.addAttribute("user", userForm);

		return "login/userRegister";

	}

	/*確認画面*/
	@RequestMapping(value = "/UserConfirmation", method = RequestMethod.POST)
	public String userConfirmation(@Validated @ModelAttribute("user") UserForm userForm, BindingResult bindingResult,
			Model model, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {

			if (!(CheckDate.checkDate(userForm.getYear()+ "/" + userForm.getMonth() + "/" + userForm.getDay()))) {
				request.setAttribute("birthdayError", "生年月日が不正です");
			}

			return "login/userRegister";
		}
		return "login/userConfirmation";
	}

	/*編集画面*/
	@RequestMapping(value = "/registration", params = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("user") UserForm userForm, Model model) {

		model.addAttribute("user", userForm);

		return "login/userRegister";

	}

	/*登録完了画面*/
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("user") UserForm userForm, Model model) {

		userDao.register(userForm);

		return "login/registration";

	}

}
