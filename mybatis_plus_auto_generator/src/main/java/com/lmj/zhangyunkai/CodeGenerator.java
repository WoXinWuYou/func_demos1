package com.lmj.zhangyunkai;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * mysql 代码生成器演示
 * </p>
 *
 * @author zhangyk
 * @since 2018-09-12
 */
public class CodeGenerator {

	/**
	 * RUN THIS
	 */
	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// ********* 可修改配置 begin **********
		String dbUrl = "jdbc:mysql://119.45.146.19:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Hongkong&allowMultiQueries=true";
		String dbUserName = "lmjtest";
		String dbPassword = "lmjtest123456";

		// controller 模块所在位置
		String moduleName = "mybatis_plus_auto_generator";
		// server模块父包
		String modulePackage = "com.lmj.test";
		
		// 表名忽略前缀
		String tablePrefix = "t";
		// 目标表正则
		String tableLike = "student";
		// 模块名称
		String modubleName = "stu";
		// 是否需要导出
		Boolean isExport = Boolean.FALSE;
		// 是否有BigDecimal类型字段
		Boolean hasBigDecimal = Boolean.FALSE;

		// ********* 可修改配置 end **********

		// 项目文件夹名称
		String parentProjectDir = "func_demos1";
		
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		
		String parentProjectPath = CodeGenerator.getParentProjectPath() + File.separator + parentProjectDir;
		
		// =====各组件所在包名
		String entityPackageName = "entity";
		String servicePackageName = "service";
		String serviceImplPackageName = "service.impl";
		String controllerPackageName = "controller";
		String daoPackageName = "dao";
		
		// ===== server模块
		// server模块路径
		String serverModuleName = moduleName;
		// server模块父包
		String serverModulePackage = modulePackage;
		// server包
		String serverPackage = serverModulePackage+"."+modubleName + "." + controllerPackageName;
		// server模块路径
		String serverProjectPath = parentProjectPath + File.separator + serverModuleName;
		
		// ===== model模块
		
		String modelModuleName = "mybatis_plus_auto_generator"; // model模块路径
		String modelModulePackage = "com.lmj.test"; // model模块父包
		String modelPackage = modelModulePackage+"."+modubleName + "." + entityPackageName; // model包
		String modelProjectPath = parentProjectPath + File.separator + modelModuleName; // model模块路径
		
		// ===== service模块
		String serviceModuleName = "mybatis_plus_auto_generator"; // service模块路径
		String serviceModulePackage = "com.lmj.test"; // service模块父包
		String serviceProjectPath = parentProjectPath + File.separator + serviceModuleName; // service模块路径
		
		// ===== 默认模块
		String defaultProjectPath = serviceProjectPath; // 默认模块项目路径
		String defaultModulePackage = serviceModulePackage; // 默认模块基础包名
		
		
		String requestPackage = defaultModulePackage+"."+modubleName + ".request"; // request模块父包
		String voPackage = defaultModulePackage+"."+modubleName + ".vo"; // vo模块父包
		
		// ******* 以下勿动 *******
		// controller父类
		String superControllerClass = "com.hg.common.base.BaseController";
		// request父类
		String requestSuperClass = "com.hg.common.base.BaseRequest";
		// 转换工具类
		String commonUtilsClass = "com.hg.common.base.CommonUtils";
		// 统一返回值类
		String responseClass = "com.hg.common.base.response.R";
		// 导出工具类
		String exportUtilsClass = null;

		String systemUserClass = "com.hg.common.entity.SysUser";


		gc.setOutputDir(defaultProjectPath + "/src/main/java");
		gc.setAuthor("auto");
		gc.setOpen(false);
		gc.setSwagger2(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(dbUrl);
		dsc.setDriverName("com.mysql.cj.jdbc.Driver");
		dsc.setUsername(dbUserName);
		dsc.setPassword(dbPassword);
		dsc.setTypeConvert(new MySqlTypeConvert() {
			@Override
			public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
				// tinyint转换成Integer
				if (fieldType.toLowerCase().contains("tinyint")) {
					return DbColumnType.INTEGER;
				}
				return super.processTypeConvert(globalConfig, fieldType);
			}
		});
		mpg.setDataSource(dsc);

		// 包配置（默认模块使用）
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(modubleName);
		pc.setParent(defaultModulePackage);
		pc.setEntity(entityPackageName);
		pc.setService(servicePackageName);
		pc.setServiceImpl(serviceImplPackageName);
		pc.setController(controllerPackageName);
		pc.setMapper(daoPackageName);
		mpg.setPackageInfo(pc);

		// 自定义参数配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				map.put("requestSuperClass", requestSuperClass);
				map.put("CommonUtilsClass", commonUtilsClass);
				map.put("responseClass", responseClass);
				map.put("isExport", isExport);
				map.put("hasBigDecimal", hasBigDecimal);
				map.put("exportUtilsClass", exportUtilsClass);
				map.put("systemUserClass", systemUserClass);
				map.put("baseControllerClass", superControllerClass);
				map.put("serverPackage",serverPackage);
				map.put("modelPackage",modelPackage);
				map.put("requestPackage",requestPackage);
				map.put("voPackage",voPackage);
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return defaultProjectPath + "/src/main/resources/mapper/" + pc.getModuleName() + File.separator
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});


		focList.add(new FileOutConfig("/templates/entity.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return modelProjectPath + "/src/main/java/" + packageToPath(modelPackage) + tableInfo.getEntityName() + StringPool.DOT_JAVA;
			}
		});

		// 自定义controller配置（位置异常，重新调整）
		/*focList.add(new FileOutConfig("/templates/controller.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 使用controller服务所在地址，替换原来输出地址
				String expand = gc.getOutputDir().replace(whichServe, controllerServer) + "/"
						+ packageName.replaceAll("\\.", "/") + "/controller" + "/" + modubleName;
				String entityFile = String.format((expand + File.separator + "%s" + ".java"),
						tableInfo.getControllerName());
				return entityFile;
			}
		});*/

		// 自定义request配置
		focList.add(new FileOutConfig("/templates/request.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = gc.getOutputDir() + File.separator + packageToPath(requestPackage);
				String entityFile = String.format((expand + "%s" + "Request.java"),
						tableInfo.getEntityName());
				return entityFile;
			}
		});

		// 自定义vo配置
		focList.add(new FileOutConfig("/templates/vo.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = gc.getOutputDir() + File.separator + packageToPath(voPackage);
				String entityFile = String.format((expand + "%s" + "Vo.java"),
						tableInfo.getEntityName());
				return entityFile;
			}
		});
		

		focList.add(new FileOutConfig("/templates/controller.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return serverProjectPath + "/src/main/java/" + packageToPath(serverPackage) + tableInfo.getControllerName() + StringPool.DOT_JAVA;
			}
		});

		/* >>>>>> 前端代码生成 begin <<<<<<< */
/*
		// 自定义前端model配置
		focList.add(new FileOutConfig("/templates/index.vue.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = baseLocation + projectPackage + "/" + tableInfo.getEntityName();
				String entityFile = expand + File.separator + "index.vue";
				return entityFile;
			}
		});

		// 自定义前端services配置
		focList.add(new FileOutConfig("/templates/view/services/index.js.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = baseLocation + projectPackage + "/" + tableInfo.getEntityName() + "/services";
				String entityFile = expand + File.separator + "index.js";
				return entityFile;
			}
		});

		// 自定义前端搜索栏js配置
		focList.add(new FileOutConfig("/templates/view/FilterIpts.js.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = baseLocation + projectPackage + "/" + tableInfo.getEntityName() + "/";
				String entityFile = expand + File.separator + "FilterIpts.js";
				return entityFile;
			}
		});

		// 自定义前端list主体配置
		focList.add(new FileOutConfig("/templates/view/index.js.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = baseLocation + projectPackage + "/" + tableInfo.getEntityName() + "/";
				String entityFile = expand + File.separator + "index.js";
				return entityFile;
			}
		});

		// 自定义前端list 弹窗主体配置
		focList.add(new FileOutConfig("/templates/view/ModifyForm.js.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = baseLocation + projectPackage + "/" + tableInfo.getEntityName() + "/";
				String entityFile = expand + File.separator + "ModifyForm.js";
				return entityFile;
			}
		});*/

		/* >>>>>> 前端代码生成 end <<<<<<< */



		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().disable(TemplateType.ENTITY).setXml(null).setController(null)); // xml、controller通过FileOutConfig指定，这里设置为null
		//mpg.setTemplate(new TemplateConfig().disable(TemplateType.ENTITY).setEntity("templates/entity.java"));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setEntityLombokModel(true);
		// strategy.setRestControllerStyle(true);

		strategy.setTablePrefix(tablePrefix);
		strategy.setInclude(tableLike);
		// controller继承父类
		strategy.setSuperControllerClass(superControllerClass);
		// strategy.setSuperEntityColumns("id"); 注释掉可以在表结构生成主键
		// strategy.setControllerMappingHyphenStyle(true);

		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

	/**
	 * 获取工程所在目录（注意：与当前文件所在位置有关）
	 * @return
	 */
	public static String getParentProjectPath() {
		String curPath = CodeGenerator.class.getClassLoader().getResource("").getFile();
		File curDir = new File(curPath);
		return curDir.getParentFile().getParentFile().getParentFile().getParent();
 
	}
	
	/**
	 * 包 转换 为路径，末尾范围“/”
	 * @param fullPackage
	 * @return
	 */
	public static String packageToPath(String fullPackage) {
		return fullPackage.replaceAll("\\.", Matcher.quoteReplacement(File.separator)) + File.separator;
	}
}