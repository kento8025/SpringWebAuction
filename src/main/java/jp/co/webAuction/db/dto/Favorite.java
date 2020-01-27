package jp.co.webAuction.db.dto;

public class Favorite {

	private int id;
	private int userId;
	private Integer RegistrNumber;
	private String favoriteName = "–¢“o˜^";
	private String favoriteUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getRegistrNumber() {
		return RegistrNumber;
	}

	public void setRegistrNumber(Integer registrNumber) {
		RegistrNumber = registrNumber;
	}

	public String getFavoriteName() {
		return favoriteName;
	}

	public void setFavoriteName(String favoriteName) {
		this.favoriteName = favoriteName;
	}

	public String getFavoriteUrl() {
		return favoriteUrl;
	}

	public void setFavoriteUrl(String favoriteUrl) {
		this.favoriteUrl = favoriteUrl;
	}

	@Override
	public String toString() {
		return "Favorite [id=" + id + ", userId=" + userId + ", RegistrNumber=" + RegistrNumber + ", favoriteName="
				+ favoriteName + ", favoriteUrl=" + favoriteUrl + "]";
	}

}
