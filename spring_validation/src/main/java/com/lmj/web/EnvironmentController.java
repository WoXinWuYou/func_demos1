package com.lmj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("env")
public class EnvironmentController {
	@Autowired
	Environment environment;
	
	@RequestMapping("{key}")
	public String getKey(@PathVariable("key")String key) {
		return environment.getProperty(key);
	}
}
