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

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.SearchDao;
import jp.co.webAuction.db.entity.TradeDao;

@Controller
public class WebController {

	@Autowired
	private TradeDao tradeDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private SearchDao searchDao;

	@Autowired
	FavoriteDao favoriteDao;

	@RequestMapping(value = { "/homePage", "/index" })
	public String homeIndex(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		favoriteDao.favoriteSearch(user, request);

		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		return "home/homePage";
	}

	/*è§ïiçwì¸èàóù*/
	@RequestMapping(value = "/trade", params = "SuccessfulDid", method = RequestMethod.POST)
	public String SuccessfulDid(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model,
			HttpServletRequest request) {

		if (tradeForm.getPrice() > tradeForm.getContractPrice()) {
			request.setAttribute("priceError", "èoïiíÜÇÃílíiÇÊÇËí·Ç¢âøäiÇ≈ÇÕóééDÇ≈Ç´Ç‹ÇπÇÒ");
			PurchaseDisplay purchaseDisplay = searchDao.productInformation(tradeForm.getProductId());
			model.addAttribute("purchaseDisplay", purchaseDisplay);
			model.addAttribute("tradeForm", new TradeForm());

			return "product/bid/exhibitPurchase";
		} else {
			tradeDao.register(tradeForm);
			return "product/bid/successfulDid";
		}

	}

}
