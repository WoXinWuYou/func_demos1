package com.lmj.http.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.lmj.config.Global;
import com.lmj.http.entity.AlarmInfo;
import com.lmj.http.entity.AlarmInfoElement;
import com.lmj.http.entity.CustomXmlRootElement;
import com.lmj.http.entity.ResultInfo;
import com.lmj.http.entity.ResultInfoElement;
import com.lmj.http.entity.StationInfo;
import com.lmj.http.entity.VehicleInfo;
import com.lmj.log.LogUtils;
import com.lmj.mapper.JaxbMapper;

public class CarInspectionWebServiceClientHttp {
	/**获取webservice接口地址*/
	public static final String CAR_INSPECTION_WEBSERVICE_URL = Global.getConfig("car.inspection.webservice.url");
	public static final String CAR_INSPECTION_WEBSERVICE_SOAPACTION = Global.getConfig("car.inspection.webservice.soapaction");
	public static final String CAR_INSPECTION_STATIONINFO_METHOD = Global.getConfig("car.inspection.stationinfo.method");
	public static final String CAR_INSPECTION_VEHICLEINFO_METHOD = Global.getConfig("car.inspection.vehicleinfo.method");
	public static final String CAR_INSPECTION_ALARMINFO_METHOD = Global.getConfig("car.inspection.alarminfo.method");
	public static final String CAR_INSPECTION_RESULTINFO_METHOD = Global.getConfig("car.inspection.resultinfo.method");
	
	
	private static long webserviceConnectionTimeout = Long.valueOf(Global.getConfig("webservice.connection.timeout"));
	private static long webserviceReceiveTimeout = Long.valueOf(Global.getConfig("webservice.receive.timeout"));
	
	static String alarmRequestDataFormat = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"+
			"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
			"<soap:Body>"+
			"<GetAlarmInformation xmlns=\"Niwxxs\">"+
			"<StaTime>%s</StaTime>"+ // <StaTime>2020-11-30 17:00:00</StaTime>"+
			"<EndTime>%s</EndTime>"+ // <EndTime>2020-11-30 19:00:00</EndTime>
			"</GetAlarmInformation>"+
			"</soap:Body>"+
			"</soap:Envelope>";
	static String stationRequestDataFormat = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"+
			"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
			"<soap:Body>"+
			"<GetStationInformation xmlns=\"Niwxxs\">"+
			"<StaID>%s</StaID>"+ // <StaID>xxx</StaID>
			"</GetStationInformation >"+
			"</soap:Body>"+
			"</soap:Envelope>";
	static String vehicleRequestDataFormat = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"+
			"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
			"<soap:Body>"+
			"<GetVehicleInformation xmlns=\"Niwxxs\">"+
			"<hphm>%s</hphm>"+
			"<hpzl>%s</hpzl>"+
			"<clsbdh>%s</clsbdh>"+
			"</GetVehicleInformation>"+
			"</soap:Body>"+
			"</soap:Envelope>";
	static String resultRequestDataFormat = "<?xml version=\"1.0\" encoding=\"utf-16\"?>"+
			"<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">"+
			"<soap:Body>"+
			"<GetDetEvlData xmlns=\"Niwxxs\">"+
			"<StaTime>%s</StaTime>"+
			"<EndTime>%s</EndTime>"+
			"</GetDetEvlData>"+
			"</soap:Body>"+
			"</soap:Envelope>";
	
	/**
	 * 获取报警信息
	 * @param startTime 开始时间（大于等于）
	 * @param endTime 结束时间（小于）
	 * @return
	 * @throws Exception
	 */
	public static List<AlarmInfo> getAlarmInfos(String startTime, String endTime) throws Exception {
		String requestData = String.format(alarmRequestDataFormat, startTime,endTime);
		List<AlarmInfoElement> alarmInfoElements = getInfos(CAR_INSPECTION_ALARMINFO_METHOD,  requestData, AlarmInfoElement.class);
		if(alarmInfoElements.size()>0) {
			return alarmInfoElements.get(0).getAlarmInfos();
		}
        return new ArrayList<AlarmInfo>();
	}
	/**
	 * 获取机构信息
	 * @param staId 机构编号
	 * @return
	 * @throws Exception
	 */
	public static List<StationInfo> getStationInfos(String staId) throws Exception {
		String requestData = String.format(stationRequestDataFormat, staId);
		List<StationInfo> stationInfos = getInfos(CAR_INSPECTION_STATIONINFO_METHOD,  requestData, StationInfo.class);
		return stationInfos;
	}
	/**
	 * 获取车辆信息
	 * @param hphm 车辆号牌号码 不可空
	 * @param hpzl 车辆号牌种类 不可空
	 * @param vin 车架号 不可空
	 * @return
	 * @throws Exception
	 */
	public static List<VehicleInfo> getVehicleInfos(String hphm, String hpzl, String vin) throws Exception {
		String requestData = String.format(vehicleRequestDataFormat, hphm, hpzl, vin);
		List<VehicleInfo> vehicleInfos = getInfos(CAR_INSPECTION_VEHICLEINFO_METHOD, requestData, VehicleInfo.class);
		return vehicleInfos;
	}
	
	/**
	 * 获取检测结果信息
	 * @param startTime 开始时间（大于等于）
	 * @param endTime 结束时间（小于）
	 * @return
	 * @throws Exception 
	 */
	public static List<ResultInfo> getResultInfos(String startTime, String endTime) throws Exception {
		String requestData = String.format(resultRequestDataFormat, startTime, endTime);
		
		List<ResultInfoElement> resultInfoElements = getInfos(CAR_INSPECTION_RESULTINFO_METHOD,  requestData, ResultInfoElement.class);
		if(resultInfoElements.size()>0) {
			return resultInfoElements.get(0).getResultInfos();
		}
        return new ArrayList<ResultInfo>();
	}
	
	
	public static <T> List<T> getInfos(String method, String requestData, Class<T> clazz) throws Exception {
		List<T> infos = new ArrayList<>();
		
		PostMethod postMethod = new PostMethod(CAR_INSPECTION_WEBSERVICE_URL);
		// requestData可以直接用soapui中请求的数据，注意<![CDATA[]]>的使用  
        byte[] b = requestData.getBytes("UTF-8");  
        InputStream in = new ByteArrayInputStream(b, 0, b.length);  
        RequestEntity re = new InputStreamRequestEntity(in, "text/xml; charset=utf-8");  
        postMethod.setRequestEntity(re);
        // 设置header SOAPAction,不设置的话，会报异常:no SOAPAction  
        // header,但是SOAPAction的好像任意值都可以  
        String soapAction = CAR_INSPECTION_WEBSERVICE_SOAPACTION + "/"+method;  
        postMethod.setRequestHeader("SOAPAction", soapAction);  
        String contentType = "text/xml;charset=UTF-8";  
        postMethod.setRequestHeader("Content-Type", contentType);  
        HttpClient client = new HttpClient();  
        // 设置超时（不知道默认是多久，没有设置的时候，也没有报错，设置下保险些）  
        client.getHttpConnectionManager().getParams().setConnectionTimeout((int)webserviceConnectionTimeout);  
        client.getHttpConnectionManager().getParams().setSoTimeout((int)webserviceReceiveTimeout);  
        //  
        int status = client.executeMethod(postMethod);
        if (status == 200) {// 成功  
            InputStream is = postMethod.getResponseBodyAsStream();  
            /** 
             * 获取的结果可以参考用soapui调用时的返回值， 
             * 如果约定的返回值是XML,并不会像soapui一样把xml用<![CDATA[]]>包含起来,要注意解析的方法, 
             * 不知道soapui如何处理的，暂时没时间研究。 
             */  
            // 根据具体的返回值写的解析  
    	    SAXReader reader = new SAXReader();  
    	    Document document = reader.read(is);
    	    Element envelopElement = document.getRootElement();
    	    Element bodyElement = envelopElement.elements().get(0);
    	    Element rootElement = bodyElement.elements().get(0).elements().get(0).elements().get(0); // body->response->result->root
    	    CustomXmlRootElement<T> alarmInfoRootElement= JaxbMapper.fromXml(rootElement.asXML(), CustomXmlRootElement.class, clazz);
		    if("0".equals(alarmInfoRootElement.getCode())) {
		    	LogUtils.error(CAR_INSPECTION_WEBSERVICE_URL+":"+method+"获取数据失败，返回信息：" + alarmInfoRootElement.getMessage()+" 请求参数:"+requestData);
		    }else {
		    	infos = alarmInfoRootElement.getInfos();
		    	infos = infos == null ? new ArrayList<>(): infos;
		    	// LogUtils.info("调用Webservice：{}，method：{}，获取数据条数:{}条", CAR_INSPECTION_WEBSERVICE_URL,method,infos.size());
		    }
        } else {  
            LogUtils.error("调用Webservice：{}，method：{}，出错，错误代码为：",CAR_INSPECTION_WEBSERVICE_URL,soapAction,status);  
        }
		return infos;
	}
}
