package com.nate.loginandregistration.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.nate.loginandregistration.models.Book;
import com.nate.loginandregistration.models.LoginUser;
import com.nate.loginandregistration.models.User;
import com.nate.loginandregistration.services.BookService;
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

    @Autowired
    private BookService bookService;

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
            return "redirect:/books";
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
            return "redirect:/books";
        }
    }

    // --- we are logged in at this point ---

    // home route
    @GetMapping("/books")
    public String books(HttpSession session, Model model){
        Long userId = (Long) session.getAttribute("user_id");
        if(userId == null){
            return "redirect:/";
        } else {
            User thisLoggedInUser = userService.findOne(userId);
            model.addAttribute("thisLoggedInUser",thisLoggedInUser);
            List<Book> books = bookService.allBooks();
            model.addAttribute("booksList", books);
            return "books.jsp";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
