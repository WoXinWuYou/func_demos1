/**
 * Copyright &copy; 2015-2020 <a href="#">HuagaoSoft</a> All rights reserved.
 */
package com.lmj.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.common.collect.Maps;

/**
 * 全局配置类
 * @author gtg
 * @version 2014-06-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	
	/**
	 * 想要修改的属性文件路径
	 */
	private static String modifyPropertiesFilePath = "/application.properties";
	
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader loader = new PropertiesLoader("application.properties");

	/**
	 * 显示/隐藏
	 */
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	/**
	 * 是/否
	 */
	public static final String YES = "1";
	public static final String NO = "0";
	
	/**
	 * 对/错
	 */
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";
	
	/**
	 * 系统类型标识：管廊/泵站/能源机场
	 */
	public static final String SYS_TYPE_GL = "1";
	public static final String SYS_TYPE_BZ = "2";
	public static final String SYS_TYPE_NY = "23c1ce93ce8214af3863ba3adcfef79ab1";
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 * @see ${fns:getConfig('adminPath')}
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
	
	/**
	 * 获取管理端根路径
	 */
	public static String getAdminPath() {
		return getConfig("adminPath");
	}
	
	/**
	 * 获取前端根路径
	 */
	public static String getFrontPath() {
		return getConfig("frontPath");
	}
	
	/**
	 * 获取URL后缀
	 */
	public static String getUrlSuffix() {
		return getConfig("urlSuffix");
	}
	
	/**
	 * 是否是演示模式，演示模式下不能修改用户、角色、密码、菜单、授权
	 */
	public static Boolean isDemoMode() {
		String dm = getConfig("demoMode");
		return "true".equals(dm) || "1".equals(dm);
	}
	
	/**
	 * 在修改系统用户和角色时是否同步到Activiti
	 */
	public static Boolean isSynActivitiIndetity() {
		String dm = getConfig("activiti.isSynActivitiIndetity");
		return "true".equals(dm) || "1".equals(dm);
	}
    
	/**
	 * 页面获取常量
	 * @see ${fns:getConst('YES')}
	 */
	public static Object getConst(String field) {
		try {
			return Global.class.getField(field).get(null);
		} catch (Exception e) {
			// 异常代表无配置，这里什么也不做
		}
		return null;
	}

	/**
	 * 获取上传文件的根目录
	 * @return
	 */
	public static String getUserfilesBaseDir() {
		String dir = getConfig("userfiles.basedir");
		if (StringUtils.isBlank(dir)){
			try {
				dir = Global.class.getClassLoader().getResource("").getPath();
			} catch (Exception e) {
				return "";
			}
		}
		if(!dir.endsWith("/")) {
			dir += "/";
		}
//		System.out.println("userfiles.basedir: " + dir);
		return dir;
	}
	
	public static String getFileLanPath() {
		return USERFILES_BASE_URL.replace("/", "");
	}
	
	// 插件及app安装包存放路径
	public static String getPluginPath() {
		return getConfig("plugin_path").replace("/", "");
	}  
	
    /**
     * 获取工程路径
     * @return
     */
    public static String getProjectPath(){
    	// 如果配置了工程路径，则直接返回，否则自动获取。
		String projectPath = Global.getConfig("projectPath");
		if (StringUtils.isNotBlank(projectPath)){
			return projectPath;
		}
		try {
			File file = new DefaultResourceLoader().getResource("").getFile();
			if (file != null){
				while(true){
					File f = new File(file.getPath() + File.separator + "src" + File.separator + "main");
					if (f == null || f.exists()){
						break;
					}
					if (file.getParentFile() != null){
						file = file.getParentFile();
					}else{
						break;
					}
				}
				projectPath = file.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return projectPath;
    }
    
    /**
	 * 写入properties信息
	 * 
	 * @param key
	 *            名称
	 * @param value
	 *            值
	 */
	public static void modifyConfig(String key, String value) {
		FileOutputStream outputFile = null;
		try {
			// 从输入流中读取属性列表（键和元素对）
			Properties prop = getProperties(modifyPropertiesFilePath);
			prop.setProperty(key, value);
			String path = Global.class.getResource(modifyPropertiesFilePath).getPath();
			outputFile = new FileOutputStream(path);
			prop.store(outputFile, "modify");
			outputFile.close();
			outputFile.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回　Properties
	 * 文件路径使用Spring Resource格式.
	 * @param propertiesFilePath 
	 * @return
	 */
	public static Properties getProperties(String propertiesFilePath){
		Properties prop = new Properties();
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		InputStream is = null;
		try {
			Resource resource = resourceLoader.getResource(propertiesFilePath);
			is = resource.getInputStream();
			prop.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return prop;
	}



	/**
	 * 平台类型
	 */
	public static String SYS_TYPE= Global.getConfig("sysType");//"1";//1：管廊平台；2：海绵机场；'23c1ce93ce8214af3863ba3adcfef79ab1'：能源机场

	public static String getSysType() {
		return SYS_TYPE;
	}

	public static void setSysType(String sysType) {
		SYS_TYPE = sysType;
	}

}
