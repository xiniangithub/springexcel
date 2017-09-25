package com.soft863.dao;

import java.util.List;
import java.util.Map;

/**
 * 持久层接口
 */
public interface IndexDao {

    public List<Map<String, Object>> query();

}
