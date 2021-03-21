package com.lmj.http.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lmj.http.entity.AlarmInfo;
import com.lmj.http.entity.ResultInfo;
import com.lmj.http.entity.StationInfo;
import com.lmj.http.entity.VehicleInfo;
import com.lmj.http.service.CarInspectionWebServiceClientHttp;
import com.lmj.log.LogUtils;
import com.lmj.mapper.JsonMapper;
import com.lmj.utils.ExceptionUtils;
import com.lmj.utils.timeutil.TimeUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("manualCarInspectionData")
@Api(tags = "手动获取车检数据")
public class CarInspectionDataController {
	
	@GetMapping("alarmInfo")
	@ApiOperation(value = "手动获取报警数据")
	public String alarmInfo(
			@ApiParam(value="开始时间（大于等于，格式yyyy-MM-dd HH:mm:ss",required = true)
			@RequestParam(required = true) Date startDate,
			@ApiParam(value="结束时间（小于），格式yyyy-MM-dd HH:mm:ss",required = true)
			@RequestParam(required = true) Date endDate) {
		List<AlarmInfo> list = new ArrayList<AlarmInfo>();
		try {
			list = CarInspectionWebServiceClientHttp.getAlarmInfos(TimeUtils.convertDate2String(startDate, "yyyy-MM-dd HH:mm:ss"), TimeUtils.convertDate2String(endDate, "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			LogUtils.error("手动获取报警数据，发生未知异常，异常信息：{}", ExceptionUtils.getStackTraceAsString(e));
			return "手动获取报警数据，发生未知异常，异常信息：{}" + ExceptionUtils.getStackTraceAsString(e);
		}
		return JsonMapper.toJsonString(list);
	}
	
	@GetMapping("stationInfo")
	@ApiOperation(value = "手动获取机构数据")
	public String alarmInfo(
			@ApiParam(value="机构编号",required = true)
			@RequestParam(required = true) String staId
			) {
		List<StationInfo> list = new ArrayList<StationInfo>();
		try {
			list = CarInspectionWebServiceClientHttp.getStationInfos(staId);
		} catch (Exception e) {
			LogUtils.error("手动获取机构数据，发生未知异常，异常信息：{}", ExceptionUtils.getStackTraceAsString(e));
			return "手动获取机构数据，发生未知异常，异常信息：{}" + ExceptionUtils.getStackTraceAsString(e);
		}
		return JsonMapper.toJsonString(list);
	}
	
	@GetMapping("vehicleInfo")
	@ApiOperation(value = "手动获取车辆数据")
	public String vehicleInfo(
			@ApiParam(value="车辆号牌号码",required = true)
			@RequestParam(required = true) String hphm,
			@ApiParam(value="车辆号牌种类",required = true)
			@RequestParam(required = true) String hpzl,
			@ApiParam(value="车辆识别代号，别名VIN 或者大架号",required = true)
			@RequestParam(required = true) String vin
			) {
		List<VehicleInfo> list = new ArrayList<VehicleInfo>();
		try {
			list = CarInspectionWebServiceClientHttp.getVehicleInfos(hphm, hpzl, vin);
		} catch (Exception e) {
			LogUtils.error("手动获取车辆数据，发生未知异常，异常信息：{}", ExceptionUtils.getStackTraceAsString(e));
			return "手动获取车辆数据，发生未知异常，异常信息：{}" + ExceptionUtils.getStackTraceAsString(e);
		}
		return JsonMapper.toJsonString(list);
	}
	
	@GetMapping("resultInfo")
	@ApiOperation(value = "手动获取车检结果数据")
	public String vehicleInfo(
			@ApiParam(value="开始时间（大于等于，格式yyyy-MM-dd HH:mm:ss",required = true)
			@RequestParam(required = true) Date startDate,
			@ApiParam(value="结束时间（小于），格式yyyy-MM-dd HH:mm:ss",required = true)
			@RequestParam(required = true) Date endDate
			) {
		List<ResultInfo> list = new ArrayList<ResultInfo>();
		try {
			list = CarInspectionWebServiceClientHttp.getResultInfos(TimeUtils.convertDate2String(startDate, "yyyy-MM-dd HH:mm:ss"), TimeUtils.convertDate2String(endDate, "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			LogUtils.error("手动获取车检结果数据，发生未知异常，异常信息：{}", ExceptionUtils.getStackTraceAsString(e));
			return "手动获取车检结果，发生未知异常，异常信息：{}" + ExceptionUtils.getStackTraceAsString(e);
		}
		return JsonMapper.toJsonString(list);
	}
}
