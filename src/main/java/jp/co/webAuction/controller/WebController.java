package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	private MenuDao menuDao;

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

}
