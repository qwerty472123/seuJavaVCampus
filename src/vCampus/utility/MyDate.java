package vCampus.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDate {
	
	public static Date nowOnDay(int day) {
		Date date=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE,day);
		return c.getTime();
	}
	
	public static Date dateOnDay(Date date,int day) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, day);
		return c.getTime();
	}
	
	public static long daysBetween(Date d1,Date d2) {
		long diff = d2.getTime() - d1.getTime();
	    long days = diff / (1000 * 60 * 60 * 24);
	    return days;
	}
	
	public static String toString(Date date,String format) {
		String ans=new SimpleDateFormat(format).format(date.getTime());
		return ans;
	}

}
