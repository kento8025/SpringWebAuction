package jp.co.webAuction.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.db.dto.User;
import jp.co.webAuction.db.entity.ProductDao;

@Controller
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/ProductRegister")
	public String ProductRegister(@ModelAttribute("product") ProductForm productForm, Model model) {
		System.out.println("�o�i����");
		model.addAttribute("product", new ProductForm());
		return "Product/Exhibit/ProductRegister";
	}

	@RequestMapping(value = "/ContentConfirmation", method = RequestMethod.POST)
	public String ContentConfirmation(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request) throws IOException, ServletException {

		System.out.println("�o�^���");

		model.addAttribute("product", productForm);

		//��΃p�X�̎w��
		final String ABSOLUTE_PATH = new File("").getAbsoluteFile().getPath()+"\\src\\main\\webapp\\WebContent\\ProductImg";

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		System.out.println(user.getId());

		//�t�H���_�[�����邩�ǂ����m�F�B
		//�Ȃ���΍쐬����B

		File folder = new File(ABSOLUTE_PATH + "/" + user.getId());

		if (folder.exists()) {

			System.out.println("�t�H���_������܂���");

		} else {

			System.out.println("�t�H���_�����݂��܂���");

			// ��΃p�X�ō쐬
			File file1 = new File(ABSOLUTE_PATH + "/" + user.getId());

			if (file1.mkdir()) {
				System.out.println("�t�H���_�̍쐬�ɐ������܂���");
			} else {
				System.out.println("�t�H���_�̍쐬�Ɏ��s���܂���");
			}
		}

		Part part = request.getPart("file");
		String name = this.getFileName(part);
		part.write(ABSOLUTE_PATH + "/" + user.getId() + "/" + name);

		productForm.setProductImg("\\WebContent\\ProductImg"+"/" + user.getId() + "/" + name);

		System.out.println(productForm.getProductImg());

		return "Product/Exhibit/ContentConfirmation";

	}

	@RequestMapping(value = "/RistingCompleted", method = RequestMethod.POST)
	public String RistingCompleted(@ModelAttribute("product") ProductForm productForm, Model model,
			HttpServletRequest request) {
		System.out.println("���i�m�F���");

		model.addAttribute("product", productForm);

		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		session.setAttribute("product", productForm);

		productDao.register(productForm, user);

		return "Product/Exhibit/RistingCompleted";
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
