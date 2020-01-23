
package jp.co.webAuction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.webAuction.controller.form.FavoriteForm;
import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.SearchDao;

@Controller
public class SearchController {

	@Autowired
	private SearchDao searchDao;

	@Autowired
	private MenuDao menuDao;

	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchResult(@ModelAttribute("product") ProductForm productForm, Model model,
			@RequestParam(name = "priceBetween", required = false) String priceBetweenCommand,
			@RequestParam(name = "productStatus", required = false) String productStatus,
			@RequestParam(name = "category", required = false) String category) {

		List<Category> categoryList = menuDao.categorySearch();
		List<Product> productList = searchDao.productSearch(productForm.getProductName(), category, priceBetweenCommand,
				productStatus);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("product", productForm);
		model.addAttribute("productList", productList);
		model.addAttribute("favorite", new FavoriteForm());
		return "searchResult/searchResult";

	}

	@RequestMapping(value = "/searchResult", params = "favoriteRegister", method = RequestMethod.GET)
	public String favoriteRegister(@ModelAttribute("product") ProductForm productForm, Model model,
			@RequestParam(name = "priceBetween", required = false) String priceBetweenCommand,
			@RequestParam(name = "productStatus", required = false) String productStatus,
			@RequestParam(name = "category", required = false) String category) {

		List<Category> categoryList = menuDao.categorySearch();
		List<Product> productList = searchDao.productSearch(productForm.getProductName(), category, priceBetweenCommand,
				productStatus);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("product", productForm);
		model.addAttribute("productList", productList);

		System.out.println("******************************");

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
