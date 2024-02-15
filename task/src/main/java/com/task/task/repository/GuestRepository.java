package com.task.task.repository;


import com.task.task.entity.GuestSuggestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GuestRepository extends JpaRepository<GuestSuggestionEntity, Long> {
    @Query(value = "SELECT * FROM guest_entity r where r.status = 'CREATE'",nativeQuery = true)
    Optional<List<GuestSuggestionEntity>> findAllCreatedSuggestions();

    @Query(value = "SELECT * FROM guest_entity r where r.status = 'REMOVE'",nativeQuery = true)
    Optional<List<GuestSuggestionEntity>> findAllRemovedSuggestions();
}