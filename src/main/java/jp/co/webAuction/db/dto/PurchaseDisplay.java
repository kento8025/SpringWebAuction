package jp.co.webAuction.db.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PurchaseDisplay {

	private int primaryProductId;
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

	private int primaryUserId;
	private String userName;
	private String year;
	private String month;
	private String day;
	private String manOrWoman;
	private String mail;
	private String userId;
	private String passWord;
	private int userRank;
	private Date registrationDate;
	private Date birthday;

	private Integer seller;//�o�i�҂�ID
	private Integer buyer;//�w���҂�ID

	private Integer trade;//���ID

	private int count;/*���D��*/
	private int highestbidprice;

	private Date registration_dete;/*�o�^�������t*/

	private Calendar remainingTime = Calendar.getInstance();/*�c�莞��*/
	private SimpleDateFormat sdf = new SimpleDateFormat("MM��dd�� HH��mm��");

	public PurchaseDisplay() {

	}

	public int getPrimaryProductId() {
		return primaryProductId;
	}

	public void setPrimaryProductId(int primaryProductId) {
		this.primaryProductId = primaryProductId;
	}

	public int getPrimaryUserId() {
		return primaryUserId;
	}

	public void setPrimaryUserId(int primaryUserId) {
		this.primaryUserId = primaryUserId;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public int getExhibition_period() {
		return exhibition_period;
	}

	public void setExhibition_period(int exhibition_period) {
		this.exhibition_period = exhibition_period;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getManOrWoman() {
		return manOrWoman;
	}

	public void setManOrWoman(String manOrWoman) {
		this.manOrWoman = manOrWoman;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getUserRank() {
		return userRank;
	}

	public void setUserRank(int userRank) {
		this.userRank = userRank;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSeller() {
		return seller;
	}

	public void setSeller(Integer seller) {
		this.seller = seller;
	}

	public Integer getBuyer() {
		return buyer;
	}

	public void setBuyer(Integer buyer) {
		this.buyer = buyer;
	}

	public Integer getTrade() {
		return trade;
	}

	public void setTrade(Integer trade) {
		this.trade = trade;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

		//System.out.println(sdf.format(remainingTime.getTime()));

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
