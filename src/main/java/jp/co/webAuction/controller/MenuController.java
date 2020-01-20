package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.MenuDao;

@Controller
public class MenuController {

	@Autowired
	MenuDao menuDao;


	/*メニュー画面 {ユーザ情報編集}*/
	@RequestMapping("/userInformation")
	public String userInformation(@ModelAttribute("product") UserForm userForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "menu/user/userInformation";
	}

	@RequestMapping("/Edit")
	public String edit(@ModelAttribute("product") UserForm userForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "menu/user/userEdit";
	}

	@RequestMapping("/userUpdate")
	public String userUpdate(@ModelAttribute("product") UserForm userForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		menuDao.userUpdate(userForm, user.getId());
		return "menu/user/userUpdateDone";
	}


	/*メニュー画面 {落札中}*/
	@RequestMapping("/successfulDid")
	public String successfulDid(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Product> productList = menuDao.productSuccessfulDid(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";
	}

	/*メニュー画面 {落札履歴}*/
	@RequestMapping("/successfulDidHistory")
	public String successfulDidHistory(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Product> productList = menuDao.productSuccessfulDidHistory(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";
	}


	/*メニュー画面 {出品中}*/
	@RequestMapping("/exhibition")
	public String Exhibition(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Product> productList = menuDao.productExhibition(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";

	}

	/*メニュー画面 {出品履歴}*/
	@RequestMapping("/exhibitionHistory")
	public String ExhibitionHistory(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		List<Product> productList = menuDao.productExhibitionHistory(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";
	}





}
