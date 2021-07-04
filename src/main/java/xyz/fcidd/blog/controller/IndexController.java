package xyz.fcidd.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    //主页
    @GetMapping("/")
    public String index(){
        return "index";
    }
    //开发工具页面
    @GetMapping("/devTools")
    public String devTools(){
        return "devTools";
    }

}
