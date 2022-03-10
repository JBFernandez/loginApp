package com.example.loginAPI.controller;
import com.example.loginAPI.UsersService;
import com.example.loginAPI.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //used to create RESTful web services using Spring MVC. Spring RestController takes care of mapping request data to the defined request handler method.
@RequestMapping("/api/login/users") //enpoint que específicamos en javascript const endpoint = "http://127.0.0.1:8080/api/login/clientes"
                                                                                                        //:8081
@CrossOrigin("*") //enables cross-origin resource sharing only for this specific method. it allows all origins, all headers, and the HTTP methods specified in the @RequestMapping annotation.
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }//constructor

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    } //getAllUsers

    @GetMapping(path = "{id}") // /api/login/1
    public Users getUser(@PathVariable("id") Long id) { //@Pathvariable indicates that a method parameter should be bound to a URI template variable.
        return usersService.getUser(id);
    } //getUser

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUser(id);
    }//deleteUser

    @PostMapping
    public void addUser(@RequestBody Users user) {
        usersService.addUsers(user);
    }//addUser

    @PutMapping
    public void updateUser(@PathVariable("id") Long id,
                           @RequestParam(required = false) String originalPassword,
                           @RequestParam(required = false) String newPassword,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email) {
        usersService.updateUser(id, originalPassword, newPassword, name, email);
    }//update usuario

}// Class UsersController
