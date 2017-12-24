package com.example.demo.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class ValController extends WebMvcConfigurerAdapter{

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("personForm", new PersonForm());
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@ModelAttribute @Validated PersonForm personForm, BindingResult bindingResult) {

        System.out.println(bindingResult);
        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/rst";
    }

    @GetMapping("/rst")
    public String resultMapping(){
        return "result";
    }
}
