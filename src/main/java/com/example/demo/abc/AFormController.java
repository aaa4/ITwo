package com.example.demo.abc;


import com.example.demo.validation.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/a")
public class AFormController {


    @GetMapping("/getForm")
    public String getFormA(Model model){
        model.addAttribute("personForm", new PersonForm());
        return "aFrom";
    }

    @PostMapping("/getForm")
    public String postFormA(@ModelAttribute PersonForm personForm){
        System.out.println(personForm);
       // return "redirect:/getForm";
        return "r2";
    }
}
