package com.soft863.controller;

import com.soft863.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/toIndex")
    public ModelAndView toIndex() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("/query")
    public @ResponseBody List<Map<String, Object>> query() {
        return indexService.query();
    }
}
