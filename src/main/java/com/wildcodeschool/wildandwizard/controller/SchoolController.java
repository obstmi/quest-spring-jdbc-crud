package com.wildcodeschool.wildandwizard.controller;

import com.wildcodeschool.wildandwizard.entity.School;
import com.wildcodeschool.wildandwizard.repository.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SchoolController {

    private SchoolRepository repository = new SchoolRepository();

    @GetMapping("/schools")
    public String getAll(Model model) {
    	
    	System.out.println("Bin im SchoolController, Methode getAll()");

        model.addAttribute("schools", repository.findAll());

        return "schools";
    }

    @GetMapping("/school")
    public String getSchool(Model model,
                            @RequestParam(required = false) Long id) {
    	
    	System.out.println("Bin im SchoolController, Methode getSchool()");


        //School school = new School();
    	//..kleine Variation:
    	School school;
        if (id != null) {
            school = repository.findById(id);
        } else {
        	school = new School();
        }
        model.addAttribute("school", school);

        return "school";
    }

    @PostMapping("/school")
    public String postSchool(@ModelAttribute School school) {
    	
    	System.out.println("Bin im SchoolController, Methode postSchool()");


        if (school.getId() != null) {
            repository.update(school);
        } else {
            repository.save(school);
        }
        return "redirect:/schools";
    }

    @GetMapping("/school/delete")
    public String deleteSchool(@RequestParam Long id) {
    	
    	System.out.println("Bin im SchoolController, Methode deleteSchool()");


        repository.deleteById(id);

        return "redirect:/schools";
    }
}
