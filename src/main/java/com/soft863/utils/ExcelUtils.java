package com.soft863.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;

/**
 * Excel工具类
 */
public class ExcelUtils {

    public static void buildExcelFile(List<Map<String, Object>> list) {
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
    }

}
