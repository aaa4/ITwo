package com.example.demo.controllers;

import com.example.demo.model.Instructor;
import com.example.demo.model.InstructorDetail;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IIController {

    Instructor instructor;
    InstructorDetail instructorDetail;

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("detail", new InstructorDetail());
        return "form1";
    }

    @PostMapping("/new")
    public String formPost(@ModelAttribute Instructor instructor,
                           @ModelAttribute InstructorDetail detail){
        System.out.println(instructor);
        System.out.println(detail);

        return "redirect:/newResult";
    }

    @GetMapping("/newResult")
    @ResponseBody
    public String newResult(){
        return " good "+instructor +"\n"+instructorDetail;
    }
}
