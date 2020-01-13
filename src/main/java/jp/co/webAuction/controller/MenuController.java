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

	/*���j���[��� {���[�U���ҏW}*/
	@RequestMapping("/userInformation")
	public String userInformation(@ModelAttribute("product") UserForm userForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "menu/userInformation";
	}

	/*���j���[��� {���D��}*/
	@RequestMapping("/successfulDid")
	public String successfulDid(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Product> productList = menuDao.productSuccessfulDid(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";
	}


	/*���j���[��� {�o�i��}*/
	@RequestMapping("/Exhibition")
	public String Exhibition(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Product> productList = menuDao.productExhibition(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";
	}


}
