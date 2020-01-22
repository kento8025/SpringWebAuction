package jp.co.webAuction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.entity.AdminDao;
import jp.co.webAuction.db.entity.SearchDao;

@Controller
public class AdminController {

	@Autowired
	AdminDao adminDao;

	@Autowired
	SearchDao searchDao;

	@RequestMapping(value = "/searchAdminProduct", params = "delete")
	public String searchAdminProductDelete(@ModelAttribute("tradeForm") TradeForm tradeForm,
			@ModelAttribute("product") ProductForm productForm, Model model) {

		adminDao.delete(tradeForm.getProductId());
		List<Product> productList = searchDao.productSearch(productForm.getProductName(), null, null, null);
		model.addAttribute("productList", productList);
		model.addAttribute("product", productForm);
		return "searchResult/searchResult";

	}

	@RequestMapping(value = "/searchAdminProduct", params = "hidden")
	public String searchAdminProductHidden(@ModelAttribute("tradeForm") TradeForm tradeForm,
			@ModelAttribute("product") ProductForm productForm, Model model) {

		List<Product> productList = searchDao.productSearch(productForm.getProductName(), null, null, null);
		model.addAttribute("productList", productList);
		model.addAttribute("product", productForm);
		adminDao.hidden(tradeForm.getProductId());
		return "searchResult/searchResult";

	}

	/*ä«óùé“âÊñ */

	@RequestMapping(value = "/AdminProduct", params = "delete")
	public String adminProducDelete(@ModelAttribute("product") ProductForm productForm, Model model) {
		adminDao.delete(productForm.getId());
		return "admin/admin";

	}

	@RequestMapping(value = "/AdminProduct", params = "hidden")
	public String adminProductHidden(@ModelAttribute("product") ProductForm productForm, Model model) {
		adminDao.hidden(productForm.getId());
		return "admin/admin";

	}

	@RequestMapping(value = "/AdminProduct", params = "hiddenCancel")
	public String adminProductHiddenCancel(@ModelAttribute("product") ProductForm productForm, Model model) {
		adminDao.hiddenCancel(productForm.getId());
		return "admin/admin";

	}

}
