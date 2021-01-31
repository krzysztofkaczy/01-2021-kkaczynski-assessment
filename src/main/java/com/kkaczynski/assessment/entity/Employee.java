package com.kkaczynski.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.csv.CSVRecord;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Employee {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    private String email;

    private Integer phone;

    private LocalDateTime dateOfEmployment;

    private Double salary;
    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;
    @ManyToOne(cascade = CascadeType.ALL)
    private Position position;
// TODO: 30.01.2021 connect to database and add FK
    public Employee(CSVRecord csvMap) {
        this.name = csvMap.get("id");
        this.surname = csvMap.get("surname");
        this.address = new Address(csvMap.get("address.street"), csvMap.get("address.homeNumber"), csvMap.get("address.zipCode"), csvMap.get("address.city"));
        this.email = csvMap.get("email");
        this.phone = Integer.parseInt(csvMap.get("phone"));
        this.dateOfEmployment = LocalDateTime.parse(csvMap.get("dateOfEmployment"));
        this.salary = Double.parseDouble(csvMap.get("salary"));
        Address addressDepartment = new Address(csvMap.get("department.street"), csvMap.get("department.homeNumber"), csvMap.get("department.zipCode"), csvMap.get("department.city"));
        this.department = new Department(csvMap.get("department.name"), addressDepartment);
        this.position = new Position(csvMap.get("position"));
    }

    public Employee(String name, String surname, Address address, String email, Integer phone, LocalDateTime dateOfEmployment, Double salary, Department department, Position position) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.dateOfEmployment = dateOfEmployment;
        this.salary = salary;
        this.department = department;
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public void setDateOfEmployment(LocalDateTime dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
