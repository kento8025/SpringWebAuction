package jp.co.webAuction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.entity.SearchDao;

@Controller
public class webController {

	@Autowired
	private SearchDao searchDao;


	@RequestMapping("/homePage")
	public String homeIndex(@ModelAttribute("product") ProductForm productForm, Model model) {
		System.out.println("’Ê‰ß1");
		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		return "home/homePage";
	}

	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchIndex(@ModelAttribute("product") ProductForm productForm, Model model) {

		List<Product> productList = searchDao.productSearch(productForm.getProductName());
		model.addAttribute("productList", productList);

		return "searchResult/searchResult";

	}




}
