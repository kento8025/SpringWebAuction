package jp.co.webAuction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.TradeForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.TradeDao;

@Controller
public class WebController {

	@Autowired
	TradeDao tradeDao;

	@Autowired
	MenuDao menuDao;

	@RequestMapping("/homePage")
	public String homeIndex(@ModelAttribute("product") ProductForm productForm, Model model) {
		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList" , categoryList);

		return "home/homePage";
	}

	/*è§ïiçwì¸èàóù*/
	@RequestMapping("/SuccessfulDid")
	public String SuccessfulDid(@ModelAttribute("tradeForm") TradeForm tradeForm, Model model) {
		tradeDao.register(tradeForm);
		return "product/bid/successfulDid";
	}

}
