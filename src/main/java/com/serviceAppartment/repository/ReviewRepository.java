package com.serviceAppartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceAppartment.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	public Review findByApartmentIdAndUserId(Long apartmentId,Long userId);
}
