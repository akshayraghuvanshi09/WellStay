package com.serviceAppartment.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String street;
	    private String city;
	    private String state;
	    private String zipCode;

	    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
	    private Apartment apartment;
	    public String toString() {
	        return "Address{" +
	                "id=" + id +
	                ", street='" + street + '\'' +
	                ", city='" + city + '\'' +
	                ", state='" + state + '\'' +
	                ", zipCode='" + zipCode + '\'' +
	                '}';
	    }
}