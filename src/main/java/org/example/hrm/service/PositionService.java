package org.example.hrm.service;

import org.example.hrm.entity.Positions;
import org.example.hrm.iservice.IPositionService;
import org.example.hrm.repository.IPositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService implements IPositionService {

    @Autowired
    private IPositionsRepository positionRP;

    @Override
    public List<Positions> findAll() {
        return positionRP.findAll();
    }



    @Override
    public void updatePositionsById(String positionsName, String description, Integer positionsId) {
        positionRP.updatePositionsById(positionsName, description, positionsId);
    }


    @Override
    public void insertPosition(String positionName, String description) {
        positionRP.insertPosition(positionName, description);
    }

    @Override
    public Optional<Positions> findById(Integer integer) {
        return positionRP.findById(integer);
    }

    @Override
    public void deleteById(Integer integer) {
        positionRP.deleteById(integer);
    }

    @Override
    public boolean existsByPositionsName(String departmentName) {
        return positionRP.existsByPositionsName(departmentName);
    }

    @Override
    public boolean existsByPositionsName1(String departmentName) {
        return positionRP.existsByPositionsName1(departmentName);
    }
}
