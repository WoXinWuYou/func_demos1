package com.lmj.http.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 机构信息
 * @author 李梦杰
 * @date 2020-11-23
 */
@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class StationInfo {
	private String id;
	@XmlElement(name = "StaID") private String staId; // 机构编号.
	@XmlElement(name = "StaName") private String staName; // 机构名称.
	@XmlElement(name = "Address") private String address; // 机构地址.
	@XmlElement(name = "CAbbreviation") private String cAbbreviation; // 机构中文简称.
	@XmlElement(name = "StaProperty") private String staProperty; // 机构类型代码.0：省环保厅，1：市环保局,2：检测机构
	@XmlElement(name = "StaPropertyName") private String staPropertyName; // 机构类型名称.
	@XmlElement(name = "StatoidID") private String statoidID; // 行政区划编号.
	@XmlElement(name = "StatoidName") private String statoidName; // 行政区划名称.
	@XmlElement(name = "Telephone") private String telephone; // 联系电话.
	@XmlElement(name = "Faxes") private String faxes; // 传真.
	@XmlElement(name = "WebSite") private String webSite; // 公司网址.
	@XmlElement(name = "Email") private String email; // 电子邮箱.
	@XmlElement(name = "ZipCode") private String zipCode; // 邮政编码.
	@XmlElement(name = "OrgCode") private String orgCode; // 社会统一信用代码.
	@XmlElement(name = "LegalPerson") private String legalPerson; // 法人代表.
	@XmlElement(name = "Staffing") private String staffing; // 编制数量.
	@XmlElement(name = "LinkDate") private String linkDate; // 联网日期.
	@XmlElement(name = "RegistrationTime") private String registrationTime; // 注册日期.
	@XmlElement(name = "RegTime") private String regTime; // 成立日期.
	@XmlElement(name = "CEOName") private String ceoName; // 负责人姓名.
	@XmlElement(name = "CEOMobilePhone") private String ceoMobilePhone; // 负责人电话.
	@XmlElement(name = "CEOOfficePhone") private String ceoOfficePhone; // 负责人办公电话.
	@XmlElement(name = "LicenseKey") private String licenseKey; // 资质认定编号.
	@XmlElement(name = "ValidityDate") private String validityDate; // 资质认定有效期.
	@XmlElement(name = "StaState") private String staState; // 机构状态.0：正常，1：省厅锁定，2：市局锁定，3：资质许可到期，4：计量到期，5：环保委托到期，6：暂未开业
	@XmlElement(name = "ServiceStar") private String serviceStar; // 服务星级.1: 一星，2：二星，3：三星，4：四星，5：五星
	@XmlElement(name = "RegGasCarNum") private String regGasCarNum; // 允许每天注册汽油车数量.
	@XmlElement(name = "RegDervCarNum") private String regDervCarNum; // 允许每天注册柴油车数量.
	@XmlElement(name = "TestCarNum") private String testCarNum; // 允许每天检测车辆数.
	@XmlElement(name = "TestCarTimes") private String testCarTimes; // 允许每天检测车辆次数.
	@XmlElement(name = "IsMissingCar") private String isMissingCar; // 是否允许检测漏检车标识.0：否，1：是
	@XmlElement(name = "IsMissingCarTag") private String isMissingCarTag; // 是否允许检测漏检车(是、否).
	@XmlElement(name = "IsYellowLabelCar") private String isYellowLabelCar; // 是否允许检测黄标车标识.0：否，1：是
	@XmlElement(name = "IsYellowLabelCarTag") private String isYellowLabelCarTag; // 是否允许检测黄标车(是、否).
	@XmlElement(name = "StaServerIP") private String staServerIP; // 站点服务器 IP.
	@XmlElement(name = "InterfaceAddress") private String interfaceAddress; // 站点接口地址.
	@XmlElement(name = "CabCheckWay") private String cabCheckWay; // 标定检查方法.
	@XmlElement(name = "Longitude") private String longitude; // 机构位置纬度.
	@XmlElement(name = "Latitude") private String latitude; // 机构位置经度.

	private Date createDate;
	private Date updateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStaId() {
		return staId;
	}
	public void setStaId(String staId) {
		this.staId = staId;
	}
	public String getStaName() {
		return staName;
	}
	public void setStaName(String staName) {
		this.staName = staName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getcAbbreviation() {
		return cAbbreviation;
	}
	public void setcAbbreviation(String cAbbreviation) {
		this.cAbbreviation = cAbbreviation;
	}
	public String getStaProperty() {
		return staProperty;
	}
	public void setStaProperty(String staProperty) {
		this.staProperty = staProperty;
	}
	public String getStaPropertyName() {
		return staPropertyName;
	}
	public void setStaPropertyName(String staPropertyName) {
		this.staPropertyName = staPropertyName;
	}
	public String getStatoidID() {
		return statoidID;
	}
	public void setStatoidID(String statoidID) {
		this.statoidID = statoidID;
	}
	public String getStatoidName() {
		return statoidName;
	}
	public void setStatoidName(String statoidName) {
		this.statoidName = statoidName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFaxes() {
		return faxes;
	}
	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getStaffing() {
		return staffing;
	}
	public void setStaffing(String staffing) {
		this.staffing = staffing;
	}
	public String getLinkDate() {
		return linkDate;
	}
	public void setLinkDate(String linkDate) {
		this.linkDate = linkDate;
	}
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	public String getRegTime() {
		return regTime;
	}
	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}
	public String getCeoName() {
		return ceoName;
	}
	public void setCeoName(String ceoName) {
		this.ceoName = ceoName;
	}
	public String getCeoMobilePhone() {
		return ceoMobilePhone;
	}
	public void setCeoMobilePhone(String ceoMobilePhone) {
		this.ceoMobilePhone = ceoMobilePhone;
	}
	public String getCeoOfficePhone() {
		return ceoOfficePhone;
	}
	public void setCeoOfficePhone(String ceoOfficePhone) {
		this.ceoOfficePhone = ceoOfficePhone;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public String getValidityDate() {
		return validityDate;
	}
	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}
	public String getStaState() {
		return staState;
	}
	public void setStaState(String staState) {
		this.staState = staState;
	}
	public String getServiceStar() {
		return serviceStar;
	}
	public void setServiceStar(String serviceStar) {
		this.serviceStar = serviceStar;
	}
	public String getRegGasCarNum() {
		return regGasCarNum;
	}
	public void setRegGasCarNum(String regGasCarNum) {
		this.regGasCarNum = regGasCarNum;
	}
	public String getRegDervCarNum() {
		return regDervCarNum;
	}
	public void setRegDervCarNum(String regDervCarNum) {
		this.regDervCarNum = regDervCarNum;
	}
	public String getTestCarNum() {
		return testCarNum;
	}
	public void setTestCarNum(String testCarNum) {
		this.testCarNum = testCarNum;
	}
	public String getTestCarTimes() {
		return testCarTimes;
	}
	public void setTestCarTimes(String testCarTimes) {
		this.testCarTimes = testCarTimes;
	}
	public String getIsMissingCar() {
		return isMissingCar;
	}
	public void setIsMissingCar(String isMissingCar) {
		this.isMissingCar = isMissingCar;
	}
	public String getIsMissingCarTag() {
		return isMissingCarTag;
	}
	public void setIsMissingCarTag(String isMissingCarTag) {
		this.isMissingCarTag = isMissingCarTag;
	}
	public String getIsYellowLabelCar() {
		return isYellowLabelCar;
	}
	public void setIsYellowLabelCar(String isYellowLabelCar) {
		this.isYellowLabelCar = isYellowLabelCar;
	}
	public String getIsYellowLabelCarTag() {
		return isYellowLabelCarTag;
	}
	public void setIsYellowLabelCarTag(String isYellowLabelCarTag) {
		this.isYellowLabelCarTag = isYellowLabelCarTag;
	}
	public String getStaServerIP() {
		return staServerIP;
	}
	public void setStaServerIP(String staServerIP) {
		this.staServerIP = staServerIP;
	}
	public String getInterfaceAddress() {
		return interfaceAddress;
	}
	public void setInterfaceAddress(String interfaceAddress) {
		this.interfaceAddress = interfaceAddress;
	}
	public String getCabCheckWay() {
		return cabCheckWay;
	}
	public void setCabCheckWay(String cabCheckWay) {
		this.cabCheckWay = cabCheckWay;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
