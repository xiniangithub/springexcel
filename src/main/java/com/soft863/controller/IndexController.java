package com.soft863.controller;

import com.soft863.entity.Result;
import com.soft863.service.IndexService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * 控制器
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IndexService indexService;

    @RequestMapping("/toIndex")
    public ModelAndView toIndex() {
        ModelAndView mav = new ModelAndView("index");

        // 获取上传路径
        String uploadsPath = request.getSession().getServletContext().getRealPath("/") + "uploads";
        // 创建文件对象
        File file = new File(uploadsPath);
        // 获取上传路径下的所有文件
        File[] files = file.listFiles();

        List<String> filenameList = new ArrayList<String>();
        if(files != null) {
            // 遍历获取所有文件名
            for (File f : files) {
                if (f.isFile()) {
                    filenameList.add(f.getName());
                }
            }
        }

        mav.addObject("filenameList", filenameList);
        return mav;
    }

    @RequestMapping("/query")
    public @ResponseBody List<Map<String, Object>> query() {
        return indexService.query();
    }

    /**
     * 导入Excel
     * @param uploadFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/uploadExcel")
    public @ResponseBody Result uploadExcel(MultipartFile uploadFile) throws IOException {
        // 判断上传路径是否存在，不存在则创建上传文件夹
        String uploadPath = request.getSession().getServletContext().getRealPath("/") + "uploads";

        File filePath = new File(uploadPath);
        if(!filePath.exists()) {
            filePath.mkdir();
        }

        File file = new File(uploadPath, uploadFile.getOriginalFilename());
        uploadFile.transferTo(file);

        Result result = new Result();
        result.setMessage("上传成功！");
        return result;
    }

    /**
     * 文件下载
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(String filename)throws Exception {
        // 获取下载文件路径
        String path = request.getServletContext().getRealPath("/uploads/");
        // 获取下载文件
        File file = new File(path + File.separator + filename);

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

    @RequestMapping("/exportExcel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        List<Map<String, Object>> list = indexService.query();

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

        String filename = "用户信息.xls";
        // 获取下载文件
        File file = new File(filename);
        // 将excel文件内容写到excel文件中
        workbook.write(file);

        // 解决文件下载时中文文件名乱码
        filename = new String(filename.getBytes("UTF-8"),"iso-8859-1");

        // 设置头信息
        HttpHeaders headers = new HttpHeaders();
        // 设置浏览器以attachment（下载方式）
        headers.setContentDispositionFormData("attachment", filename);
        // application/octet-stream ： 二进制流数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
