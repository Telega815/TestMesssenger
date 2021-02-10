package ru.telega.messsenger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.telega.messsenger.model.User;
import ru.telega.messsenger.service.RestServise;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MainController {

    private final RestServise restServise;

    public MainController(RestServise restServise) {
        this.restServise = restServise;
    }


    @GetMapping(value = "/ass")
    public ResponseEntity<String> registerUser(){
        return ResponseEntity.ok("success!");
    }

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> registerUser(@RequestBody User user,
                                               HttpServletResponse response){

        restServise.registerUserRequest(user, response);

        return ResponseEntity.ok("success!");
    }
}
