package org.nrr.review.services.impl;

import org.nrr.review.model.Review;
import org.nrr.review.payload.dto.ReviewRequest;
import org.nrr.review.payload.dto.SalonDto;
import org.nrr.review.payload.dto.UserDto;
import org.nrr.review.repository.ReviewRepository;
import org.nrr.review.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review createReview(ReviewRequest reviewRequest, UserDto userDto, SalonDto salonDto) {
        Review review = new Review();
        review.setReviewText(reviewRequest.getReviewText());
        review.setRating(reviewRequest.getRating());
        review.setUserId(userDto.getId());
        review.setSalonId(salonDto.getId());
        reviewRepository.save(review);
        return null;
    }

    @Override
    public List<Review> getReviewsBySalonId(Long salonId) {
        return reviewRepository.findAllBySalonId(salonId);
    }

    private Review getReviewById(Long reviewId) throws Exception {
        Review review=reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            throw new Exception("Review not exist! ");
        }
        return review;
    }

    @Override
    public Review updateReview(ReviewRequest reviewRequest, Long reviewId, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if(!review.getUserId().equals(userId)) {
            throw new Exception("You don't have permission to update this review!");
        }
        review.setReviewText(reviewRequest.getReviewText());
        review.setRating(reviewRequest.getRating());
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review=getReviewById(reviewId);
        if(!review.getUserId().equals(userId)) {
            throw new Exception("You don't have permission to delete this review!");
        }
        reviewRepository.delete(review);
    }
}
