package com.jingu.IOT.util;

import java.text.DecimalFormat;
import java.util.List;

public class StringUtils {
	public static boolean isStringEmpty(String str){
		return (str==null||str.trim().length()==0);
	}
	
	public static boolean isNotStringEmpty(String str){
		return (str!=null && str.trim().length()>0 && !str.equals("null")&&!"".equals(str));
	}
	public static boolean isListEmpty(List<?> list){
		return (list==null||list.isEmpty()||list.get(0)==null);
	}
	public static boolean isNotListEmpty(List<?> list){
		return (list!=null&&!list.isEmpty()&&list.get(0)!=null);
	}
	
	public static String trimKg(String str){
		return str.trim(); 
	}

	public static double DoubSpli(double querySpecialZsum) {
		// TODO Auto-generated method stub
		String pattern=".00";
		DecimalFormat df=new DecimalFormat(pattern); 
		double nums=Double.parseDouble(df.format(querySpecialZsum));
		return nums;
	}
}
