package com.serviceAppartment.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.serviceAppartment.dto.ApartmentDTO;
import com.serviceAppartment.service.ApartmentService;

@RestController
@RequestMapping("/apartments")
public class ApartmentController {

	@Autowired
	private ApartmentService apartmentService;

	@GetMapping
	public ResponseEntity<List<ApartmentDTO>> getAllApartments() {
		System.out.println("heloo");
		List<ApartmentDTO> apartments = apartmentService.getAllApartments();
		return ResponseEntity.ok(apartments);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApartmentDTO> getApartmentById(@PathVariable Long id) {
		ApartmentDTO apartment = apartmentService.getApartmentById(id);
		return ResponseEntity.ok(apartment);
	}

//	@PostMapping(consumes = {"multipart/form-data"})
//    public ResponseEntity<ApartmentDTO> createApartment(@RequestBody ApartmentDTO apartmentDto,
//                                                        @RequestParam("image") List<MultipartFile> imageFile) {
	@PostMapping(consumes = {"multipart/form-data"})
	public ResponseEntity<ApartmentDTO> createApartment(@RequestPart("apartmentDto") ApartmentDTO apartmentDto,
	                                                    @RequestPart("imageFile") List<MultipartFile>  imageFile) {
		ApartmentDTO createdApartment = apartmentService.createOrUpdateApartment(apartmentDto,imageFile);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdApartment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApartmentDTO> updateApartment(@PathVariable Long id, @RequestBody ApartmentDTO apartmentDto) {
		ApartmentDTO updatedApartment = apartmentService.updateApartment(id, apartmentDto);
		return ResponseEntity.ok(updatedApartment);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteApartment(@PathVariable Long id) {
		apartmentService.deleteApartment(id);
		return ResponseEntity.noContent().build();
	}
	
	 @GetMapping("/available")
	    public List<ApartmentDTO> getAvailableApartments(
	            @RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
	            @RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {
	        return apartmentService.getAvailableApartments(checkInDate, checkOutDate);
	    }
}
