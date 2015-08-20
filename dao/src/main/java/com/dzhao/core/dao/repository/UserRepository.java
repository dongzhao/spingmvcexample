package com.dzhao.core.dao.repository;

import com.dzhao.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dzhao on 19/08/2015.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByUserName(String username);
}
