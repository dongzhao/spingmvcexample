package com.dzhao.springmvc.repositories;

import com.dzhao.springmvc.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dzhao on 19/08/2015.
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

}
