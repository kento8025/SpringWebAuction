package jp.co.webAuction.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.TradeDao;

@Controller
public class WebController {

	@Autowired
	private MenuDao menuDao;

	@Autowired
	FavoriteDao favoriteDao;

	@Autowired
	private TradeDao tradeDao;

	@RequestMapping(value = { "/homePage", "/index", "/" })
	public String homeIndex(Model model,
			HttpServletRequest request) {

		tradeDao.productListUpdate();

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		favoriteDao.favoriteSearch(user, request);

		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		return "home/homePage";
	}


	@RequestMapping(value = { "/productListUpdate" })
	public String productListUpdate(Model model,
			HttpServletRequest request) {

		tradeDao.productListUpdate();

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		favoriteDao.favoriteSearch(user, request);

		model.addAttribute("product", new ProductForm());
		model.addAttribute("user", new UserForm());
		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		return "home/homePage";
	}

	@RequestMapping("/error") // エラーページへのマッピング
	public ModelAndView error(HttpServletRequest req, ModelAndView mav) {

		// どのエラーでも 404 Not Found にする
		// 必要に応じてステータコードや出力内容をカスタマイズ可能

		//エラーの指定
		mav.setStatus(HttpStatus.NOT_FOUND);

		// ビュー名を指定する
		mav.setViewName("error");

		req.setAttribute("errorMessage", "指定されたファイルが見つかりません<br>"
				+ "URLを確認してください");

		return mav;
	}


}
