package com.lmj.http.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import com.lmj.config.Global;
import com.lmj.http.entity.AlarmInfo;
import com.lmj.http.entity.AlarmInfoElement;
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
public class CarInspectionWebServiceClientAxis {
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("StartTime", startTime);
		params.put("EndTime", endTime);
		List<AlarmInfoElement> alarmInfoElements = getInfos(CAR_INSPECTION_ALARMINFO_METHOD,  params, AlarmInfoElement.class);
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("StaID", staId);
		List<StationInfo> stationInfos = getInfos(CAR_INSPECTION_STATIONINFO_METHOD,  params, StationInfo.class);
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
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("hphm", hphm);
		params.put("hpzl", hpzl);
		params.put("vin", vin);
		List<VehicleInfo> vehicleInfos = getInfos(CAR_INSPECTION_VEHICLEINFO_METHOD,  params, VehicleInfo.class);
		return vehicleInfos;
	}
	
	public static <T> List<T> getInfos(String method, Map<String, Object> params, Class<T> clazz) throws Exception {
		List<T> alarmInfos = new ArrayList<>();
        
		//获取域名地址，server定义的
        String soapaction = "/";//"http://schemas.xmlsoap.org/soap/envelope/"; // TODO 这里会一样吗？
        // 创建一个服务(service)调用(call)
        Service service = new Service();
        // 创建一个服务(service)调用(call)
        Call call = (Call) service.createCall(); // 通过service创建call对象
        // 设置service所在URL
        call.setTargetEndpointAddress(CAR_INSPECTION_WEBSERVICE_URL);
        call.setOperationName(new QName(soapaction, method));
        //设置参数及类型，与接口参数对应
        List<Object> paramValues = new ArrayList<Object>();
        for (Entry<String, Object> paramEntry : params.entrySet()) {
        	call.addParameter(new QName(soapaction, paramEntry.getKey()), XMLType.XSD_STRING, ParameterMode.IN);
        	paramValues.add(paramEntry.getValue());
		}
        call.setUseSOAPAction(true);
        // call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_DOCUMENT); //返回参数的类型（注意是string还是document）
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING); //返回参数的类型（注意是string还是document）
        call.setSOAPActionURI(soapaction + method); //这个也要注意 就是要加上要调用的方法,不然也会报错

        //invoke调用方法并传递参数，获取XML
		String xmlStr = (String) call.invoke(paramValues.toArray());
		if (xmlStr != null) {
			CustomXmlRootElement<T> alarmInfoRootElement= JaxbMapper.fromXml(xmlStr, CustomXmlRootElement.class, clazz);
		    if(alarmInfoRootElement.getCode() == "0") {
		    	throw new Exception(CAR_INSPECTION_WEBSERVICE_URL+":"+method+"获取数据失败，返回信息：" + alarmInfoRootElement.getMessage());
		    }else {
		    	alarmInfos = alarmInfoRootElement.getInfos();
		    }
		}

        return alarmInfos;
	}
}
