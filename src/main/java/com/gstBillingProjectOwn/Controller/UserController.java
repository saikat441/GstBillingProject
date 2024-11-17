package com.gstBillingProjectOwn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gstBillingProjectOwn.Entity.User;
import com.gstBillingProjectOwn.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	//Request come from userSignUp.html
	@PostMapping("/do-signup")
	public String processSignup(@ModelAttribute User user, Model model) {
	    
	    if (userRepository.existsByEmail(user.getEmail())) {
	        // Add error message to the model
	        model.addAttribute("error", "Email already exists. Please use a different email.");
	        return "userSignUp";  // Return to the signup page with error
	    }

	    userRepository.save(user);
	    model.addAttribute("success", "User added successfully.");
	    // Redirect to login page after successful sign-up
	    return "redirect:/userLogin";
	}
	
	//Request come from userLogin.html
	@PostMapping("/do-login")
    public String processLogin(@RequestParam String email,@RequestParam String password,Model model,HttpSession session) {
        
        User user = userRepository.findByEmail(email);
        
        if (user != null && user.getPassword().equals(password)) {
           
        	session.setAttribute("user", user);
            model.addAttribute("user", user);  // You can use session management here
            return "userHome";
        } 
        else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "userLogin";
        }
    }


}
