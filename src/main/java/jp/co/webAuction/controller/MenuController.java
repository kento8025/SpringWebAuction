package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.SearchDao;
import jp.co.webAuction.db.entity.TradeDao;
import jp.co.webAuction.tool.CheckDate;

@Controller
public class MenuController {

	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private SearchDao searchDao;

	@Autowired
	private FavoriteDao favoriteDao;

	private String errorMessage = "���O�C�����Ȃ��Ƃ��̋@�\�͎g���܂���";

	/*���j���[��� {���[�U���ҏW}*/
	@RequestMapping("/userInformation")
	public String userInformation(Model model, HttpServletRequest request) {

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
	public String edit(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "menu/user/userEdit";
	}

	@RequestMapping("/userUpdate")
	public String userUpdate(@Validated @ModelAttribute("user") UserForm userForm, BindingResult bindingResult,
			Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		model.addAttribute("user", userForm);

		if (!(CheckDate.checkDate(userForm.getYear() + "/" + userForm.getMonth() + "/" + userForm.getDay()))) {
			request.setAttribute("birthdayError", "���N�������s���ł�");
			return "menu/user/userEdit";

		}

		if (bindingResult.hasErrors()) {
			return "menu/user/userEdit";
		}

		menuDao.userUpdate(userForm, user.getId());
		return "menu/user/userUpdateDone";
	}

	/*���j���[��� {���D���@���D�����@�o�i���@�o�i����}*/
	@RequestMapping("/menuSearch")
	public String menuSearch(Model model, HttpServletRequest request, @RequestParam("menuCommand") String menuCommand) {

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

	/*���i�w������*/
	@RequestMapping(value = "/trade", params = "SuccessfulDid", method = RequestMethod.POST)
	public String successfulDid(@Validated @ModelAttribute("tradeForm") TradeForm tradeForm,
			BindingResult bindingResult, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		PurchaseDisplay purchaseDisplay = searchDao.productInformation(tradeForm.getProductId());
		model.addAttribute("purchaseDisplay", purchaseDisplay);
		model.addAttribute("tradeForm", tradeForm);

		if (user == null) {
			purchaseDisplay.setSeller(0);
			purchaseDisplay.setBuyer(0);
			request.setAttribute("notLoginError", errorMessage);
			return "product/bid/exhibitPurchase";

		}

		if (bindingResult.hasErrors()) {
			purchaseDisplay.setSeller(0);
			purchaseDisplay.setBuyer(0);
			return "product/bid/exhibitPurchase";
		}

		if (tradeForm.getPrice() > tradeForm.getContractPrice()) {
			request.setAttribute("priceError", "�o�i���̒l�i���Ⴂ���i�ł͗��D�ł��܂���");
			return "product/bid/exhibitPurchase";
		} else {
			tradeDao.register(tradeForm);
			return "product/bid/successfulDid";
		}

	}

	/*�o�i�������i�̎�����*/
	@RequestMapping(value = "/trade", params = "productCancel", method = RequestMethod.POST)
	public String productCancel(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {

		tradeDao.productHidden(tradeForm.getProductId());

		return "menu/cancel/productCancel";

	}

	/*���i�w��������*/
	@RequestMapping(value = "/trade", params = "successFulDidCancel", method = RequestMethod.POST)
	public String successFulDidCancel(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {

		tradeDao.tradeCancel(tradeForm.getTradeId());

		return "menu/cancel/successFulDidCancel";
	}

	/*�o�i�������i�̑�����*/
	@RequestMapping(value = "/trade", params = "promptDecision", method = RequestMethod.POST)
	public String promptDecision(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {

		tradeDao.promptDecision(tradeForm.getProductId());

		return "menu/cancel/productCancel";

	}

}
