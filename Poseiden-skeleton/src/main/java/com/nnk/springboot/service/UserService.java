package com.nnk.springboot.service;


import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service

public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {

        return userRepository.findAll();
    }
    public User save(User user) {

        return userRepository.save(user);
    }
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

}

