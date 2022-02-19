package com.nate.loginandregistration.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.nate.loginandregistration.models.LoginUser;
import com.nate.loginandregistration.models.User;
import com.nate.loginandregistration.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    @Autowired
    private UserService userService;

    // Login
    @GetMapping("/")
    public String loginReg(Model model){
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }

    // Register new user
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session){
        // executing service
        userService.register(newUser, result);
        // then check errors
        if(result.hasErrors()){
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        } else {
            session.setAttribute("user_id", newUser.getId());
            return "redirect:/home";
        }
    }

    // login
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session){
        User user = userService.login(newLogin, result);
        if(result.hasErrors()){
            model.addAttribute("newUser", new User());
            return "index.jsp";
        } else {
            session.setAttribute("user_id", user.getId());
            return "redirect:/home";
        }
    }

    // --- we are logged in at this point ---

    // home route
    @GetMapping("/home")
    public String home(HttpSession session, Model model){
        // retrieve from the DB using session id
        Long userId = (Long) session.getAttribute("user_id");
        // check if userId is null
        if(userId == null){
            return "redirect:/";
        } else {
            // go to the DB to retrieve the user using the id
            User thisUser = userService.findOne(userId);
            model.addAttribute("thisUser", thisUser);
            return "home.jsp";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
