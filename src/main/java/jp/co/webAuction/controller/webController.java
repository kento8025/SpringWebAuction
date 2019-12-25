package jp.co.webAuction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.entity.TradeDao;

@Controller
public class webController {

	@Autowired
	TradeDao tradeDao;

	@RequestMapping("/homePage")
	public String homeIndex(@ModelAttribute("product") ProductForm productForm, Model model) {
		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		return "home/homePage";
	}

	/*è§ïiçwì¸èàóù*/
	@RequestMapping("/SuccessfulDid")
	public String SuccessfulDid(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {
		System.out.println("*******************");
		tradeDao.register(tradeForm);
		return "Product/bid/SuccessfulDid";
	}

}
