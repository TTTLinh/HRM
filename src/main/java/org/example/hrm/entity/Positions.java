package org.example.hrm.entity;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "job_positions")
public class Positions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "positions_id")
    private int positionsId;

    @Column(name = "positions_name")
    private String positionsName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "positions")
    private List<Employee> positionsEmployees;

    public Positions() {
    }

    public Positions(int positionsId, String positionsName, String description, List<Employee> positionsEmployees) {
        this.positionsId = positionsId;
        this.positionsName = positionsName;
        this.description = description;
        this.positionsEmployees = positionsEmployees;
    }

    public int getPositionsId() {
        return positionsId;
    }

    public void setPositionsId(int positionsId) {
        this.positionsId = positionsId;
    }

    public String getPositionsName() {
        return positionsName;
    }

    public void setPositionsName(String positionsName) {
        this.positionsName = positionsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getPositionsEmployees() {
        return positionsEmployees;
    }

    public void setPositionsEmployees(List<Employee> positionsEmployees) {
        this.positionsEmployees = positionsEmployees;
    }
}

