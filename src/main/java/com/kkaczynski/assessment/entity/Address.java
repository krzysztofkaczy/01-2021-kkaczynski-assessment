package com.kkaczynski.assessment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@ToString
public class Address {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long id;

    private String street;

    private String homeNumber;

    private String zipCode;

    private String city;

    public Address(String street, String homeNumber, String zipCode, String city) {
        this.street = street;
        this.homeNumber = homeNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
