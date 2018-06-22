package com.mc.challenge.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String showHomepage(){
        return "intro";
    }

    @RequestMapping("/addjob")
    public String addJobdetails(Model model){
        model.addAttribute("jobentity", new JobEntity());
        return "jobDetails";
    }

    @PostMapping("/process")
    public String processJob(@ModelAttribute JobEntity jobentity){
        jobRepository.save(jobentity);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String showList(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "listJobs";
    }

    @GetMapping("/detailList")
    public String showDescription(Model model){
        model.addAttribute("jobs", jobRepository.findAll());
        return "description";
    }
}
