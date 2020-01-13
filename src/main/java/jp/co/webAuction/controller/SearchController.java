
package jp.co.webAuction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.entity.SearchDao;

@Controller
public class SearchController {

	@Autowired
	private SearchDao searchDao;

	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchIndex(@ModelAttribute("product") ProductForm productForm, Model model) {

		List<Product> productList = searchDao.productSearch(productForm.getProductName());
		model.addAttribute("productList", productList);
		model.addAttribute("product", productForm);

		return "searchResult/searchResult";

	}

	@RequestMapping(value = "/ExhibitPurchase", method = RequestMethod.GET)
	public String ExhibitPurchase(@ModelAttribute("product") ProductForm productForm, Model model,
			@RequestParam("id") int productId) {

		PurchaseDisplay purchaseDisplay = searchDao.productInformation(productId);
		model.addAttribute("purchaseDisplay", purchaseDisplay);
		model.addAttribute("tradeForm", new TradeForm());

		return "product/bid/exhibitPurchase";


	}
}
