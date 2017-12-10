package com.example.a2096129.alertfarm.api.services;

import com.example.a2096129.alertfarm.api.alertFarmException.ServicesException;
import com.example.a2096129.alertfarm.entities.User;

import java.util.List;

/**
 * @author Santiago Carrillo
 * 8/21/17.
 */
public interface UserService
{
    List<User> getUsers() throws ServicesException;

    User getUser(Long id);

    User createUser(User user);

    User findUserByEmail(String email);

    User findUserByEmailAndPassword(String email, String password);

    User registerUser(User user);
}