package cn.etl.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

public class PropertyFile {

    private static final Logger logger = LoggerFactory.getLogger(PropertyFile.class);
    private static Properties dbProps;
    private static String confPath;

    private static synchronized void init_prop(String clientId4Path) {
        dbProps = new Properties();
        InputStream fileInputStream = null;

        try {
            if (isExist(MessageConstant.LINUX_PATH + clientId4Path)) {
                confPath =
                        MessageConstant.LINUX_PATH + (StringUtils.isNotBlank(clientId4Path) ?
                                clientId4Path + File.separator :
                                "");
                fileInputStream = new FileInputStream(confPath + MessageConstant.SMS_EMAIL_CONFIG);
            } else if (isExist(MessageConstant.WINDOWS_PATH + clientId4Path)) {
                confPath =
                        MessageConstant.WINDOWS_PATH + (StringUtils.isNotBlank(clientId4Path) ?
                                clientId4Path + File.separator :
                                "");
                fileInputStream = new FileInputStream(confPath + MessageConstant.SMS_EMAIL_CONFIG);
            } else {
                String path = PropertyFile.class.getClassLoader().getResource("conf").getPath();
                if (path == null) {
                    throw new NullPointerException(new StringBuilder("配置路径不存在,至少在以下路径需存在配置文件:\r\n")
                            .append(MessageConstant.LINUX_PATH).append("\r\n")
                            .append(MessageConstant.WINDOWS_PATH).append("\r\n")
                            .append(PropertyFile.class.getClassLoader().getResource(""))
                            .append("/conf/").toString());
                }
                path = path.replaceAll("%20", " ") + "/";
                fileInputStream = new FileInputStream(path + MessageConstant.SMS_EMAIL_CONFIG);
                confPath = path;
            }
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(), e1);
        }

        try {
            dbProps.load(fileInputStream);
            dbProps.put(MessageConstant.LOG4J_PROPERTIES_DIR, confPath + "log4j.properties");
            addProperties2SystemEnvironment(dbProps);
        } catch (IOException e) {
            logger.error("不能读取数据库配置文件, 请确保dts-client.properties在CLASSPATH指定的路径中!", e);
        }
    }

    public static Properties getProprties(String profile) {
        Properties props = new Properties();
        InputStream fileInputStream = null;

        try {
            if (isExist(MessageConstant.LINUX_PATH + profile)) {
                confPath =
                        MessageConstant.LINUX_PATH + (StringUtils.isNotBlank(profile) ?
                                profile :
                                "");
                fileInputStream = new FileInputStream(confPath);
            } else if (isExist(MessageConstant.WINDOWS_PATH + profile)) {
                confPath =
                        MessageConstant.WINDOWS_PATH + (StringUtils.isNotBlank(profile) ?
                                profile :
                                "");
                fileInputStream = new FileInputStream(confPath);
            } else {
                String path = PropertyFile.class.getClassLoader().getResource("conf").getPath();
                if (path == null) {
                    throw new NullPointerException(new StringBuilder("配置路径不存在,至少在以下路径需存在配置文件:\r\n")
                            .append(MessageConstant.LINUX_PATH).append("\r\n")
                            .append(MessageConstant.WINDOWS_PATH).append("\r\n")
                            .append(PropertyFile.class.getClassLoader().getResource(""))
                            .append("/conf/").toString());
                }
                path = path.replaceAll("%20", " ") + "/";
                fileInputStream = new FileInputStream(path + profile);
                confPath = path;
            }
        } catch (FileNotFoundException e1) {
            logger.error(e1.getMessage(), e1);
        }

        try {
            props.load(fileInputStream);
            //props.put(MessageConstant.LOG4J_PROPERTIES_DIR, confPath + "log4j.properties");
            //addProperties2SystemEnvironment(props);
        } catch (IOException e) {
            logger.error("不能读取数据库配置文件, 请确保dts-client.properties在CLASSPATH指定的路径中!", e);
        }
        return props;
    }

    public static Properties getProps(String clientId4Path) {
        if (dbProps == null) init_prop(clientId4Path);
        return dbProps;
    }

    public static String getConfPath() {
        return confPath;
    }

    public static String getCurDateTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// "yyyyMMddHHmmss"
        return df.format(new Date());
    }

    public static boolean isExist(String filePath) {
        return new File(filePath).exists();
    }

    public static String getValue(String name, String defvalue) {
        String value = PropertyFile.getProps("").getProperty(name);
        if (value != null && value.length() > 0) {
            return value;
        } else {
            return defvalue;
        }
    }

    public static boolean getBoolValue(String name, boolean defvalue) {
        String value = PropertyFile.getProps("").getProperty(name);
        if ("true".equals(value)) {
            return true;
        } else if ("false".equals(value)) {
            return false;
        } else {
            return defvalue;
        }
    }

    public static long getLongValue(String name, long defvalue) {
        String value = PropertyFile.getProps("").getProperty(name);
        if (value != null) {
            return Long.valueOf(value);
        } else {
            return defvalue;
        }
    }

    /**
     * 将属性文件中的配置项加载进系统环境变量
     *
     * @param properties
     */
    private static void addProperties2SystemEnvironment(Properties properties) {
        Enumeration<Object> props = properties.keys();
        while (props.hasMoreElements()) {
            String key = (String) props.nextElement();
            System.setProperty(key, (String) properties.get(key));
        }
    }

    public static void main(String[] args) {
        System.out.println(PropertyFile.getBoolValue("dc.delctl.isopen", true));
    }

}
