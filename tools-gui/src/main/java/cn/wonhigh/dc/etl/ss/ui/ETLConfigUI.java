package cn.wonhigh.dc.etl.ss.ui;

import cn.wonhigh.dc.client.common.util.CreateNewTableConfUtils;
import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;

import java.util.HashMap;
import java.util.Map;

public class ETLConfigUI extends ApplicationWindow {
    private Text tfBeginNo;
    private Text tfDbConf;
    private Text tfTbPath;
    private Text tfSourceXmlPath;
    private Text tfXmlPath;
    private Text tfExcelPath;
    private Text tfSqlPath;
    private Combo cboxSys;
    /**
     * 数据库与ID字段
     */
    private Map<String, String> dbConf;
    private final String GENERATE_SUCCESS = "生成脚本成功! \n";
    private final String GENERATE_FAILED = "生成脚本失败! \n Caused by : ";
    private final String ETL_HELP_MSG = "\t\t帮助说明：\t\t\n" +
            "\t\t\t\n" +
            "（1）路径格式\t\n" +
            "数据库配置文件\t： 数据库配置xml文件\t\n" +
            "所需表的文件\t： 配置表增量和全量txt文件\t\n" +
            "源xml路径\t： 读取全部xml文件目录\t\n" +
            "目标xml路径\t： 生成xml配置目录\t\n" +
            "目标excel路径\t： 生成excel配置目录\t\n" +
            "目标sql路径\t： 生成sql脚本目录\t\n" +
            "（2）所需表的文件格式\t\n" +
            " * -i  新增表，增量或者全量\t\n" +
            " * -u  新增现有表字段\t\n" +
            " * 格式：必须如下：\t\n" +
            " * orcl-employee_info-temp_update-0-u\t\n" +
            " ";

    /**
     * Create the application window.
     */
    public ETLConfigUI() {
        super(null);
        setShellStyle(SWT.MIN);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }
    /**
     * Button Listen PressDown Event
     */
    /**
     * File Choose
     *
     * @param e
     */
    private void BtDbConfActionPerformed(SelectionEvent e) {
        // TODO add your code here
        FileDialog fd = new FileDialog(this.getShell(), SWT.OPEN);
        String dbConfDirPath = fd.open();

        tfDbConf.setText(dbConfDirPath);
        dbConf = CreateNewTableConfUtils.getSystemDBConf(dbConfDirPath);
        cboxSys.removeAll();
        for (Map.Entry<String, String> entry : dbConf.entrySet()) {
            cboxSys.add(entry.getKey());
            cboxSys.select(cboxSys.getSelectionIndex() + 1);
        }
    }

    private void BtTbPathActionPerformed(SelectionEvent e) {
        // TODO add your code here
        FileDialog fd = new FileDialog(this.getShell(), SWT.OPEN);
        String tbPath = fd.open();
        tfTbPath.setText(tbPath);
    }

    private void BtSourceXmlPathActionPerformed(SelectionEvent e) {
        // TODO add your code here
        DirectoryDialog folderdlg = new DirectoryDialog(this.getShell(), SWT.OPEN);
        String sourceXmlPath = folderdlg.open();
        if (!StringUtils.isEmpty(tfBeginNo.getText())) {
            int beginNo = CreateNewTableConfUtils.getSerialNum(sourceXmlPath);
            tfBeginNo.setText(String.valueOf(beginNo));
        }
        tfSourceXmlPath.setText(sourceXmlPath);
        tfBeginNo.setText(String.valueOf(CreateNewTableConfUtils.getSerialNum(sourceXmlPath)));
    }

    private void BtXmlPathActionPerformed(SelectionEvent e) {
        // TODO add your code here
        DirectoryDialog folderdlg = new DirectoryDialog(this.getShell(), SWT.OPEN);
        String xmlPath = folderdlg.open();
        tfXmlPath.setText(xmlPath);
    }

    private void BtExcelPathActionPerformed(SelectionEvent e) {
        // TODO add your code here
        DirectoryDialog folderdlg = new DirectoryDialog(this.getShell(), SWT.OPEN);
        String excelPath = folderdlg.open();
        tfExcelPath.setText(excelPath);
    }

    private void BtSqlPathActionPerformed(SelectionEvent e) {
        // TODO add your code here
        DirectoryDialog folderdlg = new DirectoryDialog(this.getShell(), SWT.OPEN);
        String sqlPath = folderdlg.open();
        tfSqlPath.setText(sqlPath);
    }

    private void ExitKeyPressed(SelectionEvent e) {
        // TODO add your code here
        MessageBox messagebox = new MessageBox(this.getShell(), SWT.YES
                | SWT.NO);
        messagebox.setText("Exit");
        messagebox.setMessage("你确定要退出吗？");
        int val = messagebox.open();
        if (val == SWT.YES) {
            getShell().close();
        }

    }

    private void LbHelpActionPerformed(MouseEvent e) {
        ToolTip toolTip = new ToolTip(this.getShell(), SWT.YES);
        toolTip.setMessage(this.ETL_HELP_MSG);
        toolTip.setVisible(true);
    }


    private void GenerateKeyPressed(SelectionEvent e) {
        // TODO add your code here
        Display display = this.getShell().getDisplay();

        if (!display.isDisposed()) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Map<String, String> confParams = new HashMap<String, String>();
                    // D:/data/wonhigh/dc/client/db
                    confParams.put("sorceDbConfPath", tfDbConf.getText());
                    // D:/data/wonhigh/dc/client/task/sqoop
                    confParams.put("latestXmlDirPath", tfSourceXmlPath.getText());
                    // D:/data/wonhigh/tb/retail_pos.txt
                    confParams.put("biMdm", tfTbPath.getText());
                    confParams.put("targetXMLDirPath", tfXmlPath.getText());
                    confParams.put("targetExcelDirPath", tfExcelPath.getText());
                    confParams.put("targetSqlDirPath", tfSqlPath.getText());
                    confParams.put("sourceDbId", dbConf.get(cboxSys.getText()));
                    // confParams.put("sourceDbId", dbConf.get(cboxSys.getSelectionIndex() + 1));
                    confParams.put("serialNum", tfBeginNo.getText());
                    String message = CreateNewTableConfUtils.createTargetFile(confParams);
                    MessageBox messagebox = null;
                    messagebox = new MessageBox(ETLConfigUI.this.getShell(), SWT.YES);
                    if (message != null && !"".equals(message)) {
                        messagebox.setMessage(ETLConfigUI.this.GENERATE_FAILED + message);
                        messagebox.open();
                    } else {
                        messagebox.setMessage(ETLConfigUI.this.GENERATE_SUCCESS);
                        messagebox.open();
                    }
                }
            };
            display.syncExec(runnable);
        }
    }

    /**
     * Create contents of the application window.
     *
     * @param parent
     */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(null);

        Label lbSys = new Label(container, SWT.NONE);
        lbSys.setBounds(113, 41, 61, 17);
        lbSys.setText("业务系统 :");
        {
            Label lbBeginNo = new Label(container, SWT.NONE);
            lbBeginNo.setBounds(308, 44, 61, 17);
            lbBeginNo.setText("起始编号 :");
        }
        {
            cboxSys = new Combo(container, SWT.NONE);
            cboxSys.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                }
            });
            cboxSys.setBounds(180, 33, 88, 25);
        }
        {
            tfBeginNo = new Text(container, SWT.BORDER);
            tfBeginNo.setBounds(375, 35, 73, 23);
        }
        {
            Label lbDbConf = new Label(container, SWT.NONE);
            lbDbConf.setBounds(66, 89, 95, 17);
            lbDbConf.setText("数据库配置文件 : ");
        }
        {
            tfDbConf = new Text(container, SWT.BORDER);
            tfDbConf.setBounds(180, 83, 189, 23);
        }

        Button btDbConf = new Button(container, SWT.NONE);
        btDbConf.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                BtDbConfActionPerformed(e);
            }
        });
        btDbConf.setBounds(408, 79, 80, 27);
        btDbConf.setText("浏览");
        {
            Label lbTbPath = new Label(container, SWT.NONE);
            lbTbPath.setBounds(66, 124, 88, 17);
            lbTbPath.setText("所需表的文件 : ");
        }
        {
            tfTbPath = new Text(container, SWT.BORDER);
            tfTbPath.setBounds(180, 121, 189, 23);
        }
        {
            Button btTbPath = new Button(container, SWT.NONE);
            btTbPath.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    BtTbPathActionPerformed(e);
                }
            });
            btTbPath.setText("浏览");
            btTbPath.setBounds(408, 117, 80, 27);
        }
        {
            Label lbSourceXmlPath = new Label(container, SWT.NONE);
            lbSourceXmlPath.setText("源XML路径 : ");
            lbSourceXmlPath.setBounds(66, 163, 88, 17);
        }
        {
            tfSourceXmlPath = new Text(container, SWT.BORDER);
            tfSourceXmlPath.setBounds(180, 160, 189, 23);
        }
        {
            Label lbXmlPath = new Label(container, SWT.NONE);
            lbXmlPath.setText("目标XML路径 : ");
            lbXmlPath.setBounds(66, 199, 88, 17);
        }
        {
            tfXmlPath = new Text(container, SWT.BORDER);
            tfXmlPath.setBounds(180, 196, 189, 23);
            tfXmlPath.setText("D:\\data\\wonhigh\\dc\\client\\target\\xml");
        }
        {
            Button btSourceXmlPath = new Button(container, SWT.NONE);
            btSourceXmlPath.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    BtSourceXmlPathActionPerformed(e);
                }
            });
            btSourceXmlPath.setText("浏览");
            btSourceXmlPath.setBounds(408, 156, 80, 27);
        }
        {
            Button btXmlPath = new Button(container, SWT.NONE);
            btXmlPath.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    BtXmlPathActionPerformed(e);
                }
            });
            btXmlPath.setText("浏览");
            btXmlPath.setBounds(408, 194, 80, 27);
        }
        {
            Label lbExcelPath = new Label(container, SWT.NONE);
            lbExcelPath.setText("目标EXCEL路径 : ");
            lbExcelPath.setBounds(66, 236, 88, 17);
        }
        {
            Label lbSqlPath = new Label(container, SWT.NONE);
            lbSqlPath.setText("目标SQL路径 : ");
            lbSqlPath.setBounds(66, 271, 88, 17);
        }
        {
            Button btGenerate = new Button(container, SWT.NONE);
            btGenerate.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    GenerateKeyPressed(e);
                }
            });
            btGenerate.setBounds(188, 308, 80, 27);
            btGenerate.setText("生成");
        }
        {
            Button btExit = new Button(container, SWT.NONE);
            btExit.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    ExitKeyPressed(e);
                }
            });
            btExit.setBounds(284, 308, 80, 27);
            btExit.setText("退出");
        }
        {
            tfExcelPath = new Text(container, SWT.BORDER);
            tfExcelPath.setBounds(180, 233, 189, 23);
            tfExcelPath.setText("D:\\data\\wonhigh\\dc\\client\\target");
        }
        {
            tfSqlPath = new Text(container, SWT.BORDER);
            tfSqlPath.setBounds(180, 268, 189, 23);
            tfSqlPath.setText("D:\\data\\wonhigh\\dc\\client\\target");
        }
        {
            Button btExcelPath = new Button(container, SWT.NONE);
            btExcelPath.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    BtExcelPathActionPerformed(e);
                }
            });
            btExcelPath.setText("浏览");
            btExcelPath.setBounds(408, 231, 80, 27);
        }
        {
            Button btSqlPath = new Button(container, SWT.NONE);
            btSqlPath.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent e) {
                    BtSqlPathActionPerformed(e);
                }
            });
            btSqlPath.setText("浏览");
            btSqlPath.setBounds(408, 266, 80, 27);
        }
        Label lbHelp = new Label(container, SWT.NONE);
        lbHelp.addMouseTrackListener(new MouseTrackAdapter() {
            @Override
            public void mouseEnter(MouseEvent e) {
                LbHelpActionPerformed(e);
            }
        });
        lbHelp.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.NORMAL));
        lbHelp.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
        lbHelp.setBounds(468, 39, 61, 17);
        lbHelp.setText("获取帮助");
        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
    }

    /**
     * Create the menu manager.
     *
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");
        return menuManager;
    }

    /**
     * Create the toolbar manager.
     *
     * @return the toolbar manager
     */
    @Override
    protected ToolBarManager createToolBarManager(int style) {
        ToolBarManager toolBarManager = new ToolBarManager(style);
        return toolBarManager;
    }

    /**
     * Create the status line manager.
     *
     * @return the status line manager
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /**
     * Launch the application.
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            ETLConfigUI window = new ETLConfigUI();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure the shell.
     *
     * @param newShell
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("DC ETL Tools");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(590, 457);
    }
}
