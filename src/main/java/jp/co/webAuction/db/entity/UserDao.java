package jp.co.webAuction.db.entity;

import jp.co.webAuction.controller.form.UserForm;
import jp.co.webAuction.db.dto.User;

public interface UserDao {

	public void register(UserForm userfom);

	public boolean userIdCheck(String userId);

	public boolean loginCheck(String userId, String password);

	public User getUser();


}
