package com.serviceAppartment.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "appartment")
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private String type;

	private double price;
	private boolean available;
	private double size;
	private int floor;
	private int yearBuilt;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "room_id")
	private Room room;

//	    @OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
//	    private List<Amenity> amenities;

	@OneToMany(mappedBy = "apartment", cascade = CascadeType.ALL)
	private List<Image> images = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "apartment_amenities", joinColumns = @JoinColumn(name = "apartment_id"), inverseJoinColumns = @JoinColumn(name = "amenity_id"))
	private List<Amenity> amenities;

	@OneToOne(mappedBy = "apartment")
	private Booking booking;
	
	

}
