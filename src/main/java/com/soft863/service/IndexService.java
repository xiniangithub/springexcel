package com.soft863.service;

import org.springframework.http.ResponseEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * 业务层接口
 */
public interface IndexService {

    public List<Map<String, Object>> query();

    /**
     * 导出Excel
     * @return
     */
    public ResponseEntity<byte[]> exportExcel() throws UnsupportedEncodingException, Exception;

}
