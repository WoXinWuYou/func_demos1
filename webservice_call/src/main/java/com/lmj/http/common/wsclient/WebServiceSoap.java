
package com.lmj.http.common.wsclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebServiceSoap", targetNamespace = "/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WebServiceSoap {


    /**
     * 
     * @param staID
     * @return
     *     returns com.lmj.http.common.wsclient.GetStationInformationResponse.GetStationInformationResult
     */
    @WebMethod(operationName = "GetStationInformation", action = "/GetStationInformation")
    @WebResult(name = "GetStationInformationResult", targetNamespace = "")
    @RequestWrapper(localName = "GetStationInformation", targetNamespace = "", className = "com.lmj.http.common.wsclient.GetStationInformation")
    @ResponseWrapper(localName = "GetStationInformationResponse", targetNamespace = "", className = "com.lmj.http.common.wsclient.GetStationInformationResponse")
    public com.lmj.http.common.wsclient.GetStationInformationResponse.GetStationInformationResult getStationInformation(
        @WebParam(name = "StaID", targetNamespace = "")
        String staID);

    /**
     * 
     * @param hpzl
     * @param clsbdh
     * @param hphm
     * @return
     *     returns com.lmj.http.common.wsclient.GetVehicleInformationResponse.GetVehicleInformationResult
     */
    @WebMethod(operationName = "GetVehicleInformation", action = "/GetVehicleInformation")
    @WebResult(name = "GetVehicleInformationResult", targetNamespace = "")
    @RequestWrapper(localName = "GetVehicleInformation", targetNamespace = "", className = "com.lmj.http.common.wsclient.GetVehicleInformation")
    @ResponseWrapper(localName = "GetVehicleInformationResponse", targetNamespace = "", className = "com.lmj.http.common.wsclient.GetVehicleInformationResponse")
    public com.lmj.http.common.wsclient.GetVehicleInformationResponse.GetVehicleInformationResult getVehicleInformation(
        @WebParam(name = "hphm", targetNamespace = "")
        String hphm,
        @WebParam(name = "hpzl", targetNamespace = "")
        String hpzl,
        @WebParam(name = "clsbdh", targetNamespace = "")
        String clsbdh);

    /**
     * 
     * @param staTime
     * @param endTime
     * @return
     *     returns com.lmj.http.common.wsclient.GetAlarmInformationResponse.GetAlarmInformationResult
     */
    @WebMethod(operationName = "GetAlarmInformation", action = "/GetAlarmInformation")
    @WebResult(name = "GetAlarmInformationResult", targetNamespace = "")
    @RequestWrapper(localName = "GetAlarmInformation", targetNamespace = "", className = "com.lmj.http.common.wsclient.GetAlarmInformation")
    @ResponseWrapper(localName = "GetAlarmInformationResponse", targetNamespace = "", className = "com.lmj.http.common.wsclient.GetAlarmInformationResponse")
    public com.lmj.http.common.wsclient.GetAlarmInformationResponse.GetAlarmInformationResult getAlarmInformation(
        @WebParam(name = "StaTime", targetNamespace = "")
        String staTime,
        @WebParam(name = "EndTime", targetNamespace = "")
        String endTime);

}