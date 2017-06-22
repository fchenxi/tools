package cn.wonhigh.dc.etl.util;

/**
 * 消息属性名相关的常量类
 * 
 * @author wang.w
 * 
 */
public class MessageConstant {

	/** 属性文件存放在linux上的路径 */
	public static final String LINUX_PATH = "/etc/";

	/** 属性文件存放在windows上的路径 */
	public static final String WINDOWS_PATH = "D:/";

	/**属性文件名 */
	public static final String SMS_EMAIL_CONFIG = "sms-email-config.properties";
	
	public static final String LUCKY_GIFT_CONFIG = "luckyGift.properties";
	
	public static final String LOG4J_PROPERTIES_DIR = "log4j.properties.path";
	
	
	/**任务xml文件存放路径 */
	public static final String LINUX_CONFIG_TASK_XML_PATH = "/data/wonhigh/dc/client";
	
	/**任务xml存放路径 */
	public static final String WINDOW_CONFIG_TASK_XML_PATH = "D:/data/wonhigh/dc/client";
	
	/**任务xml文件存放路径 */
	public static final String LINUX_SQOOP_TASK_XML_PATH = "/data/wonhigh/dc/client/task/sqoop";
	
	/**任务xml存放路径 */
	public static final String WINDOW_SQOOP_TASK_XML_PATH = "D:/data/wonhigh/dc/client/task/sqoop";
	
	/**任务shell文件存放路径 */
	public static final String LINUX_SHELL_FILE_ROOT_PATH = "/data/wonhigh/dc/client/task/shell";
	
	/**任务shell存放路径 */
	public static final String WINDOW_SHELL_FILE_ROOT_PATH = "D:/data/wonhigh/dc/client/task/shell";
	
	/**任务xml文件存放路径 */
	public static final String LINUX_SQOOP_PRO_TASK_XML_PATH = "/data/wonhigh/dc/client/task/sqooppro";
	
	/**任务xml存放路径 */
	public static final String WINDOW_SQOOP_PRO_TASK_XML_PATH = "D:/data/wonhigh/dc/client/task/sqooppro";
	
	/**任务xml文件存放路径 */
	public static final String LINUX_DB_XML_PATH = "/data/wonhigh/dc/client/db/";
	
	/**任务xml存放路径 */
	public static final String WINDOW_DB_XML_PATH = "D:/data/wonhigh/dc/client/db/";
	/**主机ip */
	public static final String IS_OPEN_CONFIG_MONITOR = "config.monitor";
	/**主机ip */
	public static final String ACTIVE_MQ_HOST = "activemq.host";
	/**配置文件扫描心跳时间*/
	public static final String CONFIG_SCAN_TIME = "config.scantime";
	
	/**用户名 */
	public static final String ACTIVE_MQ_USER_NAME = "activemq.username";
	
	/**DEBUG   开关 */
	public static final String DEBUG = "dc-client.debug";
	
	/**用户密码 */
	public static final String ACTIVE_MQ_PASSWORD = "activemq.password";
	
	/**sqoop导入 */
	public static final Integer SQOOP_IMPORT = 100;
	
	/**sqoop导出 */
	public static final Integer SQOOP_EXPORT = 200;
	
	/**SQOOP EVAL */
	public static final Integer SQOOP_EVAL = 20;
	
	/**hive数据库用户 dc_retail_mdm*/
	public static final String DC_RETAIL_MDM_STR = "dc_retail_mdm";
	
	/**hive数据库用户 dc_retail_pos*/
	public static final String DC_RETAIL_POS_STR = "dc_retail_pos";
	
	/**hive数据库用户 dc_retail_mps*/
	public static final String DC_RETAIL_MPS_STR = "dc_retail_mps";
	
	/**hive数据库用户 dc_retail_gms*/
	public static final String DC_RETAIL_GMS_STR = "dc_retail_gms";
	
	/**hive数据库用户 dc_retail_pms*/
	public static final String DC_RETAIL_PMS_STR = "dc_retail_pms";
	
	/**hive数据库用户 dc_retail_fms*/
	public static final String DC_RETAIL_FMS_STR = "dc_retail_fms";
	
	
	/**消息发送队列 */
	public static final String DC_SCHEDULER_JOB_QUEUE = "cn.belle.retail.scheduler.jobExecStateConsumer.queue";
	/**消息发送队列 */
	public static final String DC_SCHEDULER_TRIGGERMSG_QUEUE = "cn.belle.retail.scheduler.TriggersMsgConsumer.queue";
  /** Hive kerberos principal */
  public static final String DC_HIVE_PRINCIPAL = "dc.hive.principal";
	
	/**  trans*/
	public static final String TRANSCATION_HISTORY_LOG ="transaction_history_log";
	public static final String VERIFY ="_verify";
	/** 64M 效率更高  */
	private static final String STRIPE_SIZE = "'67108864'";

	/** 桶的个数，默认是10  */
	public static final String BUCKETS_SIZE = "10";


	public static final String ODS_UPDATE_TIME ="ods_update_time";
	public static final String YW_UPDATE_TIME ="yw_update_time";
	public static final String PG_UPDATE_TIME ="yw_update_time";

	public static final String ODS_UPDATE_TIME_MSG =ODS_UPDATE_TIME + " timestamp COMMENT 'ODS 入库时间',";

	public static final String PG_UPDATE_TIME_MSG =PG_UPDATE_TIME + "  IS 'pg 入库时间';";
	public static final String HIVE_UPDATE_TIME ="from_unixtime(unix_timestamp(),\'yyyy-MM-dd HH:mm:ss\')";
	public static final String SRC_UPDATE_TIME ="src_update_time";
	public static final String SYNCTIMECOLUMN = "update_time";
	public static final String PARTITION_DATE = "partition_date";
	public static final String SRC_UPDATE_TIME_MSG =SRC_UPDATE_TIME + " timestamp COMMENT 'SRC 入库时间',";
	public static final String sTranscation_History_log = "seq_no,capture_event_uuid,dml_type,db_name,db_source_name,table_name,id_column_name,id_column_value,update_timestamp_column_name,update_timestamp_column_value, capture_time";
	/** ORC 格式，表TBLPROPERTIES的信息  */
	public static String TBLPROPERTIES =" TBLPROPERTIES ( "
			+ " 'transactional'='true',"
			+ " 'orc.compress'='SNAPPY',"
			+ System.getProperty("line.separator")
			+ " 'orc.create.index'='true',"
			+ "'orc.stripe.size'=" + STRIPE_SIZE
			+ ");";
	/**
	 * 密码加密秘钥key
	 */
	public static final String PWD_ENCRYPTION_KEY = "pwd.encryption.key";
	
	/**
	 * 密码加密秘钥值
	 */
	public static String PWD_ENCRYP_VALUE = null;
	
	/**
	 * 密码加密开关
	 */
	public static final String PWD_ON_OFF_KEY = "pwd.encryption.on.off";
	
	/**
	 * 密码加密开关值
	 */
	public static boolean PWD_ON_OFF_VALUE = false;	
	
	/**
	 * Hive重复主键值核查--键
	 */
	public static String TAB_DUP_PRIMARY_CONFIG_KEY = "tab.dup.primary.config.key";	
	
	/**
	 * Hive重复主键值核查--值
	 */
	public static Integer TAB_DUP_PRIMARY_CONFIG_VALUE = 365;		
}
