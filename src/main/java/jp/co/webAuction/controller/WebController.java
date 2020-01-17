package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping("/homePage")
	public String homeIndex(@ModelAttribute("product") ProductForm productForm, Model model) {
		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		return "home/homePage";
	}

	/*商品購入処理*/
	@RequestMapping(value = "/trade", params = "SuccessfulDid", method = RequestMethod.POST)
	public String SuccessfulDid(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model,
			HttpServletRequest request) {

		if (tradeForm.getPrice() > tradeForm.getContractPrice()) {
			request.setAttribute("priceError", "出品中の値段より低い価格では落札できません");
			PurchaseDisplay purchaseDisplay = searchDao.productInformation(tradeForm.getProductId());
			model.addAttribute("purchaseDisplay", purchaseDisplay);
			model.addAttribute("tradeForm", new TradeForm());

			return "product/bid/exhibitPurchase";
		} else {
			tradeDao.register(tradeForm);
			return "product/bid/successfulDid";
		}

	}

	/*出品した商品の即売り*/
	@RequestMapping(value = "/trade", params = "promptDecision", method = RequestMethod.POST)
	public String promptDecision(@ModelAttribute("tradeForm") TradeForm tradeForm,  Model model) {

		return "menu/cancel/productCancel";

	}


	/*出品した商品の取り消し*/
	@RequestMapping(value = "/trade", params = "productCancel", method = RequestMethod.POST)
	public String productCancel(@ModelAttribute("tradeForm") TradeForm tradeForm,  Model model) {

		tradeDao.productHidden(tradeForm.getProductId());

		return "menu/cancel/productCancel";

	}

	/*商品購入取り消し*/
	@RequestMapping(value = "/trade", params = "successFulDidCancel", method = RequestMethod.POST)
	public String successFulDidCancel(@ModelAttribute("tradeForm") TradeForm tradeForm,  Model model) {

		tradeDao.tradeCancel(tradeForm.getTradeId());

		return "menu/cancel/successFulDidCancel";
	}

}
