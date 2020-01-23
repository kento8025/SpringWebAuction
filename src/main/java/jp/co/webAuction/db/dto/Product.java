package jp.co.webAuction.db.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Product {

	private int id;
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

	private String userName;/*�o�i�������[�U�[*/
	private int count;/*���D��*/
	private int highestbidprice;

	private Date registration_dete;/*�o�^�������t*/

	private Calendar remainingTime = Calendar.getInstance();/*�c�莞��*/
	private SimpleDateFormat sdf = new SimpleDateFormat("MM��dd�� HH��mm��");

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

		if (description.length() > 40) {
			description = description.substring(0, 40) + "�c�c";
		}
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

	public int getId() {
		System.out.println(id);
		return id;
	}

	public void setId(int productId) {
		this.id = productId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getRegistration_dete() {
		return registration_dete;
	}

	public void setRegistration_dete(Date registration_dete) {
		this.registration_dete = registration_dete;
	}

	public String getRemainingTime() {

		remainingTime.setTime(getRegistration_dete());

		remainingTime.add(Calendar.DAY_OF_MONTH, getExhibition_period());

		return sdf.format(remainingTime.getTime());
	}

	public void setRemainingTime(Calendar remainingTime) {
		this.remainingTime = remainingTime;
	}

	public int getHighestbidprice() {
		return highestbidprice;
	}

	public void setHighestbidprice(int highestbidprice) {
		this.highestbidprice = highestbidprice;
	}

}
