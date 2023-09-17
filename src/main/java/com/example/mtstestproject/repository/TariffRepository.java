package com.example.mtstestproject.repository;

import com.example.mtstestproject.model.TariffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffRepository extends JpaRepository<TariffEntity, Long> {
    List<TariffEntity> findByUpdateId(long id);
}
