package com.lmj.http.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.w3c.dom.DOMException;

/***要导入dom4j-1.6.1.jar
 * DomXMLString.java（一般而言，只要NETDATA_URL地址访问正常，本程序即可正常运行）
 * @author xuechong
 * @time 6/12/2010 11:03
 * @src  http://eric-619.iteye.com/blog/693152
 * @title：dom4j方式访问远程WebService返回xml形式的String类型数据
 * 根据url来定，浏览器中能得到什么，java程序这里就能输出什么
 */
public class CarInspectionWebServiceClientNet2备份{
	//远程WebService接口url
    private static String NETDATA_URL = "http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx/getRegionProvince";

    public static void main(String[] args) throws Exception{
    	Document document = getProvinceDocument(NETDATA_URL);
    	if(document != null){
    		System.out.println(document.asXML());
    	}
    }

    /*返回一个Document对象*/
    public static Document getProvinceDocument(String netXMLDataURL){	
    	Document document = null;
		SAXReader reader = new SAXReader();
		Map<String, String> map = new HashMap<String, String>();
		reader.getDocumentFactory().setXPathNamespaceURIs(map);
        try{
			InputStream inputStream = getSoapInputStream(netXMLDataURL);    //具体webService相关
			document = reader.read(inputStream);
			inputStream.close();
		}catch(DOMException e){
			e.printStackTrace();
			return null;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}catch (DocumentException e){
			e.printStackTrace();
		}
		return document;
    }

    /*返回InputStream对象*/
    public static InputStream getSoapInputStream(String url){
        InputStream inputStream = null;
        try{
			URL urlObj = new URL(url);
			URLConnection urlConn = urlObj.openConnection();
			/*参数具体WebService基于http://webservice.webxml.com.cn/WebServices/WeatherWS.asmx?op=getRegionProvince*/
			urlConn.setRequestProperty("Host", "webservice.webxml.com.cn");
			urlConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			urlConn.setRequestProperty("SOAPAction", "http://WebXml.com.cn/getRegionProvince");
			urlConn.connect();
			inputStream = urlConn.getInputStream();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
        return inputStream;
    }

}
