package jp.co.webAuction.controller.form;

public class ProductForm {

	private int productId;
	private String productName; /*���i��*/
	private String productImg; /*���i�摜*/
	private int categoryId; /*�J�e�S��Id �O���L�[�Q��*/
	private String categoryName; /*�J�e�S���̖��O*/
	private String productStatus;/*���*/
	private String description;/*����*/
	private String postage;/*�������S*/
	private String shippingOrigin;/*������*/
	private String shipping_method;/*�z�����@*/
	private int exhibition_period;/*�o�i����*/
	private int price; /*���i*/

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getExhibition_period() {
		return exhibition_period;
	}

	public void setExhibition_period(int exhibition_period) {
		this.exhibition_period = exhibition_period;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
