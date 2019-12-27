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
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.MenuDao;

@Controller
public class MenuController {

	@Autowired
	MenuDao menuDao;

	/*ÉÅÉjÉÖÅ[âÊñ  {óééDíÜ}*/
	@RequestMapping("/menu")
	public String menu(@ModelAttribute("product") ProductForm productForm, Model model, HttpServletRequest request) {


		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		List<Product> productList = menuDao.productSearch(user.getId());
		model.addAttribute("productList", productList);
		return "menu/menu";
	}

}
