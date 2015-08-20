package com.dzhao.core.dao.service;

import com.dzhao.core.dao.repository.UserRepository;
import com.dzhao.core.dao.service.api.UserService;
import com.dzhao.core.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dzhao on 19/08/2015.
 */
@Service
public class UserServiceImpl implements UserService{

    //@Resource
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(User user) {
        User newUser = user;
        return userRepository.save(newUser);
    }
    @Transactional(rollbackFor=RuntimeException.class)
    public User delete(String id) {
        User deletedUser = userRepository.findOne(id);

        if (deletedUser == null)
            throw new RuntimeException("");

        userRepository.delete(deletedUser);
        return deletedUser;
    }
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Transactional(rollbackFor=RuntimeException.class)
    public User update(User user) {
        User updatedUser = userRepository.findOne(user.getId());

        if (updatedUser == null)
            throw new RuntimeException("");

        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setEmail(user.getEmail());
        return updatedUser;
    }
    @Transactional
    public User findById(String id) {
        return userRepository.findOne(id);
    }

    @Transactional
    public List<User> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
