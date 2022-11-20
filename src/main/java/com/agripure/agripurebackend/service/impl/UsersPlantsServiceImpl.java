package com.agripure.agripurebackend.service.impl;

import com.agripure.agripurebackend.entities.UsersPlants;
import com.agripure.agripurebackend.repository.IUsersPlantsRepository;
import com.agripure.agripurebackend.service.IUsersPlantsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersPlantsServiceImpl implements IUsersPlantsService {

    private final IUsersPlantsRepository usersPlantsRepository;

    public UsersPlantsServiceImpl(IUsersPlantsRepository usersPlantsRepository) {
        this.usersPlantsRepository = usersPlantsRepository;
    }

    @Override
    @Transactional
    public UsersPlants save(UsersPlants usersPlants) throws Exception {
        return usersPlantsRepository.save(usersPlants);
    }

    @Override
    public void delete(Long id) throws Exception {
        usersPlantsRepository.deleteById(id);
    }

    @Override
    public List<UsersPlants> getAll() throws Exception {
        return usersPlantsRepository.findAll();
    }

    @Override
    public Optional<UsersPlants> getById(Long id) throws Exception {
        return usersPlantsRepository.findById(id);
    }

    /*@Override
    public void deleteByIds(Long userId, Long plantId) throws Exception {
        usersPlantsRepository.deleteByIds(userId, plantId);
    }*/
}
