package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showAddForm(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("pageTitle","Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.saveUser(user);
        ra.addFlashAttribute("message","Save successfully!!");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model){
        User user = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id: "+ id));
        model.addAttribute("user", user);
        model.addAttribute("pageTitle","Edit User "+ user.getFirstName());
        return "user_form";

    }


    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        User user = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        service.deleteUser(user);
        ra.addFlashAttribute("message","Delete successfully!");
        return"redirect:/users";
    }

}
