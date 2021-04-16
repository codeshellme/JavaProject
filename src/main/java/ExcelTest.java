import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest {

    private static String cellToString(HSSFWorkbook wb, Cell cell) {
        // 用于数据类型转换
        DataFormatter objDefaultFormat = new DataFormatter();
        FormulaEvaluator objFormulaEvaluator = new HSSFFormulaEvaluator(wb);

        objFormulaEvaluator.evaluate(cell);
        return objDefaultFormat.formatCellValue(cell, objFormulaEvaluator);
    }

    public static String getCellValue(Cell cell) {
        String value = "";

        if( cell.getCellTypeEnum() == CellType.STRING) {
            // 字符串类型
            value = cell.getStringCellValue();

        } else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                // 日期类型
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                value = dateFormat.format(cell.getDateCellValue());
            } else {
                // 数字类型
                // value = cell.getNumericCellValue();
                value = String.valueOf(Double.valueOf(cell.getNumericCellValue()).intValue());
            }
        } else if (cell.getCellTypeEnum() == CellType.BLANK){
            value = "";
        } else {
            value = "";
        }

        return value;
    }

    public static List<List<String>> readExcel(String filePath, int sheetIndex, int rowIndexs) throws IOException {
        File file = new File(filePath);
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        POIFSFileSystem fs = new POIFSFileSystem(in);
        HSSFWorkbook wb = new HSSFWorkbook(fs);

        // 遍历每个 Sheet
        // for (int index = 0; index < wb.getNumberOfSheets(); index++) {
            // HSSFSheet st = wb.getSheetAt(index);
        // }

        // 读取第几个 Sheet，从 0 开始
        HSSFSheet st = wb.getSheetAt(sheetIndex);
        List<List<String>> list = new ArrayList<List<String>>();

        // 读取每一行
        // 注意这里必须是小于等于 <=
        for (int rowIndex = rowIndexs; rowIndex <= st.getLastRowNum(); rowIndex++) {
            HSSFRow row = st.getRow(rowIndex);

            List<String> l = new ArrayList<String>();
            for (Cell cell : row) {
                String value = getCellValue(cell);
//                String value = cellToString(wb, cell);
                l.add(value);
            }

            list.add(l);
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\工作\\test文件.xls";

        List<List<String>> list = readExcel(path, 0, 1);
        for (List<String> l : list) {
            System.out.println("=========" + l.toString() + l.size());
//            for (String i : l) {
//                System.out.println(i);
//            }
        }
    }
}