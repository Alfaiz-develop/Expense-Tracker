package in.smart.tracker.controller;


import in.smart.tracker.entity.User;
import in.smart.tracker.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.PushBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register.html")
    public String openRegPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String submitReg(@ModelAttribute("user") User user , Model model){

        boolean status =userService.regUser(user);
        if(status){
            model.addAttribute("successMsg", "User register successfully");
        }
        else {
            model.addAttribute("errorMsg","User not register due to some error");
        }
        return "register";
    }

    @GetMapping("/login.html")
    public String openLoginPage(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute("user") User user , Model model) {
        User validUser = userService.loginUser(user.getEmail(), user.getPassword());
        if (validUser != null) {
            model.addAttribute("modelname",validUser.getUsername());
            return "profile";
        } else {
            model.addAttribute("errorMsg" ,"Email Id and Password did not match");
            return "login";
        }
    }

    @GetMapping("/about.html")
    public String openAboutPage(){
        return "about";
    }

    @GetMapping("/contact.html")
    public String openContactPage(){
        return "contact";
    }

    @GetMapping("/dashboard.html")
    public String showDashboard(){
        return "dashboard";
    }

}
