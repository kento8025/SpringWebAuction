package jp.co.webAuction.controller.form;

public class TradeForm {

	private int contractPrice;
	private int userId;
	private int productId;

	public int getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(int contractPrice) {
		this.contractPrice = contractPrice;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
