package com.duynt.projectsecurity.service;

import com.duynt.projectsecurity.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List fillAll(String username);

}
