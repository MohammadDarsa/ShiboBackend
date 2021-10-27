package com.example.shibo.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080/api/v1/registration")
@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:8080/api/v1/registration")
    @PostMapping
    public String register(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    @CrossOrigin(origins = "http://localhost:8080/api/v1/registration")
    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
