package com.tcc.frontend.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import com.tcc.frontend.model.Image;
import com.tcc.frontend.services.ClassifierService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassifierController {
    @Value("${spring.application.name}")
    String appName;
 
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        return "classifier";
    }

    @PostMapping(value="/savefile")  
        public ModelAndView upload(@RequestParam MultipartFile file,HttpSession session){  
        
        try{  
            
            new ClassifierService().classify(file);
          
        }catch(Exception e){
            System.out.println(e);
        }

        return new ModelAndView("classifier");  
    }  

}