package jp.co.webAuction.controller.form;

public class FavoriteForm {

	private String search;
	private String category;
	private String priceBetweenCommand;
	private String productStatus;

	private int userId;
	private String favoriteName;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPriceBetweenCommand() {
		return priceBetweenCommand;
	}

	public void setPriceBetweenCommand(String priceBetweenCommand) {
		this.priceBetweenCommand = priceBetweenCommand;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFavoriteName() {
		return favoriteName;
	}

	public void setFavoriteName(String favoriteName) {
		this.favoriteName = favoriteName;
	}

}
