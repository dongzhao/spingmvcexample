package com.dzhao.springmvc.services.api;

import com.dzhao.springmvc.model.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 *
 */
@NoRepositoryBean
public interface UserService {
    public User create(User user);
    public User delete(String id);
    public List<User> findAll();
    public User update(User user);
    public User findById(String id);
/*    List<User> findByUserName(String username);*/
}
