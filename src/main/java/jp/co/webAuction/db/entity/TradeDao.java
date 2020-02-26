package jp.co.webAuction.db.entity;

import jp.co.webAuction.controller.form.TradeForm;

public interface TradeDao {

	public void register(TradeForm tradeForm);

	public void productHidden(int productId);

	public void tradeCancel(int tradeId);

	public void promptDecision(Integer productId);

	public void productListUpdate();

}
