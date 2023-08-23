package com.it.web.sad.itwebsad.service.Impl;

import com.it.web.sad.itwebsad.entity.User;
import com.it.web.sad.itwebsad.repository.UserRepository;
import com.it.web.sad.itwebsad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(String uuid) {
        User targetUser = userRepository.findById(uuid).get();
        userRepository.delete(targetUser);
        return targetUser;
    }

    @Override
    public User updateUser(String uuid, User user) {
        User targetUser = userRepository.findById(uuid).get();
        targetUser.setIp(user.getIp());
        targetUser.setName(user.getName());
        targetUser.setAgent(user.getAgent());
        return userRepository.save(targetUser);
    }
}
