package jp.co.webAuction.db.entity;

import java.util.List;

import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;

public interface SearchDao {

	public List<Product> productSearch(String productName);

	public PurchaseDisplay productInformation(int productId);

}
