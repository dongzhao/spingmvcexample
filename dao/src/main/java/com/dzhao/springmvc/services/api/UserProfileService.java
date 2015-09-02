package com.dzhao.springmvc.services.api;

import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.model.UserProfile;

import java.util.List;

/**
 *
 */

public interface UserProfileService {
    public UserProfile create(UserProfile userProfile);
    public UserProfile delete(Integer id);
    public List<UserProfile> findAll();
    public UserProfile update(UserProfile user);
    public UserProfile findById(Integer id);
}
