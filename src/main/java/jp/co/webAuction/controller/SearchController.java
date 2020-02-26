
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
			@RequestParam(name = "priceBetween", required = false) String priceBetween,
			@RequestParam(name = "productStatus", required = false) String productStatus,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "limt", required = false) Integer limt) {

		if (limt == null) {
			limt = 1;
		}

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		List<Category> categoryList = menuDao.categorySearch();
		List<Product> productList = searchDao.productSearch(productName, category, priceBetween,
				productStatus, limt);

		List<Product> productListCount = searchDao.productSearch(productName, category, priceBetween,
				productStatus, null);

		request.setAttribute("url", urlCreate(productName, priceBetween, productStatus, category));
		request.setAttribute("pageNo", limt);

		favoriteDao.favoriteSearch(user, request);

		priceBetweenChecked(priceBetween, request);
		productStatusChecked(productStatus, request);

		request.setAttribute("priceBetweenResult", priceBetween);
		request.setAttribute("productStatusResult", productStatus);
		request.setAttribute("listnumber", productListCount.size());
		request.setAttribute("productName", productForm.getProductName());

		if (productListCount.size() < 4) {
			request.setAttribute("pageadd", 0);
		} else if (productListCount.size() % 4 == 0) {
			request.setAttribute("pageadd", 0);
		} else {
			request.setAttribute("pageadd", 1);
		}

		model.addAttribute("productList", productList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("product", productForm);

		return "searchResult/searchResult";

	}

	@RequestMapping(value = "/searchResult", params = "favoriteRegister", method = RequestMethod.GET)
	public String favoriteRegister(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request,
			@RequestParam(name = "favoriteName", required = false) String favoriteName,
			@RequestParam(name = "registrNumber", required = false) Integer registrNumber,
			@RequestParam(name = "productName", required = false) String productName,
			@RequestParam(name = "priceBetweenResult", required = false) String priceBetween,
			@RequestParam(name = "productStatusResult", required = false) String productStatus,
			@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "limt", required = false) Integer limt) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		List<Category> categoryList = menuDao.categorySearch();
		List<Product> productList = searchDao.productSearch(productName, category, priceBetween,
				productStatus, limt);
		List<Product> productListCount = searchDao.productSearch(productName, category, priceBetween,
				productStatus, null);

		model.addAttribute("categoryList", categoryList);
		model.addAttribute("product", productForm);
		model.addAttribute("productList", productList);

		favoriteDao.favoriteSearch(user, request);

		if (user == null) {
			request.setAttribute("notLoginError", errorMessage);
			return "searchResult/searchResult";
		}

		if (favoriteName.isEmpty()) {
			request.setAttribute("favoriteNameError", "登録するお気に入り名を入力してください");
			return "searchResult/searchResult";

		} else if (favoriteName.length() > 8) {
			request.setAttribute("favoriteNameError", "名前が長すぎます８文字以下にしてください");
			return "searchResult/searchResult";
		}

		favoriteDao.favoriteRegisterOrUpdate(user.getId(), registrNumber, favoriteName,
				urlCreate(productName, priceBetween, productStatus, category));

		request.setAttribute("registrationSuccessful", "登録に成功しました！");
		request.setAttribute("listnumber", productListCount.size());
		request.setAttribute("productName", productForm.getProductName());
		request.setAttribute("url", urlCreate(productName, priceBetween, productStatus, category));
		request.setAttribute("pageNo", limt);

		favoriteDao.favoriteSearch(user, request);

		return "searchResult/searchResult";

	}

	@RequestMapping(value = "/ExhibitPurchase", method = RequestMethod.GET)
	public String exhibitPurchase(@ModelAttribute("product") ProductForm productForm, Model model,
			@RequestParam("id") int productId, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		PurchaseDisplay purchaseDisplay = searchDao.productInformation(productId);

		//userがログインしていなかった場合は落札ボタンを表示させる。
		if (user == null) {
			purchaseDisplay.setSeller(0);
			purchaseDisplay.setBuyer(0);
		}
		model.addAttribute("purchaseDisplay", purchaseDisplay);
		model.addAttribute("tradeForm", new TradeForm());

		return "product/bid/exhibitPurchase";

	}

	private String urlCreate(String productName, String priceBetween, String productStatus,
			String category) {

		String searchUrl = "/searchResult?1=1";

		if (!(productName == null) && !(productName.isEmpty())) {
			searchUrl += "&productName=" + productName;
		}

		if (!(priceBetween == null) && !(priceBetween.isEmpty())) {
			searchUrl += "&priceBetween=" + priceBetween;
		}
		if (!(productStatus == null) && !(productStatus.isEmpty())) {
			searchUrl += "&productStatus=" + productStatus;
		}
		if (!(category == null) && !(category.isEmpty())) {
			searchUrl += "&category=" + category;
		}

		return searchUrl;

	}

	private void priceBetweenChecked(String priceBetween, HttpServletRequest request) {

		if (priceBetween == null || priceBetween.equals("none")) {

			return;
		}

		switch (priceBetween) {

		case "1":
			request.setAttribute("priceBetweenChecked1", "Checked");
			break;

		case "2":
			request.setAttribute("priceBetweenChecked2", "Checked");
			break;

		case "3":
			request.setAttribute("priceBetweenChecked3", "Checked");
			break;

		case "4":
			request.setAttribute("priceBetweenChecked4", "Checked");
			break;
		}

	}

	private void productStatusChecked(String productStatus, HttpServletRequest request) {

		if (productStatus == null || productStatus.equals("none")) {

			return;
		}

		switch (productStatus) {
		case "新品":
			request.setAttribute("productStatusChecked1", "Checked");
			break;

		case "中古":
			request.setAttribute("productStatusChecked2", "Checked");
			break;

		}
	}

}
