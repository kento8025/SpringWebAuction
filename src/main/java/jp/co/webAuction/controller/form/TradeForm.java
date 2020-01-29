package jp.co.webAuction.controller.form;

import javax.validation.constraints.NotNull;

public class TradeForm {

	@NotNull
	private Integer contractPrice;

	private int price;
	private int userId;
	private int productId;
	private int tradeId;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Integer getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(Integer contractPrice) {
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

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

}
