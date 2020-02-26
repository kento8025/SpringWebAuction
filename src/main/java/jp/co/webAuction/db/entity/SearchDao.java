package jp.co.webAuction.db.entity;

import java.util.List;

import jp.co.webAuction.db.dto.Product;
import jp.co.webAuction.db.dto.PurchaseDisplay;
import jp.co.webAuction.db.dto.SuccessfulDidCount;

public interface SearchDao {

	public List<Product> productSearch(String productName, String category, String priceBetween, String productStatus,
			Integer limt);

	public PurchaseDisplay productInformation(int productId);

	public List<SuccessfulDidCount> successfulDidCountSearch(String productName, String category,
			String priceBetweenCommand,
			String productStatus,
			Integer limt);

}
