package org.example.hrm.iservice;

import org.example.hrm.entity.Positions;

import java.util.List;
import java.util.Optional;

public interface IPositionService{
    List<Positions> findAll();

    void updatePositionsById(String positionsName, String description, Integer positionsId);

    void insertPosition(String positionName, String description);

    Optional<Positions> findById(Integer integer);

    void deleteById(Integer integer);

    boolean existsByPositionsName(String departmentName);

    boolean existsByPositionsName1(String departmentName);
}
