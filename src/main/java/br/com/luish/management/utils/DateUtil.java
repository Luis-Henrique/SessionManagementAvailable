package br.com.luish.management.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	public static boolean equalsDate(Date date1, Date date2) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		if(sdf.format(date1).equals(sdf.format(date2)))
			return true;
		
		return false;
		
	}

	public static Date getDateNow() {
	
		Calendar c = Calendar.getInstance();
		return c.getTime();
		
	}

	public static Date onlyDate(Date date) {

		Calendar c = Calendar.getInstance();
		
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		
		return  c.getTime();
		
	}

	public static boolean isValidTime(long time) {
		
		if(System.currentTimeMillis() > time)
			return false;
		
		return true;
		
	}

}
