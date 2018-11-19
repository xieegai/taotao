package com.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用页面跳转逻辑
 * @author zjj
 * @date 18-10-28 1:21
 */
@RequestMapping("page")
@Controller
public class PageController {

    /**
     * @param pageName 视图名
     * @return 视图
     */
    @RequestMapping("{pageName}")
    public String toPage(@PathVariable("pageName") String pageName){
        return pageName;
    }
}
