package com.icoder.controller;

import com.icoder.model.User;
import com.icoder.model.iCoder;
import com.icoder.service.iCoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Objects;

@Controller
public class iCoderController {

    @Autowired
    public iCoderService service;

    @Autowired
    BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/about")
    public String about() {

        return "about";
    }

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("icoder") iCoder icoder) {

        System.out.println(icoder.isMember());

        iCoder iCoder = null;

        try {
            boolean save = this.service.submit(icoder);

            if (save) {
                System.out.println("Data is successfully save");
            } else {
                System.out.println("Data is not successfully save");
            }

        } catch (Exception e) {
            System.out.println("Error--------------");
            return "redirect:/contact";
        }

        return "redirect:/contact";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signup") User user) {

        service.signUp(user);

        return "redirect:/";
    }

    @PostMapping("/login12")
    public String login(@ModelAttribute("user") User user) {

        User user1 = service.getUserByUsername(user.getEmail());

        if (user1 != null) {

            if (encoder.matches(user.getPassword(), user1.getPassword())) {

                return "redirect:/";
            }else {
                System.out.println("Not Login");

                return "redirect:/contact";
            }


        }

        return "redirect:/about";
    }
}
