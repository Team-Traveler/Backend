package com.example.traveler.repository;

import com.example.traveler.model.entity.ChecklistEntity;
import com.example.traveler.model.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChecklistRepository extends JpaRepository<ChecklistEntity, Integer> {
    public ChecklistEntity findByTravel(Travel travel);
    Optional<ChecklistEntity> findBycIdAndState(int cId, int state);

    public List<ChecklistEntity> findAllByTravel(Travel travel);

    Optional<ChecklistEntity> findBycId(Integer cId);
}