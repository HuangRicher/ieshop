package com.seamwhole.serviceuaa.service.security;

import com.seamwhole.serviceuaa.domain.UserDetail;
import com.seamwhole.serviceuaa.mapper.ext.UserExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetail user=userExtMapper.getByUserSerial(Integer.valueOf(username));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
