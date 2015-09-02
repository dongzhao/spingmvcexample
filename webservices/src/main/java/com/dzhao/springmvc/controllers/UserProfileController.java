package com.dzhao.springmvc.controllers;

import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.model.UserProfile;
import com.dzhao.springmvc.services.api.UserProfileService;
import com.dzhao.springmvc.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping(value = "/userProfile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName(){
        System.out.println("return service name...");
        return UserProfileController.class.getSimpleName();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserProfile create(@RequestBody @Valid final UserProfile userProfile){
        System.out.println("start to create a new userProfile...");
        return userProfileService.create(userProfile);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public UserProfile update(@RequestBody @Valid final UserProfile userProfile){
        return userProfileService.update(userProfile);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public UserProfile delete(@PathVariable("id") Integer id) {
        return userProfileService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public UserProfile findById(@PathVariable("id") Integer id) {
        System.out.println("find the userProfile by id ["+ id + "]");
        return userProfileService.findById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public List<UserProfile> findAll() {
        System.out.println("list all userProfiles");
        return userProfileService.findAll();
    }

}
