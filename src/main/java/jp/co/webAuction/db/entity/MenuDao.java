package jp.co.webAuction.db.entity;

import java.util.List;

import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;

public interface MenuDao {

	public void userUpdate(UserForm user, int id);

	public List<Product> menuSearch(int userId , String menuCommand);

	public List<Category> categorySearch();

}
