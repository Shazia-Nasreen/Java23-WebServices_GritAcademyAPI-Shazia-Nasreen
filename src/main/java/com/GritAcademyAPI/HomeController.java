package com.GritAcademyAPI;

import com.GritAcademyAPI.students.StudentsDTO;
import com.GritAcademyAPI.students.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private StudentsService studentsService;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("message", "Hi");
        return "homePage";
    }



}
