/*
package com.dzhao.app.web.controller;

import com.dzhao.core.dao.service.api.UserService;
import com.dzhao.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

*/
/**
 * Created by dzhao on 19/08/2015.
 *//*

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/create", method= RequestMethod.GET)
    public ModelAndView newShopPage() {
        ModelAndView mav = new ModelAndView("user-new", "user", new User());
        return mav;
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public ModelAndView createNewShop(@ModelAttribute User user,
                                      final RedirectAttributes redirectAttributes) {

        ModelAndView mav = new ModelAndView();
        String message = "New user "+user.getUsername()+" was successfully created.";

        userService.create(user);
        mav.setViewName("redirect:/index.html");

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/list", method=RequestMethod.GET)
    public ModelAndView userListPage() {
        ModelAndView mav = new ModelAndView("user-list");
        List<User> userList = userService.findAll();
        mav.addObject("userList", userList);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editShopPage(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("user-edit");
        User user = userService.findById(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editShop(@ModelAttribute User user,
                                 @PathVariable Integer id,
                                 final RedirectAttributes redirectAttributes) throws RuntimeException {

        ModelAndView mav = new ModelAndView("redirect:/index.html");
        String message = "User was successfully updated.";

        userService.update(user);

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable String id,
                                   final RedirectAttributes redirectAttributes) throws RuntimeException {

        ModelAndView mav = new ModelAndView("redirect:/index.html");

        User user = userService.delete(id);
        String message = "The user "+user.getUsername()+" was successfully deleted.";

        redirectAttributes.addFlashAttribute("message", message);
        return mav;
    }
}
*/
