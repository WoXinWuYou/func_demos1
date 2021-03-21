package com.lmj.http.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.lmj.config.Global;
import com.lmj.http.common.wsclient.GetAlarmInformationResponse.GetAlarmInformationResult;
import com.lmj.http.common.wsclient.GetStationInformationResponse.GetStationInformationResult;
import com.lmj.http.common.wsclient.GetVehicleInformationResponse.GetVehicleInformationResult;
import com.lmj.http.common.wsclient.WebServiceSoap;
import com.lmj.http.entity.AlarmInfo;
import com.lmj.http.entity.CustomXmlRootElement;
import com.lmj.http.entity.StationInfo;
import com.lmj.http.entity.VehicleInfo;
import com.lmj.mapper.JaxbMapper;

/**
 * 车检webService服务客户端
 * 
 * @author 李梦杰
 * @date 2020-11-23
 */
public class CarInspectionWebServiceClientCxf {
	/**获取webservice接口地址*/
	public static final String CAR_INSPECTION_WEBSERVICE_URL = Global.getConfig("car.inspection.webservice.url");
	public static final String CAR_INSPECTION_STATIONINFO_METHOD = Global.getConfig("car.inspection.stationinfo.method");
	public static final String CAR_INSPECTION_VEHICLEINFO_METHOD = Global.getConfig("car.inspection.vehicleinfo.method");
	public static final String CAR_INSPECTION_ALARMINFO_METHOD = Global.getConfig("car.inspection.alarminfo.method");
	
	
	private static long webserviceConnectionTimeout = Long.valueOf(Global.getConfig("webservice.connection.timeout"));
	private static long webserviceReceiveTimeout = Long.valueOf(Global.getConfig("webservice.receive.timeout"));
	/**
	 * 获取报警信息
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws Exception
	 */
	public static List<AlarmInfo> getAlarmInfos(String startTime, String endTime) throws Exception {
		WebServiceSoap webServiceSoap = getIOtherDataService();
		GetAlarmInformationResult result = webServiceSoap.getAlarmInformation(startTime, endTime);
		List<Object> roots = result.getContent();
        return getInfos(roots, AlarmInfo.class);
		
	}
	/**
	 * 获取机构信息
	 * @param staId 机构编号
	 * @return
	 * @throws Exception
	 */
	public static List<StationInfo> getStationInfos(String staId) throws Exception {
		WebServiceSoap webServiceSoap = getIOtherDataService();
		GetStationInformationResult result = webServiceSoap.getStationInformation(staId);
		List<Object> roots = result.getContent();
        return getInfos(roots, StationInfo.class);
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
		WebServiceSoap webServiceSoap = getIOtherDataService();
		GetVehicleInformationResult result = webServiceSoap.getVehicleInformation(hphm, hpzl, vin);
		List<Object> roots = result.getContent();
        return getInfos(roots, VehicleInfo.class);
	}
	
	public static <T> List<T> getInfos(List<Object> roots, Class<T> clazz) throws Exception {
		List<T> alarmInfos = new ArrayList<>();

		Object root = null;
		if(roots != null && roots.size()>0) {
			root = roots.get(0);
		}
		String xmlStr = JaxbMapper.toXml(root);
		
		if (xmlStr != null) {
			CustomXmlRootElement<T> rootElement= JaxbMapper.fromXml(xmlStr, CustomXmlRootElement.class, clazz);
		    if(rootElement.getCode() == "0") {
		    	throw new Exception(CAR_INSPECTION_WEBSERVICE_URL+"：获取数据失败，返回信息：" + rootElement.getMessage());
		    }else {
		    	alarmInfos  = rootElement.getInfos();
		    }
		}
        return alarmInfos;
	}
	
	public static WebServiceSoap getIOtherDataService() {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WebServiceSoap.class);//设置请求接口
        factory.setAddress(CAR_INSPECTION_WEBSERVICE_URL); // OtherDataService相当于对 address的封装，提供了方便使用的接口。
        WebServiceSoap iWebServiceSoap = (WebServiceSoap) factory.create(); //创建客户端对象

        //设置客户端的配置信息，超时等.
        Client proxy = ClientProxy.getClient(iWebServiceSoap);
        HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(webserviceConnectionTimeout); //连接超时时间
        policy.setReceiveTimeout(webserviceReceiveTimeout);//请求超时时间.
        conduit.setClient(policy);
        return iWebServiceSoap;
	}
}
