package jp.co.webAuction.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductForm {

	private int id;/*プライマリーキーを格納*/

	@NotBlank
	private String productName; /*商品名*/

	private String productImg; /*商品画像*/

	private int categoryId; /*カテゴリId 外部キー参照*/

	@NotBlank(message="カテゴリを選択してください。")
	private String categoryName; /*カテゴリの名前*/

	@NotBlank(message="商品の状態を選択してください。")
	private String productStatus;/*状態*/

	@NotBlank
	private String description;/*説明*/

	@NotBlank(message="送料負担者を選択してください。")
	private String postage;/*送料負担*/

	@NotBlank(message="発送元を選択してください。")
	private String shippingOrigin;/*発送元*/

	@NotBlank(message="配送方法を選択してください。")
	private String shipping_method;/*配送方法*/

	@NotNull
	private Integer exhibition_period;/*出品期間*/

	@NotNull
	private Integer price; /*価格*/

	public ProductForm() {

	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostage() {
		return postage;
	}

	public void setPostage(String postage) {
		this.postage = postage;
	}

	public String getShippingOrigin() {
		return shippingOrigin;
	}

	public void setShippingOrigin(String shippingOrigin) {
		this.shippingOrigin = shippingOrigin;
	}

	public String getShipping_method() {
		return shipping_method;
	}

	public void setShipping_method(String shipping_method) {
		this.shipping_method = shipping_method;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getExhibition_period() {
		return exhibition_period;
	}

	public void setExhibition_period(Integer exhibition_period) {
		this.exhibition_period = exhibition_period;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getId() {
		System.out.println(id);
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
