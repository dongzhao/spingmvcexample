package com.dzhao.springmvc.services.api;

import com.dzhao.springmvc.model.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 *
 */
//@NoRepositoryBean
public interface UserService {
    public User create(User user);
    public User delete(Integer id);
    public List<User> findAll();
    public User update(User user);
    public User findById(Integer id);
/*    List<User> findByUserName(String username);*/
}
