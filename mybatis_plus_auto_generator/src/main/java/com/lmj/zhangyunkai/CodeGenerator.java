package com.lmj.zhangyunkai;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
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
		String dbUrl = "jdbc:mysql://119.45.146.19:3306/test?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=Hongkong&allowMultiQueries=true";
		String dbUserName = "root";
		String dbPassword = "lmj123456";


		// 表名忽略前缀
		String tablePrefix = "t";
		// 目标表正则
		String tableLike = "student"; // 对应子模块名
		// 模块名称
		String modubleName = ""; // 对应父模块名
		// 是否需要导出
		Boolean isExport = Boolean.FALSE;
		// 是否有BigDecimal类型字段
		Boolean hasBigDecimal = Boolean.FALSE;

		// ********* 可修改配置 end **********

		// 项目文件夹名称
		String projectPackage = "func_demos1";
		// 目标服务
		String whichServe = "mybatis_plus_auto_generator";

		// 目标基础包名
		String packageName = "com.lmj.student";

		// 前端view生成路径
		String baseLocation = "F:/codeGen/";

		// ******* 以下勿动 *******
		// controller父类
		String superControllerClass = "com.lmj.base.BaseController";
		// request父类
		String requestSuperClass = "com.lmj.base.BaseRequest";
		// 转换工具类
		String commonUtilsClass = "com.lmj.base.CommonUtils";
		// 统一返回值类
		String responseClass = "com.lmj.base.response.R";
		// 导出工具类
		String exportUtilsClass = null;

		String systemUserClass = "com.lmj.sys.entity.User";

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String curPath = CodeGenerator.class.getClassLoader().getResource("").getFile();
		File curDir = new File(curPath);
		curPath = curDir.getParentFile().getParentFile().getParentFile().getParent();

		curPath = curPath + File.separator + projectPackage + File.separator + whichServe;
		String projectPath = curPath;

		gc.setOutputDir(projectPath + "/src/main/java");
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

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setModuleName(modubleName);
		pc.setParent(packageName);
		pc.setEntity("entity");
		pc.setService("service");
		pc.setServiceImpl("service.impl");
		pc.setController("controller");
		pc.setMapper("dao");
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
				this.setMap(map);
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName() + "/"
						+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});

		// 自定义controller配置（位置异常，重新调整）
//		focList.add(new FileOutConfig("/templates/controller.java.ftl") {
//			@Override
//			public String outputFile(TableInfo tableInfo) {
//				// 使用controller服务所在地址，替换原来输出地址
//				String expand = gc.getOutputDir().replace(whichServe, controllerServer) + "/"
//						+ packageName.replaceAll("\\.", "/") + "/controller" + "/" + modubleName;
//				String entityFile = String.format((expand + File.separator + "%s" + ".java"),
//						tableInfo.getControllerName());
//				return entityFile;
//			}
//		});

		// 自定义request配置
		focList.add(new FileOutConfig("/templates/request.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = gc.getOutputDir() + "/" + packageName.replaceAll("\\.", "/") + "/" + modubleName
						+ "/request";
				String entityFile = String.format((expand + File.separator + "%s" + "Request.java"),
						tableInfo.getEntityName());
				return entityFile;
			}
		});

		// 自定义vo配置
		focList.add(new FileOutConfig("/templates/vo.java.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				String expand = gc.getOutputDir() + "/" + packageName.replaceAll("\\.", "/") + "/" + modubleName
						+ "/vo";
				String entityFile = String.format((expand + File.separator + "%s" + "VO.java"),
						tableInfo.getEntityName());
				return entityFile;
			}
		});

		/* >>>>>> 前端代码生成 begin <<<<<<< */

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
		/*focList.add(new FileOutConfig("/templates/view/services/index.js.ftl") {
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
		mpg.setTemplate(new TemplateConfig().setXml(null).setController("templates/controller.java"));

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

}
