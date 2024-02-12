package com.task.task.repository;


import com.task.task.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    @Query(value = "SELECT * FROM role r where r.title = :title",nativeQuery = true)
    Optional<RoleEntity> findRoleEntityByTitle(String title);}