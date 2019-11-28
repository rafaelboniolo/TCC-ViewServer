package com.tcc.frontend.controller;

import javax.servlet.http.HttpSession;

import com.tcc.frontend.model.ResultModel;
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

    @PostMapping("savefile")  
    public ModelAndView upload(@RequestParam MultipartFile file,HttpSession session, Model model){  
        try{
            model.addAttribute("img", "");
            model.addAttribute("class", "");
            model.addAttribute("erro", "");

            if(file == null || file.isEmpty()){
                model.addAttribute("erro", "Selecione uma imagem antes de classificar");
                return new ModelAndView("classifier");  
            }
                
            
            ResultModel result = new ClassifierService().classify(file);
            model.addAttribute("img", result.getImage());
            
            if(result.getPredictedClass().equals("1"))
                model.addAttribute("class", "Normal");
            else
                model.addAttribute("class", "Anomalia");

        }catch(Throwable th){
            th.printStackTrace();
        }

        return new ModelAndView("classifier");  
    }  

}