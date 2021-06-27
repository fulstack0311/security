package com.duynt.projectsecurity.service.serviceImpl;

import com.duynt.projectsecurity.entity.User;
import com.duynt.projectsecurity.repository.UserRepository;
import com.duynt.projectsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List fillAll(String username) {
        return null;
    }
}
