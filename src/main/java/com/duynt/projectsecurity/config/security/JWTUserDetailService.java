package com.duynt.projectsecurity.config.security;

import com.duynt.projectsecurity.common.ResponseConstant;
import com.duynt.projectsecurity.entity.User;
import com.duynt.projectsecurity.repository.UserRepository;
import com.duynt.projectsecurity.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lay thong tin nguoi dung tu DB
 */
@Service
public class JWTUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(ResponseConstant.MSG_USER_NOTFOUND);
        }
        return new UserDetailsImpl(user);
    }
}
