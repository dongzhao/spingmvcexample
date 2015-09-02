package com.dzhao.springmvc.services;

import com.dzhao.springmvc.model.User;
import com.dzhao.springmvc.model.UserProfile;
import com.dzhao.springmvc.repositories.UserProfileRepository;
import com.dzhao.springmvc.repositories.UserRepository;
import com.dzhao.springmvc.services.api.UserProfileService;
import com.dzhao.springmvc.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dzhao on 19/08/2015.
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository){
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional
    public UserProfile create(UserProfile userProfile) {
        UserProfile newUserProfile = userProfileRepository.save(userProfile);
        UserProfile findUserProfile = userProfileRepository.findOne(newUserProfile.getId());
        System.out.println("userprofile id: " + findUserProfile.getId());
        return findUserProfile;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    public UserProfile delete(Integer id) {
        UserProfile deletedUserProfile= userProfileRepository.findOne(id);

        if (deletedUserProfile == null)
            throw new RuntimeException("");

        userProfileRepository.delete(deletedUserProfile);
        return deletedUserProfile;
    }

    @Transactional
    public List<UserProfile> findAll() {
        List<UserProfile> userProfiles = userProfileRepository.findAll();
        System.out.println("Number of userprofiles: " + userProfiles.size());
        return userProfiles;
    }

    @Transactional(rollbackFor=RuntimeException.class)
    public UserProfile update(UserProfile userProfile) {
        UserProfile updatedUserProfile = userProfileRepository.findOne(userProfile.getId());

        if (updatedUserProfile == null)
            throw new RuntimeException("");

        updatedUserProfile.setAddress(userProfile.getAddress());
        updatedUserProfile.setDateOfBirth(userProfile.getDateOfBirth());
        return updatedUserProfile;
    }

    @Transactional
    public UserProfile findById(Integer id) {
        UserProfile findUserProfile = userProfileRepository.findOne(id);
        System.out.println("find userprofile: "+findUserProfile.getUser().getUserName());
        return findUserProfile;
    }
}