package jp.co.webAuction.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.ProductDao;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/ProductRegister")
	public String ProductRegister(@ModelAttribute("product") ProductForm productForm, Model model) {
		System.out.println("出品する");
		model.addAttribute("product", new ProductForm());
		return "home/homePage";
	}

	@RequestMapping("/ContentConfirmation")
	public String ContentConfirmation(@ModelAttribute("product") ProductForm productForm, Model model , HttpServletRequest request) {
		System.out.println("商品確認");
		model.addAttribute("product", new ProductForm());


		//絶対パスの獲得
		String ABSOLUTE_PATH = getAbsoluteFile().getRealPath("WEB-INF/productImg");

		  String cd = new File(".").getAbsoluteFile().getParent();

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		System.out.println(user.getId());

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

		Part part = request.getPart("file");
		String name = this.getFileName(part);
		part.write(ABSOLUTE_PATH + "/" + user.getId() + "/" + name);

		return "home/homePage";
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
