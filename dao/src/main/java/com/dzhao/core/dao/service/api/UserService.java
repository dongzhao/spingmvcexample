package com.dzhao.core.dao.service.api;

import com.dzhao.core.model.User;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dzhao on 19/08/2015.
 */
@NoRepositoryBean
public interface UserService {
    public User create(User user);
    public User delete(String id);
    public List<User> findAll();
    public User update(User user);
    public User findById(String id);
    List<User> findByUserName(String username);
}
