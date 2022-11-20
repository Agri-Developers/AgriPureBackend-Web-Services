package com.agripure.agripurebackend.repository;

import com.agripure.agripurebackend.entities.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPlantRepository extends JpaRepository<Plant, Long> {

    Plant findByName(String name);
    @Query("select p from Plant p, UsersPlants up where p.id = up.plant.id and up.user.id = :userId")
    List<Plant> ListByUserId(@Param("userId") Long userId);
}
