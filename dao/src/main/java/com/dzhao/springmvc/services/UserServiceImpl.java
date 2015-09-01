package com.dzhao.springmvc.services;

import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.repositories.UserRepository;
import com.dzhao.springmvc.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dzhao on 19/08/2015.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        User newUser = userRepository.save(user);
        User findUser = userRepository.findOne(newUser.getId());
        System.out.println("user id: " + findUser.getId());
        return findUser;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    public User delete(Integer id) {
        User deletedUser = userRepository.findOne(id);

        if (deletedUser == null)
            throw new RuntimeException("");

        userRepository.delete(deletedUser);
        return deletedUser;
    }

    @Transactional
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        System.out.println("Number of users: " + users.size());
        return users;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    public User update(User user) {
        User updatedUser = userRepository.findOne(user.getId());

        if (updatedUser == null)
            throw new RuntimeException("");

        updatedUser.setUserName(user.getUserName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmail(user.getEmail());
        return updatedUser;
    }

    @Transactional
    public User findById(Integer id) {
        User findUser = userRepository.findOne(id);
        System.out.println("find user: "+findUser.getUserName());
        return findUser;
    }

    @Transactional
    public List<User> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}