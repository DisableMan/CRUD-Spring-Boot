package com.mycompany.service;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();
    }
    public void saveUser(User user){
        repo.save(user);
    }

    public Optional<User> findById(Integer id){
        return repo.findById(id);
    }

    public void deleteUser(User user){
        repo.delete(user);
    }
}
