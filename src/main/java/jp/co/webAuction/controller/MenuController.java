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
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.TradeDao;

@Controller
public class MenuController {

	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private FavoriteDao favoriteDao;

	private String errorMessage = "ログインしないとこの機能は使えません";

	/*メニュー画面 {ユーザ情報編集}*/
	@RequestMapping("/userInformation")
	public String userInformation(@ModelAttribute("product") UserForm userForm, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		if (user == null) {

			List<Category> categoryList = menuDao.categorySearch();
			favoriteDao.favoriteSearch(user, request);

			model.addAttribute("categoryList", categoryList);
			model.addAttribute("product", new ProductForm());
			model.addAttribute("user", new UserForm());
			request.setAttribute("notLoginError", errorMessage);

			return "home/homePage";
		}

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

	/*メニュー画面 {落札中　落札履歴　出品中　出品履歴}*/
	@RequestMapping("/menuSearch")
	public String menuSearch(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request,
			@RequestParam("menuCommand") String menuCommand) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		if (user == null) {

			List<Category> categoryList = menuDao.categorySearch();
			favoriteDao.favoriteSearch(user, request);

			model.addAttribute("categoryList", categoryList);
			model.addAttribute("product", new ProductForm());
			model.addAttribute("user", new UserForm());
			request.setAttribute("notLoginError", errorMessage);

			return "home/homePage";
		}
		if (menuCommand.equals("successfulDid")) {

			request.setAttribute("mode", "successfulDid");

		}

		List<Product> productList = menuDao.menuSearch(user.getId(), menuCommand);
		model.addAttribute("productList", productList);
		return "menu/menu";
	}

	/*出品した商品の取り消し*/
	@RequestMapping(value = "/trade", params = "productCancel", method = RequestMethod.POST)
	public String productCancel(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {

		tradeDao.productHidden(tradeForm.getProductId());

		return "menu/cancel/productCancel";

	}

	/*商品購入取り消し*/
	@RequestMapping(value = "/trade", params = "successFulDidCancel", method = RequestMethod.POST)
	public String successFulDidCancel(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {

		tradeDao.tradeCancel(tradeForm.getTradeId());

		return "menu/cancel/successFulDidCancel";
	}

	/*出品した商品の即売り*/
	@RequestMapping(value = "/trade", params = "promptDecision", method = RequestMethod.POST)
	public String promptDecision(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {

		tradeDao.promptDecision(tradeForm.getProductId());

		return "menu/cancel/productCancel";

	}

}
