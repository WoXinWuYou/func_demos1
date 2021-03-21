package com.lmj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("env")
@Api(value = "环境信息获取-EnvironmentController",tags = {"系统","环境"})
public class EnvironmentController {
	@Autowired
	Environment environment;
	
	@GetMapping("{key}")
	public String getKey(@PathVariable("key")String key) {
		return environment.getProperty(key);
	}
}
