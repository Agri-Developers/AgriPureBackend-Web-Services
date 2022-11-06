package com.agripure.agripurebackend.service;


import com.agripure.agripurebackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserService extends CrudService<User>{
    Optional<User> findByEmail(String email) throws Exception;
    List<User> findByPremium(Boolean premium) throws Exception;
}