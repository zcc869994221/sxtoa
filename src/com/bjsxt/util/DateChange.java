package com.bjsxt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateChange {
	private static java.util.Date newDate = null;
	private static java.sql.Date sdate = null;
	private static String str = null;
	private static SimpleDateFormat sdf = null;
	public static java.sql.Date getDate(java.util.Date date){
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		str = sdf.format(date);
		try {
			newDate = sdf.parse(str);
			sdate = new java.sql.Date((newDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return sdate;
	}
	
	public static java.sql.Date getDate2(java.util.Date date){
		sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		str = sdf.format(date);
		try {
			newDate = sdf.parse(str);
			sdate = new java.sql.Date(newDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return sdate;
	}
}
