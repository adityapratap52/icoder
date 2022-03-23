package com.icoder.service;

import com.icoder.dao.UserRepository;
import com.icoder.dao.iCoderRepository;
import com.icoder.model.User;
import com.icoder.model.iCoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class iCoderService {

    @Autowired
    public iCoderRepository iCoderRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder encoder;

    public boolean submit(iCoder icoder) {

        iCoderRepository.save(icoder);

        return true;

    }

    public boolean signUp(User user) {

        user.setRole("USER");
        user.setPassword(this.encoder.encode(user.getPassword()));

        userRepository.save(user);

        return true;
    }

    public User getUserByUsername(String username) {

        return userRepository.getUserByUserName(username);
    }
}
