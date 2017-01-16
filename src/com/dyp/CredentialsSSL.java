package com.dyp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 
 * @author Dyp
 * SSL证书双向验证,退款API中需要
 */
public class CredentialsSSL {
	
	public final static void main(String[] args) throws Exception {
		//标识用PKCS12加密认证
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        //读取本地证书，指定证书的位置,java直接用以.p12结尾的那个，其他的可以忽略
        FileInputStream instream = new FileInputStream(new File("C:\\Users\\Dyp\\Desktop\\cert\\apiclient_cert.p12"));
        try {
        	//加载证书,"xxxx"表示证书密码，一般为自己的商户号
            keyStore.load(instream, "xxxxx".toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs(信任自己的证书)
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "xxxx".toCharArray())
                .build();
        //只允许TLSv1协议
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
        	//利用httpClient发送请求,这里可以用封装好的HttpUtil去将数据也封装到请求中一起发送
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            System.out.println("executing request" + httpPost.getRequestLine());
            //发送
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
            	//获得请求响应
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                	//获取数据长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    //读响应回来的数据
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                    }
                   
                }
                //利用EntityUtils帮助类来关闭资源
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
}
