package jp.co.webAuction.tool;

import java.text.DateFormat;

public class CheckDate {

	public static boolean checkDate(String strDate) {

	    strDate = strDate.replace('-', '/');
	    DateFormat format = DateFormat.getDateInstance();
	    // ���t/������͂������ɍs�����ǂ�����ݒ肷��B
	    format.setLenient(false);
	    try {
	        format.parse(strDate);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}

}
