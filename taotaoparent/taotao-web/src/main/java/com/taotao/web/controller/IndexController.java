package com.taotao.web.controller;

import com.taotao.web.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 处理首页请求
 *
 * @author zjj
 * @date 2018/11/22 23:23
 */
@Controller
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        // 首页大广告
        mv.addObject("bigAd", indexService.getBigAd());
        return mv;
    }

    /*@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }*/


}
