
package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.SearchDao;

@Controller
public class SearchController {

	@Autowired
	private SearchDao searchDao;

	@Autowired
	private FavoriteDao favoriteDao;

	@Autowired
	private MenuDao menuDao;

	private String errorMessage = "ログインしないとこの機能は使えません";

	@RequestMapping(value = "/searchResult", method = RequestMethod.GET)
	public String searchResult(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request,
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "priceBetween", required = false) String priceBetweenCommand,
			@RequestParam(name = "productStatus", required = false) String productStatus,
			@RequestParam(name = "category", required = false) String category) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		List<Category> categoryList = menuDao.categorySearch();
		List<Product> productList = searchDao.productSearch(productName, category, priceBetweenCommand,
				productStatus);

		favoriteDao.favoriteSearch(user, request);

		request.setAttribute("priceBetweenResult", priceBetweenCommand);
		request.setAttribute("productStatusResult", productStatus);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("product", productForm);
		model.addAttribute("productList", productList);
		return "searchResult/searchResult";

	}

	@RequestMapping(value = "/searchResult", params = "favoriteRegister", method = RequestMethod.GET)
	public String favoriteRegister(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request,
			@RequestParam(name = "favoriteName", required = false) String favoriteName,
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "favoriteName", required = false) String userId,
			@RequestParam(name = "registrNumber", required = false) Integer registrNumber,
			@RequestParam(name = "priceBetweenResult", required = false) String priceBetweenCommand,
			@RequestParam(name = "productStatusResult", required = false) String productStatus,
			@RequestParam(name = "category", required = false) String category) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		List<Category> categoryList = menuDao.categorySearch();
		List<Product> productList = searchDao.productSearch(productForm.getProductName(), category, priceBetweenCommand,
				productStatus);

		favoriteDao.favoriteSearch(user, request);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("product", productForm);
		model.addAttribute("productList", productList);

		if (user == null) {

			request.setAttribute("notLoginError", errorMessage);
			return "searchResult/searchResult";
		}

		favoriteDao.favoriteRegisterOrUpdate(user.getId(), registrNumber, favoriteName,
				favoriteUrlCreate(productName, priceBetweenCommand, productStatus, category));

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

	private String favoriteUrlCreate(String productName, String priceBetweenCommand, String productStatus,
			String category) {

		String searchUrl = "/searchResult?1=1";

		if (!(productName == null)) {
			searchUrl += "&productName=" + productName;
		}

		if (!(priceBetweenCommand == null)) {
			searchUrl += "&priceBetweenCommand=" + priceBetweenCommand;
		}
		if (!(productStatus == null)) {
			searchUrl += "&productStatus=" + productStatus;
		}
		if (!(category == null)) {
			searchUrl += "&category=" + category;
		}

		return searchUrl;

	}

}
