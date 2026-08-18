package com.codewithmosh.store.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name","amos");
        return "index";
    }

//    @RequestMapping("/helo")
//    public String index2(){
//        return "index.html";
//    }


}
