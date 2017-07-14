package cn.tools.excel;

import cn.tools.util.DateUtils;
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
    public void exportExcelWithoutTitleByTemplateTest() {
        List<ExcelModel> excelModels = new ArrayList<>();
        excelModels.add(new ExcelModel("dc_retail_pos", "order_main", new Date(), "1",
                "这是备注"));
        ExcelUtil.getInstance().exportExcelByTemplate(null, "/withoutTitleTemplate.xls",
                "E:/dcWithoutTitleTemplateExcel.xls", excelModels,
                ExcelModel.class, true);
    }

    @Test
    public void exportExcelByTemplateTest() {
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
    public void readWithoutTitleExcelByStreamTest() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File
                ("E:\\dcWithoutTitleTemplateExcel.xls"));
        List<Object> stus;
        try {
            stus = ExcelUtil.getInstance().readExcelByStream(inputStream, ExcelModel.class, 1,
                    0);
            for (Object obj : stus) {
                ExcelModel jobHandleRequire = (ExcelModel) obj;
                System.out.println(jobHandleRequire);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void readTemplateExcelByPathTest() {
        List<Object> stus = ExcelUtil.getInstance().readExcelByPath(
                "E:\\dcTemplateExcel.xls", ExcelModel.class, 1,
                2);
        for (Object obj : stus) {
            ExcelModel jobHandleRequire = (ExcelModel) obj;
            System.out.println(jobHandleRequire);
        }
    }
}
