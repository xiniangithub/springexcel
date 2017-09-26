import com.soft863.dao.IndexDao;
import org.apache.poi.hssf.usermodel.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-resource.xml")
public class Test {

    @Autowired
    private IndexDao indexDao;

    /**
     * 创建一个空的excel文件
     * @throws IOException
     */
    @org.junit.Test
    public void createEmptyExcelFile() throws IOException {
        // 创建一个Excel文件
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\123.xls");

        hssfWorkbook.write(fos);
        fos.close();
    }

    @org.junit.Test
    public void createExcelFile() throws IOException {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个Sheet对象
        HSSFSheet sheet = workbook.createSheet("sheet01");
        // 创建行
        HSSFRow row = sheet.createRow(0); // 参数为行数，从0开始

        // 创建一个单元格
        HSSFCell cell = row.createCell(0); // 参数为列数，从0开始
        // 设置单元格内容
        cell.setCellValue(0);

        // 创建一个单元格，并设置单元格内容
        row.createCell(1).setCellValue(1);
        row.createCell(2).setCellValue(2.2);
        row.createCell(3).setCellValue("test");
        row.createCell(4).setCellValue(true);
        row.createCell(5).setCellValue(5);

        // 创建新的单元格样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        // 设置单元格样式为定制的日期格式
        HSSFCell dateCell = row.createCell(6);
        dateCell.setCellValue(new Date()); // 设置单元格为日期类型的值
        dateCell.setCellStyle(cellStyle);

        // 写入Excel文件
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\123.xls");
        workbook.write(fos);
        fos.close();
    }

    @org.junit.Test
    public void exportExcel() throws IOException {
        List<Map<String, Object>> list = indexDao.query();

        // 创建
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("1");
        for (int i=0; i<list.size(); i++) {
            HSSFRow row = sheet.createRow(i);

            Map<String, Object> map = list.get(i);
            Set<String> keys = map.keySet();
            Iterator<String> iterator = keys.iterator();
            for(int j=0; iterator.hasNext(); j++) {
                String key = iterator.next();
                Object obj = map.get(key);

                if(obj instanceof String) {
                    String objStr = (String) obj;
                    row.createCell(j).setCellValue(objStr);
                } else if(obj instanceof Integer) {
                    int objInt = (Integer) obj;
                    row.createCell(j).setCellValue(objInt);
                } else if(obj instanceof Long) {
                    long objLong = (Long) obj;
                    row.createCell(j).setCellValue(objLong);
                } else if(obj instanceof Float) {
                    float objFloat = (Float) obj;
                    row.createCell(j).setCellValue(objFloat);
                } else if(obj instanceof Double) {
                    double objDouble = (Double) obj;
                    row.createCell(j).setCellValue(objDouble);
                } else if(obj instanceof Boolean) {
                    Boolean objBoolean = (Boolean) obj;
                    row.createCell(j).setCellValue(objBoolean);
                } else if(obj instanceof Date) {
                    Date objDate = (Date) obj;
                    row.createCell(j).setCellValue(objDate);
                }
            }
        }

        // 写入Excel文件
        FileOutputStream fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\123.xls");
        workbook.write(fos);
        fos.close();
    }

}
