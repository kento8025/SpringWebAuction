package jp.co.webAuction.db.entity;

import javax.servlet.http.HttpServletRequest;

import jp.co.webAuction.db.dto.User;

public interface FavoriteDao {

	public void favoriteRegisterOrUpdate(int userId, int registrNumber, String favoriteName, String favoriteUrl);

	public void favoriteSearch(User user, HttpServletRequest request);

}
