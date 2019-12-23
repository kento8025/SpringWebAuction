package jp.co.webAuction.db.entity;

public interface AdminDao {

	public void delete(int id);

	public void hidden(int id);

	public void hiddenCancel(int id);

}
