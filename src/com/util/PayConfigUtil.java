package com.util;

public class PayConfigUtil {

	private static  String APP_ID="wx30975f3ca5f4fdf9";
	private static String MCH_ID="1311111100";
	private static String API_KEY="";
	private static String CREATE_IP="192.168.1.133";
	private static String NOTIFY_URL="";
	private static String UFDODER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static String getAPP_ID() {
		return APP_ID;
	}
	public static void setAPP_ID(String aPP_ID) {
		APP_ID = aPP_ID;
	}
	public static String getMCH_ID() {
		return MCH_ID;
	}
	public static void setMCH_ID(String mCH_ID) {
		MCH_ID = mCH_ID;
	}
	public static String getAPI_KEY() {
		return API_KEY;
	}
	public static void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}
	public static String getCREATE_IP() {
		return CREATE_IP;
	}
	public static void setCREATE_IP(String cREATE_IP) {
		CREATE_IP = cREATE_IP;
	}
	public static String getNOTIFY_URL() {
		return NOTIFY_URL;
	}
	public static void setNOTIFY_URL(String nOTIFY_URL) {
		NOTIFY_URL = nOTIFY_URL;
	}
	public static String getUFDODER_URL() {
		return UFDODER_URL;
	}
	public static void setUFDODER_URL(String uFDODER_URL) {
		UFDODER_URL = uFDODER_URL;
	}

	
}
