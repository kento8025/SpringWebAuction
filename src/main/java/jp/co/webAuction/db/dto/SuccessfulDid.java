package jp.co.webAuction.db.dto;

import java.util.Date;

public class SuccessfulDid {

	private int id;
	private int product_id;
	private int user_id;
	private int contract_price;
	private Date trade_dete;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getContract_price() {
		return contract_price;
	}

	public void setContract_price(int contract_price) {
		this.contract_price = contract_price;
	}

	public Date getTrade_dete() {
		return trade_dete;
	}

	public void setTrade_dete(Date trade_dete) {
		this.trade_dete = trade_dete;
	}

	@Override
	public String toString() {
		return "SuccessfulDid [id=" + id + ", product_id=" + product_id + ", user_id=" + user_id + ", contract_price="
				+ contract_price + ", trade_dete=" + trade_dete + "]";
	}

}
