package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页请求
 *
 * @author zjj
 * @date 2018/11/22 22:29
 */
@Controller
public class IndexController {

    /*@RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return  mv;
    }*/

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        return  "index";
    }
}
