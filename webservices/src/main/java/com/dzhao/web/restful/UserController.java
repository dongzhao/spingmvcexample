package com.dzhao.web.restful;

import com.dzhao.core.dao.service.api.UserService;
import com.dzhao.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by dzhao on 20/08/2015.
 */
@RestController
public class UserController {

    //@Autowired(required = true)
    @Resource
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public User create(@RequestBody @Valid final User user){
        return userService.create(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public User update(@RequestBody @Valid final User user){
        return userService.update(user);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User delete(@PathVariable("id") String id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public User findById(@PathVariable("id") String id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<User> findAll() {
        return userService.findAll();
    }
}
