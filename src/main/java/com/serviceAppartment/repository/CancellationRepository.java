package com.serviceAppartment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serviceAppartment.model.Cancellation;

@Repository
public interface CancellationRepository extends JpaRepository<Cancellation,Long> {
	public List<Cancellation> findByApartmentId(Long apartmentId);
}
