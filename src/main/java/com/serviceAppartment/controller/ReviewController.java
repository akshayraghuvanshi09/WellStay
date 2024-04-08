package com.serviceAppartment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviceAppartment.dto.ReviewDTO;
import com.serviceAppartment.service.ReviewService;

@RestController
@RequestMapping("/apartments")
public class ReviewController {
    
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/reviews")
    public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDto) {
        ReviewDTO addedReview = reviewService.addOrUpdateReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReview);
    }
}
