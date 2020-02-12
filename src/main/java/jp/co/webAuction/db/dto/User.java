package jp.co.webAuction.db.dto;

import java.sql.Date;
import java.util.Calendar;

public class User {

	private int id;
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

	Calendar cal = Calendar.getInstance();

	public String getYear2() {
		this.cal.setTime(getBirthdayDate());
		return year = Integer.toString(cal.get(Calendar.YEAR));
	}

	public String getMonth2() {
		this.cal.setTime(getBirthdayDate());
		return month = Integer.toString((cal.get(Calendar.MARCH) + 1));
	}

	public String getDay2() {
		this.cal.setTime(getBirthdayDate());
		return day = Integer.toString(cal.get(Calendar.DATE));
	}

	public Date getBirthday() {

		Date birthday = Date.valueOf(year + "-" + month + "-" + day);

		return birthday;
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

	public Date getBirthdayDate() {
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
