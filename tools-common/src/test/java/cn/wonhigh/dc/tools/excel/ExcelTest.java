package cn.wonhigh.dc.tools.excel;

import cn.wonhigh.dc.tools.util.DateUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/**
 * @author tong.cx
 * @version 0.0.1
 * @usage 导入导出excel
 * @datetime 2016/3/29 10:31
 * @copyright wonhigh.cn
 */

public class ExcelTest {

    @Test
    public void testExportExcelByTemplate() {
        List<ExcelModel> excelModels = new ArrayList<>();
        excelModels.add(new ExcelModel("dc_retail_pos", "order_main", new Date(), "1",
                "这是备注"));
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("title", "DC调度系统任务报表");
        datas.put("date", DateUtils.getCurDate());
        datas.put("dep", "www.wonhigh.cn");
        ExcelUtil.getInstance().exportExcelByTemplate(datas, "/monJobTemplate.xls",
                "E:/dcTemplateExcel.xls", excelModels,
                ExcelModel.class, true);
    }

    @Test
    public void testReadExcelByStream() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File
                ("E:\\dcTemplateExcel.xls"));
        List<Object> stus;
        try {
            stus = ExcelUtil.getInstance().readExcelByStream(inputStream, ExcelModel.class, 1,
                    2);
            for (Object obj : stus) {
                ExcelModel jobHandleRequire = (ExcelModel) obj;
                System.out.println(jobHandleRequire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testReadTemplateExcelByPath() {
        List<Object> stus = ExcelUtil.getInstance().readExcelByPath(
                "E:\\dcTemplateExcel.xls", ExcelModel.class, 1,
                2);
        for (Object obj : stus) {
            ExcelModel jobHandleRequire = (ExcelModel) obj;
            System.out.println(jobHandleRequire);
        }
    }
}
