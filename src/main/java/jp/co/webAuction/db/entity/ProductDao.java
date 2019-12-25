package jp.co.webAuction.db.entity;

import jp.co.webAuction.controller.form.ProductForm;
import jp.co.webAuction.db.dto.User;

public interface ProductDao {

	public void  register(ProductForm productForm , User user);

	public int categorySearch(String category) ;

}
