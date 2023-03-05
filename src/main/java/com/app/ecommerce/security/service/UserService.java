package com.app.ecommerce.security.service;

import com.app.ecommerce.security.entity.User;
import com.app.ecommerce.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> list(){
        return userRepository.findAll();
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void delete(int id){
        userRepository.deleteById(id);
    }

    public Optional<User> getOne(int id){
        return userRepository.findById(id);
    }
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    public boolean existsById(int id){
        return userRepository.existsById(id);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
