package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.UsersPlants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsersPlantsRepository extends JpaRepository<UsersPlants, Long> {

    /*@Query("delete from UsersPlants up WHERE up.user.id = :userId and up.plant.id = :plantId")
    void deleteByIds(@Param("userId") Long userId, @Param("plantId") Long plantId);*/
}
