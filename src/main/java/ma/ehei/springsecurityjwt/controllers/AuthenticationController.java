package ma.ehei.springsecurityjwt.controllers;

import ma.ehei.springsecurityjwt.dto.LoginResponseDTO;
import ma.ehei.springsecurityjwt.dto.RegistrationDTO;
import ma.ehei.springsecurityjwt.model.ApplicationUser;
import ma.ehei.springsecurityjwt.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(),body.getPassword());
    }
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(),body.getPassword());
    }


}
