package com.kkaczynski.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
public class Department {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Address localization;

    public Department(String name, Address localization) {
        this.name = name;
        this.localization = localization;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setLocalization(Address localization) {
        this.localization = localization;
    }
}
