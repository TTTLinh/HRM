package org.example.hrm.repository;

import org.springframework.transaction.annotation.Transactional;
import org.example.hrm.entity.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPositionsRepository extends JpaRepository<Positions, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE job_positions SET positions_name = ?1, description = ?2 WHERE positions_id = ?3", nativeQuery = true)
    void updatePositionsById(String positionsName, String description, Integer positionsId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO job_positions (positions_name, description)"+"VALUE(?1,?2)",nativeQuery = true)
    void insertPosition(String positionName, String description);


    @Query(value="SELECT COUNT(p) > 0 FROM Positions p WHERE p.positionsName = ?1")
    boolean existsByPositionsName(String departmentName);

    @Query(value="SELECT COUNT(p) > 1 FROM Positions p WHERE p.positionsName = ?1")
    boolean existsByPositionsName1(String departmentName);

}
