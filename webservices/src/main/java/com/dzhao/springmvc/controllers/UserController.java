package com.dzhao.springmvc.controllers;

import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName(){
        System.out.println("return service name...");
        return UserController.class.getSimpleName();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User create(@RequestBody @Valid final User user){
        System.out.println("start to create a new user...");
        return userService.create(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(@RequestBody @Valid final User user){
        return userService.update(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findById(@PathVariable("id") Integer id) {
        System.out.println("find the user by id ["+ id + "]");
        return userService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAll() {
        System.out.println("list all users");
        return userService.findAll();
    }

}