package jp.co.webAuction.controller.form;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {

	private int id;//テーブルのプライマリーキーを格納

	@NotBlank
	private String userName;

	@NotBlank
	private String year;

	@NotBlank
	private String month;

	@NotBlank
	private String day;

	@NotBlank(message = "性別を選択してください。")
	private String manOrWoman;

	@Email(message = "メールアドレスが不正です。")
	@NotBlank
	private String mail;

	@NotBlank
	private String userId;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])[0-9a-z\\-]{8,16}$", message = "パスワードは半角英小文字と数字を組み合わせた8文字以上")
	private String passWord;

	private int userRank;
	private Date registrationDate;
	private Date birthday;

	public Date getBirthday() {

		Date date = Date.valueOf(year + "-" + month + "-" + day);

		return date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
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

	public void setUserId(String userid) {
		this.userId = userid;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getUserNo() {
		return id;
	}

	public void setUserNo(int userNo) {
		this.id = userNo;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getBirthdayonDate() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getUserRank() {
		return userRank;
	}

	public void setUserRank(int userRank) {
		this.userRank = userRank;
	}

}
