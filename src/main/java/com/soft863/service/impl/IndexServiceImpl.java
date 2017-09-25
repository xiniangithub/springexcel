package com.soft863.service.impl;

import com.soft863.dao.IndexDao;
import com.soft863.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

}
