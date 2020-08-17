package com.donbala.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

public  class DateUtil {
	public static String getSysDate() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return sdfm.format(date);
	}
	
	public static String getSysTime() {
		SimpleDateFormat sdfm = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return sdfm.format(date);
	}
	
	public static String getSysOnlyDate() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return sdfm.format(date);
	}
	
	public static String getYesterdayDate() {
		SimpleDateFormat sdfm = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
		return sdfm.format(calendar.getTime());
	}
	
	public static void main(String[] args) {
		System.out.println(getSysDate());
		System.out.println(getSysTime());
		System.out.println(getSysOnlyDate());
		System.out.println(getYesterdayDate());
	}
	
	/**
	 * 获取本地IP地址，支持Windows和Linux系统
	 * @return
	 */
	public static String getLocalHostIP() {
		try {
	        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
	        while (networkInterfaces.hasMoreElements()) {
	            NetworkInterface ni = (NetworkInterface) networkInterfaces.nextElement();
	            Enumeration<InetAddress> nias = ni.getInetAddresses();
	            while (nias.hasMoreElements()) {
	                InetAddress ia = (InetAddress) nias.nextElement();
	                if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
	                    return ia.getHostAddress();
	                }
	            }
	        }
	    } catch (Exception e) {	    
			e.printStackTrace();
		}
		return "";
	}
	

}
