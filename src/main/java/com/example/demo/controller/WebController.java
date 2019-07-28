package com.example.demo.controller;

import com.example.demo.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class WebController {

    private TemplateService templateService;
    @Autowired
    public WebController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping("/template")
    public void template(){

        templateService.doTemplate();

    }
}
