package cn.wonhigh.dc.etl;


import cn.wonhigh.dc.etl.ss.ui.ETLConfigUI;
import org.eclipse.swt.widgets.Display;

/**
 * @author      chase
 * @version     0.1.0
 * @datetime    2016/6/13 17:30cn
 * @copyright   wonhigh.
 */
public class BootStrap {
    private final String EXIT_YES = "确定";
    private final String EXIT_NO = "取消";
    private final String EXIT_MESSAGE = "你真的想退出吗?";
    private final String EXIT_WARN = "警告";
    private static BootStrap instance;
    private BootStrap() {
    }
    public static synchronized BootStrap getInstance() {
        if (instance == null) {
            instance = new BootStrap();
        }
        return instance;
    }
    public synchronized void start() {
        try {
            ETLConfigUI window = new ETLConfigUI();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public synchronized void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        BootStrap.getInstance().start();
    }
}
