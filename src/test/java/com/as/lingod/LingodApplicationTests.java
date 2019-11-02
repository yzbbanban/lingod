package com.as.lingod;

import com.as.lingod.dao.FaProductLingoCalcMapper;
import com.as.lingod.dao.FaProductLingoMapper;
import com.as.lingod.domain.FaProductLingo;
import com.as.lingod.domain.FaProductLingoCalc;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LingodApplicationTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String EXCEL_XLS = ".xls";
    private static final String EXCEL_XLSX = ".xlsm";

    @Autowired
    private FaProductLingoMapper faProductLingoMapper;

    @Autowired
    private FaProductLingoCalcMapper faProductLingoCalcMapper;

    @Test
    public void addMain() {

        String path = "/Users/YZBbanban/Desktop/WD1GS-0027.xlsm";
        try {
            addMainExcel(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addMainExcel(String path) throws Exception {
        File excelFile = new File(path);//创建excel文件对象
        InputStream is = new FileInputStream(excelFile);//创建输入流对象
        checkExcelVaild(excelFile);
        Workbook workbook = getWorkBook(is, excelFile);
//        Workbook workbook = WorkbookFactory.create(is);//同时支持2003、2007、2010
//        获取Sheet数量
        int sheetNum = workbook.getNumberOfSheets();
        System.out.println("sheetNum===>" + sheetNum);
//        FormulaEvaluator formulaEvaluator = null;
//        遍历工作簿中的sheet,第一层循环所有sheet表
        Sheet sheet = workbook.getSheetAt(4);
        System.out.println("==sheet=>" + sheet.getWorkbook());
        System.out.println("表单行数：" + sheet.getLastRowNum());
        List<FaProductLingoCalc> calcList = new ArrayList<>();
        for (int cellIndex = 14; cellIndex < 38; cellIndex++) {

            //2-10
            FaProductLingoCalc faProductLingoCalc = new FaProductLingoCalc();
            //4成品，5 车缝成品 9 成品不含线外加工项目
            faProductLingoCalc.setProtype("9");

            faProductLingoCalc.setTotalpeo(getNum(sheet, 2, cellIndex));
            //PC1GS一041614_WD1GS-0044_WD1GS-0039 1  5
            //PC1GS一041614_WD1GS-0044_WD1GS-0039 2  5

            //GS0373_GS0378_GS0380_GS0391_GS0392_GS3893_GS3896_PGS281_CQ0921 1  6
            //GS0373_GS0378_GS0380_GS0391_GS0392_GS3893_GS3896_PGS281_CQ0921 2  6

            //WD1GS-0027 版别 2  typeId 9 nameId 9
            //WD1GS-0028 版别 2  typeId 9 nameId 10
            faProductLingoCalc.setName("WD1GS-0027");
            faProductLingoCalc.setNameId(9);
            faProductLingoCalc.setEdition(2);
            faProductLingoCalc.setAvaila(new BigDecimal(getNum(sheet, 9, cellIndex)));
            faProductLingoCalc.setIepoh(new BigDecimal(getNum(sheet, 8, cellIndex)));
            faProductLingoCalc.setIepohs(new BigDecimal(getNum(sheet, 10, cellIndex)));
            faProductLingoCalc.setProduction(new BigDecimal(getNum(sheet, 7, cellIndex)));
            faProductLingoCalc.setTotalallowance("" + 10);
            faProductLingoCalc.setXuph(new BigDecimal(getNum(sheet, 7, cellIndex)));
            faProductLingoCalc.setXuphs(new BigDecimal(getNum(sheet, 3, cellIndex)));
            calcList.add(faProductLingoCalc);
        }

        for (int i = 0; i < calcList.size(); i++) {
            faProductLingoCalcMapper.insert(calcList.get(i));
        }
//        Thread.sleep(10000);
    }

    private String getNum(Sheet sheet, int r, int cellIndex) {
        Row row = sheet.getRow(r);
        Cell cell = row.getCell(cellIndex);
        //数据结果
        String num = getCellValue(cell);
        return num;
    }


    @Test
    public void contextLoads() {

        String path = "/Users/YZBbanban/Desktop/WD1GS-0027.xlsm";
        try {
            readExcelInfo(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取excel数据
     *
     * @throws Exception
     */
    public List<List<String>> readExcelInfo(String url) throws Exception {
        /*
         * workbook:工作簿,就是整个Excel文档
         * sheet:工作表
         * row:行
         * cell:单元格
         */

//        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(url)));
//        支持excel2003、2007
        File excelFile = new File(url);//创建excel文件对象
        InputStream is = new FileInputStream(excelFile);//创建输入流对象
        checkExcelVaild(excelFile);
        Workbook workbook = getWorkBook(is, excelFile);
//        Workbook workbook = WorkbookFactory.create(is);//同时支持2003、2007、2010
//        获取Sheet数量
        int sheetNum = workbook.getNumberOfSheets();
        System.out.println("sheetNum===>" + sheetNum);
//        FormulaEvaluator formulaEvaluator = null;
//        遍历工作簿中的sheet,第一层循环所有sheet表
        Sheet sheet = workbook.getSheetAt(4);
        System.out.println("表单行数：" + sheet.getLastRowNum());

        //人员安排
        Map<Integer, List<FaProductLingo>> list = new HashMap<>();
        //列
        for (int cellIndex = 14; cellIndex < 38; cellIndex++) {
            List<FaProductLingo> faList = new ArrayList<>();
            Integer key = cellIndex - 2;
            //需要保存 xuhaoList 的数据
            int xuhao = 60;
            //行
            for (int rowIndex = 13; rowIndex < 38; rowIndex++) {
//                System.out.println("==rowIndex==" + rowIndex);
                Row row = sheet.getRow(rowIndex);
                Cell cell = row.getCell(cellIndex);
                //数据结果
                String num = getCellValue(cell);
                FaProductLingo fa = null;

                if (num != null && num.length() > 0) {
                    //不为 null 是添加数据：list 添加
                    fa = new FaProductLingo();
                    fa.setUsercount(key);
                    int r = xuhao;
//                    System.out.println("--rList->" + r);
                    fa.setXuhaolist("" + r);
                    //
                    int o = rowIndex - 13;
                    //PC1GS一041614_WD1GS-0044_WD1GS-0039 1  5
                    //PC1GS一041614_WD1GS-0044_WD1GS-0039 2  5

                    //GS0373_GS0378_GS0380_GS0391_GS0392_GS3893_GS3896_PGS281_CQ0921 1  6
                    //GS0373_GS0378_GS0380_GS0391_GS0392_GS3893_GS3896_PGS281_CQ0921 2  6
                    //WD1GS-0027 版别 2  typeId 9 nameId 9
                    //WD1GS-0028 版别 2  typeId 9 nameId 10
                    fa.setName("WD1GS-0027");
                    fa.setNameId(9);
                    fa.setUsercount(Double.valueOf(num).intValue());
                    fa.setEdition("" + 2);
                    fa.setAllowance("10");
                    //4成品，5车缝
                    fa.setProtype(9);
                    //CT 115+o
                    Row ctrow = sheet.getRow(115 + o);
                    Cell ct = ctrow.getCell(cellIndex);
                    //数据结果
                    String ctnum = getCellValue(ct);
                    fa.setPurect(ctnum);
                    //pro 166+o
                    Row prorow = sheet.getRow(166 + o);
                    Cell pro = prorow.getCell(cellIndex);
                    //数据结果
                    String pronum = getCellValue(pro);
                    fa.setProduction(pronum);
                    //负载
                    //load 115+o
                    Row loadrow = sheet.getRow(64 + o);
                    Cell load = loadrow.getCell(cellIndex);
                    //数据结果
                    String loadnum = getCellValue(load);
                    fa.setLoad(loadnum);

//                    fa.setCalcId(rowIndex - 12);

                    fa.setPeocount(key);
                    faList.add(fa);

                    list.put(key, faList);
                } else {
                    //当为 null 时不添加 list，list 为之前的 vo
//                    System.out.println("cellIndex==>" + cellIndex);
                    //获取最后一个 list 中的数据
                    fa = faList.get(faList.size() - 1);
                    //为空
                    String xuhaoList = fa.getXuhaolist();
//                    System.out.println("--->" + xuhaoList);
                    String[] ro = xuhaoList.split(",");
                    String re = ro[ro.length - 1];
                    //序号
                    int r = Integer.parseInt(re) + 10;
                    fa.setXuhaolist(xuhaoList + "," + r);
                }
//                switch (rowIndex) {
//                    case 14:
//                        xuhao += 20;
//                        break;
//                    case 23:
//                        xuhao += 60;
//                        break;
//                    default:
//                        xuhao += 10;
//                        break;
//
//                }

            }
        }
        System.out.println("=====>" + new Gson().toJson(list));
        Iterator<Map.Entry<Integer, List<FaProductLingo>>> entries = list.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, List<FaProductLingo>> entry = entries.next();
            Integer key = entry.getKey();
            List<FaProductLingo> value = entry.getValue();
            System.out.println(key + ":" + value);
            for (int i = 0; i < value.size(); i++) {
                faProductLingoMapper.insert(value.get(i));
            }

        }

        is.close();
        return null;
    }

    private static String getCellValueStr(Cell load) {
        CellType cellType = load.getCellTypeEnum();
        String cellValue = "";
        if (load.toString().trim().equals("")) {
            return null;
        }

        cellValue = load.getStringCellValue();
        return cellValue;
    }

    /**
     * 获取单元格的数据,暂时不支持公式
     */
    public static String getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        String cellValue = "";
        if (cell == null || cell.toString().trim().equals("")) {
            return null;
        }

        cellValue = String.valueOf(cell.getNumericCellValue());
        return cellValue;

    }

    /**
     * 判断excel的版本，并根据文件流数据获取workbook
     *
     * @throws Exception
     */
    public static Workbook getWorkBook(InputStream is, File file) throws Exception {

        Workbook workbook = null;
        if (file.getName().endsWith(EXCEL_XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (file.getName().endsWith(EXCEL_XLSX)) {
            workbook = new XSSFWorkbook(is);
        }

        return workbook;
    }

    /**
     * 校验文件是否为excel
     *
     * @throws Exception
     */
    public static void checkExcelVaild(File file) throws Exception {
        String message = "该文件是EXCEL文件！";
        if (!file.exists()) {
            message = "文件不存在！";
            throw new Exception(message);
        }
        if (!file.isFile() || ((!file.getName().endsWith(EXCEL_XLS) && !file.getName().endsWith(EXCEL_XLSX)))) {
            System.out.println(file.isFile() + "===" + file.getName().endsWith(EXCEL_XLS) + "===" + file.getName().endsWith(EXCEL_XLSX));
            System.out.println(file.getName());
            message = "文件不是Excel";
            throw new Exception(message);
        }
    }

    @Test
    public void testCalc() {

        List<FaProductLingoCalc> list = faProductLingoCalcMapper.selectList(null);

        for (int i = 0; i < list.size(); i++) {
            int calcId = list.get(i).getId();
            int peoc = Integer.parseInt(list.get(i).getTotalpeo());
            System.out.println(calcId + "," + peoc);
            List<Integer> cid = faProductLingoMapper.getFaProId(calcId, peoc);

            System.out.println(cid);
            for (int j = 0; j < cid.size(); j++) {
                faProductLingoMapper.updateCalc(cid.get(j), calcId);
            }

        }

    }


}
