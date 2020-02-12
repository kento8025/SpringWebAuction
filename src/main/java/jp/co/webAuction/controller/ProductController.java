package jp.co.webAuction.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.FavoriteDao;
import jp.co.webAuction.db.entity.MenuDao;
import jp.co.webAuction.db.entity.ProductDao;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private FavoriteDao favoriteDao;

	private String errorMessage = "ログインしないとこの機能は使えません";

	@RequestMapping("/ProductRegister")
	public String productRegister(ProductForm productForm, Model model, HttpServletRequest request) {
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

		model.addAttribute("product", new ProductForm());
		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);
		return "product/exhibit/productRegister";
	}

	@RequestMapping(value = "/ContentConfirmation", method = RequestMethod.POST)
	public String contentConfirmation(@Validated @ModelAttribute("product") ProductForm productForm,
			BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException, ServletException {

		List<Category> categoryList = menuDao.categorySearch();
		model.addAttribute("categoryList", categoryList);

		Part part = request.getPart("file");

		if (bindingResult.hasErrors()) {

			if (part.getSize() == 0) {
				request.setAttribute("imgError", "画像ファイルを添付してください");
			}

			return "product/exhibit/productRegister";

		}

		model.addAttribute("product", productForm);

		//絶対パスの指定
		final String ABSOLUTE_PATH = new File("").getAbsoluteFile().getPath()
				+ "\\src\\main\\webapp\\WebContent\\ProductImg";

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");

		//フォルダーがあるかどうか確認。
		//なければ作成する。

		File folder = new File(ABSOLUTE_PATH + "/" + user.getId());

		if (folder.exists()) {

			System.out.println("フォルダがありました");

		} else {

			System.out.println("フォルダが存在しません");

			// 絶対パスで作成
			File file1 = new File(ABSOLUTE_PATH + "/" + user.getId());

			if (file1.mkdir()) {
				System.out.println("フォルダの作成に成功しました");
			} else {
				System.out.println("フォルダの作成に失敗しました");
			}
		}

		String name = this.getFileName(part);
		part.write(ABSOLUTE_PATH + "/" + user.getId() + "/" + name);

		productForm.setProductImg("\\WebContent\\ProductImg" + "/" + user.getId() + "/" + name);

		return "product/exhibit/contentConfirmation";

	}

	@RequestMapping(value = "/RistingCompleted", method = RequestMethod.POST)
	public String ristingCompleted(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request,
			@RequestParam(name = "productImg", required = false) String productImg) {

		productForm.setProductImg(productImg);

		model.addAttribute("product", productForm);

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		session.setAttribute("product", productForm);

		productDao.register(productForm, user);

		return "product/exhibit/ristingCompleted";
	}

	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
				name = name.substring(name.lastIndexOf("\\") + 1);
				break;
			}
		}
		return name;
	}

}
