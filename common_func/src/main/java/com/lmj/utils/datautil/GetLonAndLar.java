package com.lmj.utils.datautil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSONObject;
import com.lmj.utils.httputil.HttpUtils;

/**
 * @ProjectName: shopserver
 * @Package: com.ldst.environmentServer.utils.datautil
 * @Author: zhenyaxin
 * @Date: 2019/12/9 8:55
 * @Version: 1.0
 * 根据名称获取经纬度坐标（百度地图）
 */
public class GetLonAndLar {
    /**
     *   
     *      * @param addr  
     *      * 查询的地址  
     *      * @return  
     *      * @throws IOException  
     *      
     */
    public static String[] getCoordinate(String addr) throws IOException {
        String lng = null;//经度  
        String lat = null;//纬度  
        String address = null;
        try {
            address = java.net.URLEncoder.encode(addr, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        //System.out.println(address); 
        // http://api.map.baidu.com/geocoding/v3/?address=北京市海淀区上地十街10号&output=json&ak=NUuInjQgUoZGZQwnwQuIwvUCw2iPTs1c&callback=showLocation 
        String url = "http://api.map.baidu.com/geocoding/v3/?address="+address+"&output=json&ak=NUuInjQgUoZGZQwnwQuIwvUCw2iPTs1c";
        URL myURL = null;
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                while ((data = br.readLine()) != null) {
                    JSONObject json = JSONObject.parseObject(data);
                    lng = json.getJSONObject("result").getJSONObject("location").getString("lng");
                    lat = json.getJSONObject("result").getJSONObject("location").getString("lat");
                }
            }
        } catch (IOException e) {

        } finally {
            if (insr != null) {
                insr.close();
            }
            if (br != null) {
                br.close();
            }
            if (lng==null){
                return null;
            }
        }
        return new String[]{lng, lat};
    }

    public static String getAddr(Float lng, Float lat) throws IOException {

        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?output=json&ak=NUuInjQgUoZGZQwnwQuIwvUCw2iPTs1c&location=" + lat + "," + lng;
        URL myURL = null;
        String city = "";
        String qx = "";
        URLConnection httpsConn = null;
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStreamReader insr = null;
        BufferedReader br = null;
        try {
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理   
            if (httpsConn != null) {
                insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = null;
                while ((data = br.readLine()) != null) {
                    JSONObject json = JSONObject.parseObject(data);
                    city = json.getJSONObject("result").getString("formatted_address");
                    //qx = json.getJSONObject("result").getJSONObject("addressComponent").getString("district");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (insr != null) {
                insr.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return city;
    }
    public static void getAddrData(Double lng, Double lat) throws IOException {

        String url = "https://map.zq12369.com/#/layer=terrain/item=aqi/overlay=none/orthographic=" + lat + "," + lng + ",4";
        String s = HttpUtils.sendHttp(url);
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);
    }



    public static void xxxxmain(String[]args) throws IOException{
       // Tests getLatAndLngByBaidu = new Tests();
        //String[] o = getCoordinate("怀柔镇");
        //String[] o1 = getAddr(o[0],o[1]);
       /* System.out.println(Double.valueOf(o[0]));
        System.out.println(Double.valueOf(o[1]));*/
        //getAddrData(98.935543,36.762361);
        String addr = getAddr(114.544545f, 37.025084f);
        System.out.println(addr);
    }
}

