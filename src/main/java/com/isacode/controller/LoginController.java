package com.isacode.controller;

import com.isacode.entity.User;
import com.isacode.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView authView  = new ModelAndView("login");
        authView.addObject("user",new User());
        return authView;
    }git i

    @PostMapping("/login")
    public String processLogin(@ModelAttribute(name = "user") User user, BindingResult result, RedirectAttributes attribute) {
        if (!userService.validateLogin(user.getEmail(), user.getPassword())) {
            attribute.addFlashAttribute("warning", "Incorrect user data.");
            return "redirect:/login";
        }
        return "redirect:/repositions";

    }

}
