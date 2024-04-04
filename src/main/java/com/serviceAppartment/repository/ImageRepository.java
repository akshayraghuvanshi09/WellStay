package com.serviceAppartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceAppartment.model.Image;

public interface ImageRepository extends JpaRepository<Image,Integer> {

}
