package com.pay;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.util.HttpUtil;
import com.util.PayCommonUtil;
import com.util.PayConfigUtil;
import com.util.XMLUtil;



public class WeixinPay {
	public static void main(String[] args) throws Exception {  
        // 账号信息  
        String appid = PayConfigUtil.getAPP_ID();  // appid  
        //String appsecret = PayConfigUtil.APP_SECRET; // appsecret  
        String mch_id = PayConfigUtil.getMCH_ID(); // 商业号  
        String key = PayConfigUtil.getAPI_KEY(); // key  
  
        String currTime = PayCommonUtil.getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = PayCommonUtil.buildRandom(4) + "";  
        String nonce_str = strTime + strRandom;  
          
        String order_price = "1"; // 价格   注意：价格的单位是分  
        String body = "goodssssss";   // 商品名称  
        String out_trade_no = "11338"; // 订单号  
          
        // 获取发起电脑 ip  
        String spbill_create_ip = PayConfigUtil.getCREATE_IP();  
        // 回调接口   
        String notify_url = PayConfigUtil.getNOTIFY_URL();  
        String trade_type = "NATIVE";  
          
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();  
        packageParams.put("appid", appid);  
        packageParams.put("mch_id", mch_id);  
        packageParams.put("sub_mch_id", "1900000109");  
        packageParams.put("nonce_str", nonce_str);  
        packageParams.put("body", body);  
        packageParams.put("out_trade_no", out_trade_no);  
        packageParams.put("total_fee", order_price);  
        packageParams.put("spbill_create_ip", spbill_create_ip);  
        packageParams.put("notify_url", notify_url);  
        packageParams.put("trade_type", trade_type);  
  
        String sign = PayCommonUtil.createSign("UTF-8", packageParams,key);  
        packageParams.put("sign", sign);  
          
        String requestXML = PayCommonUtil.getRequestXml(packageParams);  
        System.out.println(requestXML);  
   
        String resXml = HttpUtil.postData(PayConfigUtil.getUFDODER_URL(), requestXML);  
        System.out.println("resXml:"+resXml);
          
        Map map = XMLUtil.doXMLParse(resXml);  
        //String return_code = (String) map.get("return_code");  
        //String prepay_id = (String) map.get("prepay_id");  
        String urlCode = (String) map.get("code_url");  
          
}  
}
