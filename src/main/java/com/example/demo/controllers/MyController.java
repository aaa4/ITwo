package com.example.demo.controllers;

import com.example.demo.model.Instructor;
import com.example.demo.model.InstructorAndDetail;
import com.example.demo.model.InstructorDetail;
import com.example.demo.repository.DetailRepository;
import com.example.demo.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    DetailRepository detailRepository;

    @GetMapping("/index")
    public String mainController(){
        return "index";
    }

    @GetMapping("/add")
    public String addInstructorForm(Model model){
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("detail", new InstructorDetail());
        return "addInstructor";
    }

    @PostMapping("/add")
    public String submitForm(@ModelAttribute @Valid InstructorDetail instructorDetail, BindingResult bindingResult, @Valid @ModelAttribute Instructor instructor, BindingResult bindingResult1){
       /* if (bindingResult.hasErrors()) {
            return "instructorsList";
        }*/


        if ((bindingResult.hasErrors())||(bindingResult1.hasErrors())) {
            return "addInstructor";
        }
        instructor.setInstructorDetail(instructorDetail);
        instructorRepository.save(instructor);
        return "redirect:/instructorsList";
    }

    @GetMapping("/instructorsList")
    public String getList(Model model){
        Map map = new HashMap<>();
        List list = new ArrayList<Instructor>();
        list = instructorRepository.findAll();
        List resultList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Instructor instructor = (Instructor)list.get(i);
            InstructorDetail detail = detailRepository.getInstructorDetailById(instructor.getId());
            InstructorAndDetail iid = new InstructorAndDetail(instructor, detail);
            resultList.add(iid);
        }
        model.addAttribute("instructors", resultList);
        return "instructorsList";
    }

}
