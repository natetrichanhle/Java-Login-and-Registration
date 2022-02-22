package com.nate.loginandregistration.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.nate.loginandregistration.models.Book;
import com.nate.loginandregistration.models.User;
import com.nate.loginandregistration.services.BookService;
import com.nate.loginandregistration.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BookController {
    
    @Autowired
    BookService bookService;
    @Autowired
    private UserService userService;

    @GetMapping("/books/new")
    public String newBook(Model model, @ModelAttribute("book") Book book, HttpSession session){
        // Route Guard
        Long userId = (Long) session.getAttribute("user_id");
        // check if userId is null
        if(userId == null){
            return "redirect:/";
        } else {
            User thisLoggedInUser = userService.findOne(userId);
            model.addAttribute("thisLoggedInUser",thisLoggedInUser);
            List<Book> books = bookService.allBooks();
            model.addAttribute("books", books);
            return "newBook.jsp";
        }
    }

    @GetMapping("/books/{bookId}")
    public String showOne(Model model, @PathVariable("bookId") Long bookId, HttpSession session){
        Long userId = (Long) session.getAttribute("user_id");
        // check if userId is null
        if(userId == null){
            return "redirect:/";
        } else {
            User thisLoggedInUser = userService.findOne(userId);
            model.addAttribute("thisLoggedInUser",thisLoggedInUser);
            Book book = bookService.findBook(bookId);
            model.addAttribute("book", book);
            return "showOne.jsp";
        }
    }
    
    @PostMapping("/books/submit")
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            // grab the user again
            Long userId = (Long) session.getAttribute("user_id");
            User thisLoggedInUser = userService.findOne(userId);
            model.addAttribute("thisLoggedInUser",thisLoggedInUser);
            // All the Books
            List<Book> books = bookService.allBooks();
            model.addAttribute("books", books);
            return "newBook.jsp";
        } else {
            bookService.createBook(book);
            return "redirect:/books";
        }
    }

    @GetMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){
        Book book = bookService.findBook(id);
        model.addAttribute("book", book);
        return "editBook.jsp";
    }

    @PutMapping("/books/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result){
        if(result.hasErrors()){
            return "editBook.jsp";
        } else {
            bookService.updateBook(book);
            return "redirect:/books";
        }
    }
}
