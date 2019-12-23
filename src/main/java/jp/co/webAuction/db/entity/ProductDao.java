package jp.co.webAuction.db.entity;

import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.User;

public interface ProductDao {

	public void  register(Product product , User user);

	public int categorySearch(String category) ;

}
