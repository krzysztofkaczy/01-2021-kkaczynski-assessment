package com.kkaczynski.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
public class Position {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    private String name;

    public Position(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
