package com.soft863.service.impl;

import com.soft863.dao.IndexDao;
import com.soft863.service.IndexService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 业务层接口实现类
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    public List<Map<String, Object>> query() {
        return indexDao.query();
    }

    public ResponseEntity<byte[]> exportExcel() throws Exception {
        String filename = "test.xls";
        // 获取下载文件
        File file = new File("test.xls");

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
                // row.createCell(i).setCellValue();
            }

        }

        // 解决文件下载时中文文件名乱码
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");

        // 设置头信息
        HttpHeaders headers = new HttpHeaders();
        // 设置浏览器以attachment（下载方式）
        headers.setContentDispositionFormData("attachment", downloadFielName);
        // application/octet-stream ： 二进制流数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
