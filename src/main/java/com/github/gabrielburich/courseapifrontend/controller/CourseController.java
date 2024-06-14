package com.github.gabrielburich.courseapifrontend.controller;

import com.github.gabrielburich.courseapifrontend.dto.CourseDTO;
import com.github.gabrielburich.courseapifrontend.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public String listPage(Model model) {
        var result = service.list();
        model.addAttribute("courses", result);
        return "list";
    }

    @GetMapping("/add")
    public String addPage() {
        return "add";
    }

    @PostMapping("/add-course")
    public String addCourse(CourseDTO courseDTO, RedirectAttributes redirectAttributes) {
        String errorMessage = courseDTO.isIncomplete()
                ? "Preencha todos os campos"
                : service.add(courseDTO);

        if (errorMessage != null) {
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/add";
        }
        return "redirect:/";
    }

    @GetMapping("/course/{id}")
    public String coursePage(@PathVariable String id, Model redirectAttributes) {
        var course = service.get(id);
        redirectAttributes.addAttribute("course", course);
        return "course";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id, Model model) {
        var course = service.get(id);
        model.addAttribute("course", course);
        return "edit";
    }

    @PostMapping("/edit-course")
    public String editCourse(CourseDTO courseDTO, RedirectAttributes redirectAttributes) {
        String errorMessage = courseDTO.isIncomplete()
                ? "Preencha todos os campos"
                : service.edit(courseDTO);

        if (errorMessage != null) {
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/add";
        }

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/active/{id}")
    public String toggleActive(@PathVariable String id) {
        service.toggleActive(id);
        return "redirect:/";
    }

}
