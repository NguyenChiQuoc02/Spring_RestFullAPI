package com.ncquoc.myweb.service;

import com.ncquoc.myweb.domain.User;
import com.ncquoc.myweb.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user){
         return this.userRepository.save(user);
    }

    public void handleDeleteUser(Long id){
        this.userRepository.deleteById(id);
    }
    public List<User> handleGetAllUser(){
        return this.userRepository.findAll();
    }

    public User fetchUserById(Long id){
        Optional<User> user = this.userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public User handleUpdateUser(User user){
        User currentUser = this.fetchUserById(user.getId());
        if(currentUser != null){
            currentUser.setName(user.getName());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            this.userRepository.save(currentUser);
        }
        return currentUser;
    }
}
