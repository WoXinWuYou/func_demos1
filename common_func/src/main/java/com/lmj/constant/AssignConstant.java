package com.lmj.constant;

/**
 * 监管交办 常量
 * @author 李梦杰
 * @date 2020-07-16
 */
public class AssignConstant {
	/**监管交办-班组类型 2-值守*/
	public static final String TEAM_TYPE_GUARD = "2";
	
	/**监管交办-班组类型 1-值班*/
	public static final String TEAM_TYPE_DUTY = "1";
	
	/**班组成员类型 1-普通成员*/
	public static final String TEAM_MEMBER_TYPE_COMMON = "1";
	
	/**班组成员类型 2-组长*/
	public static final String TEAM_MEMBER_TYPE_HEAD = "2";
	
	/**班组成员类型 3-领导*/
	public static final String TEAM_MEMBER_TYPE_LEADER = "3";
	/*即时值班组又是值守组*/
	public static final String TEAM_TYPE_GUARD_AND_DUTY = "0";
	/*其他身份*/
	public static final String TEAM_TYPE_OTHER = "-1";

	
	/** === 任务类型:1-区域传输预警;2-国控站点报警;3-国控站周边站点报警;4-一般空气站点报警;5-扬尘;6-秸秆;7-VOC;8-工业源;9-分表记电;10-遥感;11-OBD;12-非道路===*/
    /** 任务类型:1-区域传输预警 */
    public final static String TASK_TYPE_AREA_TRANSPORT = "1";
    /** 任务类型:2-国控站点报警 */
    public final static String TASK_TYPE_GUO_STATION = "2";
    /** 任务类型:3-国控站周边站点报警 */
    public final static String TASK_TYPE_GUO_STATION_AROUND = "3";
    /** 任务类型:4-一般空气站点报警 */
    public final static String TASK_TYPE_COMMON_STATION = "4";
    /** 任务类型:5-扬尘 */
    public final static String TASK_TYPE_YC = "5";
    /** 任务类型:6-秸秆 */
    public final static String TASK_TYPE_STRAW = "6";
    /** 任务类型:7-VOC*/
    public final static String TASK_TYPE_VOC = "7";
    /** 任务类型:8-工业源*/
    public final static String TASK_TYPE_PS = "8";
    /** 任务类型:9-分表记电*/
    public final static String TASK_TYPE_FBJD = "9";
    /** 任务类型:10-遥感*/
    public final static String TASK_TYPE_YG = "10";
    /** 任务类型:11-OBD*/
    public final static String TASK_TYPE_OBD = "11";
    /** 任务类型:12-非道路*/
    public final static String TASK_TYPE_NON_ROAD = "12";
    /** 任务类型:13-信访*/
    public final static String TASK_TYPE_LETTER = "13";

    /** === 任务交办状态 ===*/
    /** 任务交办状态：未交办-1 */
    public final static String TASK_STATUS_NOT_ASSIGN = "1";
    /** 任务交办状态：上报 -2*/
    public final static String TASK_STATUS_REPORT = "2"; // 已上报
    /** 任务交办状态：派发-3 */
    public final static String TASK_STATUS_DISTRIBUTE  = "3"; // 已派发
    /** 任务交办状态：处理中-4 */
    public final static String TASK_STATUS_DISPOSING = "4"; // 处理中
    /** 任务交办状态：已处理-5*/
    public final static String TASK_STATUS_DISPOSED = "5"; // 已处理
    /** 任务交办状态：重新处理-6 */
    public final static String TASK_STATUS_AGAIN = "6"; // 重新处理
    /** 任务交办状态：忽略-7 */
    public final static String TASK_STATUS_IGNORE = "7"; // 已忽略
    /** 任务交办状态：已完结-8 */
    public final static String TASK_STATUS_FINISH = "8"; // 已完结

    /** === 任务审核结果 ===*/
    /** 通过 */
    public final static Integer APPROVAL_RESULT_PASS = 1;
    /** 未通过 */
    public final static Integer APPROVAL_RESULT_NOT_PASS = 2;

    public final static String APPROVAL_RESULT_PASSING= "是";

    public final static String APPROVAL_RESULT_DISTRIBUTE= "派发";

    public final static String APPROVAL_RESULT_IGNORE= "忽略";
    /**
     * 报警分析结果表 报警类型
     */
    public final static String APPROVAL_ALARM_TYPE_GUO= "alarm_guo";
    /*交办流程流转审核标识*/
    public final static String CHECK_BS_PASS = "1";
    public final static String CHECK_BS_NOT_PASS = "2";
    /*交办流程流转条件标识*/
    public final static String CONDITION_BS_DEFAULT = "1";

}
