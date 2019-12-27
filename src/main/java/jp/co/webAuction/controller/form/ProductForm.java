package jp.co.webAuction.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductForm {

	private int id;/*�v���C�}���[�L�[���i�[*/

	@NotBlank
	private String productName; /*���i��*/

	private String productImg; /*���i�摜*/

	private int categoryId; /*�J�e�S��Id �O���L�[�Q��*/

	@NotBlank(message="�J�e�S����I�����Ă��������B")
	private String categoryName; /*�J�e�S���̖��O*/

	@NotBlank(message="���i�̏�Ԃ�I�����Ă��������B")
	private String productStatus;/*���*/

	@NotBlank
	private String description;/*����*/

	@NotBlank(message="�������S�҂�I�����Ă��������B")
	private String postage;/*�������S*/

	@NotBlank(message="��������I�����Ă��������B")
	private String shippingOrigin;/*������*/

	@NotBlank(message="�z�����@��I�����Ă��������B")
	private String shipping_method;/*�z�����@*/

	@NotNull
	private Integer exhibition_period;/*�o�i����*/

	@NotNull
	private Integer price; /*���i*/

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
