package jp.co.webAuction.db.entity;

import java.util.List;

import jp.co.webAuction.db.dto.Category;
import jp.co.webAuction.db.dto.Product;


public interface MenuDao {

	public List<Product> productSearch(int userId);

	public List<Category> categorySearch();

}
