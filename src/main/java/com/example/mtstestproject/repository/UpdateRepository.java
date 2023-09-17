package com.example.mtstestproject.repository;

import com.example.mtstestproject.model.UpdateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRepository extends JpaRepository<UpdateEntity, Long> {
    UpdateEntity findTopByOrderByIdDesc();
}
