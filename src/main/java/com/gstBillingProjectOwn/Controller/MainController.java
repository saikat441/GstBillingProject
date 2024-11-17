package com.gstBillingProjectOwn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gstBillingProjectOwn.Entity.User;

@Controller
public class MainController {
	
	private static final String ADMIN_EMAIL = "admin@gmail.com";
    private static final String ADMIN_PASSWORD = "123";
	
    //when application will be start
	@GetMapping("/")
    public String showHomePage() {
        return "welcome";  // Thymeleaf will look for "welcome.html" in the templates directory
    }
	
	//Request come from welcome.html
	@GetMapping("/adminLogin")
    public String showAdminLoginPage() {
        return "adminLogin";
    }
	
	//Request come from welcome.html
	@GetMapping("/userLogin")
    public String showUserLoginPage() {
        return "userLogin";
    }

	//Request come from welcome.html
    @GetMapping("/userSignUp")
    public String showSignupPage(Model model) {
    	model.addAttribute("user", new User());
        return "userSignUp";
    }
    
    //Request come from adminLogin.html
    @PostMapping("/adminLogin")
    public String processAdminLogin(@RequestParam String email,@RequestParam String password,Model model) {
 
        if (ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password)) {
            // Successful login logic (e.g., set admin in session)
            return "adminHome"; 
        } else {
            
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "adminLogin";
        }
        
    }
    
    @GetMapping("/adminHome")
    public String adminHome() {
        return "adminHome";  // This will look for adminHome.html in templates
    }
	
//	@PostMapping("/signup")
//    public String signup(@RequestParam String role) {
//		
//        switch (role) {
//            case "Admin":
//                return "adminSignup";  // Redirects to "adminSignup.html"
//            case "User":
//                return "userSignup";  // Redirects to "userSignup.html"
//            default:
//                return "FirstIndexPage";  // Redirects back to the form if no valid role is selected
//        }
//    }
    
}

