package com.donbala.util;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MD5加密
 * 
 * @author zhanDan
 * @date 2016-7-1
 */
public class MD5Util {
	
	public static void main(String[] args) {
	}
	/**
	 *  采用MD5加密
	 * 
	 * @author zhanDan
	 * @date 2016-7-1
	 * @param inStr
	 * @return String
	 * @throws Exception
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	public static long dateDiff(String startTime, String endTime, String format) throws ParseException {
		//按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
		long min = diff%nd%nh/nm;//计算差多少分钟
		 return Math.abs(min);
	}
	public static String makedate(){
		 Date currentTime = new Date();
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	     String dateString = formatter.format(currentTime);		
		return dateString;
	}
}
