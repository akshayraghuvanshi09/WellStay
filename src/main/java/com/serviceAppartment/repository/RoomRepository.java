package com.serviceAppartment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviceAppartment.model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
