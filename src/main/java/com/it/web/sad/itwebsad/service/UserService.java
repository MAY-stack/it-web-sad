package com.it.web.sad.itwebsad.service;

import com.it.web.sad.itwebsad.entity.User;

import java.util.List;

public interface UserService {

    public List<User> getUser();

    public User addUser(User user);

    public User deleteUser(String uuid);

    public User updateUser(String uuid, User user);

}
