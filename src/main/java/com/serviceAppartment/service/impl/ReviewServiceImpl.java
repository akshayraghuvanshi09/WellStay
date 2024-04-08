package com.serviceAppartment.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serviceAppartment.dto.ReviewDTO;
import com.serviceAppartment.model.Review;
import com.serviceAppartment.repository.ReviewRepository;
import com.serviceAppartment.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Override
	public ReviewDTO addOrUpdateReview(ReviewDTO reviewDto) {
		Optional<Review> existingReviewOptional = Optional.ofNullable(
				reviewRepository.findByApartmentIdAndUserId(reviewDto.getApartmentId(), reviewDto.getUserId()));
		if (existingReviewOptional.isPresent()) {
			// Update the existing review
			Review existingReview = existingReviewOptional.get();
			existingReview.setRating(reviewDto.getRating());
			existingReview.setComment(reviewDto.getComment());
			// Save the updated review
			existingReview = reviewRepository.save(existingReview);
			// Convert the updated review to DTO and return
			return convertToDto(existingReview);
		} else {
			// Create a new review
			Review review = new Review();
			review.setApartmentId(reviewDto.getApartmentId());
			review.setUserId(reviewDto.getUserId());
			review.setRating(reviewDto.getRating());
			review.setComment(reviewDto.getComment());
			// Save the new review
			review = reviewRepository.save(review);
			// Convert the new review to DTO and return
			return convertToDto(review);
		}
	}

	// Helper method to convert entity to DTO
	private ReviewDTO convertToDto(Review review) {
		ReviewDTO reviewDto = new ReviewDTO();
		reviewDto.setId(review.getId());
		reviewDto.setApartmentId(review.getApartmentId());
		reviewDto.setUserId(review.getUserId());
		reviewDto.setRating(review.getRating());
		reviewDto.setComment(review.getComment());
		return reviewDto;
	}

}
