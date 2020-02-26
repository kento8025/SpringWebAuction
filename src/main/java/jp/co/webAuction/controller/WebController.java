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

	@RequestMapping("/error") // �G���[�y�[�W�ւ̃}�b�s���O
	public ModelAndView error(HttpServletRequest req, ModelAndView mav) {

		// �ǂ̃G���[�ł� 404 Not Found �ɂ���
		// �K�v�ɉ����ăX�e�[�^�R�[�h��o�͓��e���J�X�^�}�C�Y�\

		//�G���[�̎w��
		mav.setStatus(HttpStatus.NOT_FOUND);

		// �r���[�����w�肷��
		mav.setViewName("error");

		req.setAttribute("errorMessage", "�w�肳�ꂽ�t�@�C����������܂���<br>"
				+ "URL���m�F���Ă�������");

		return mav;
	}


}
