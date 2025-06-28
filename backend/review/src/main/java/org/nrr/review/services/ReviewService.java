package org.nrr.review.services;

import org.nrr.review.model.Review;
import org.nrr.review.payload.dto.ReviewRequest;
import org.nrr.review.payload.dto.SalonDto;
import org.nrr.review.payload.dto.UserDto;

import java.util.List;

public interface ReviewService {
    Review createReview(ReviewRequest reviewRequest, UserDto userDto, SalonDto salonDto);
    List<Review> getReviewsBySalonId(Long salonId);
    Review updateReview(ReviewRequest reviewRequest, Long reviewId,Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
}
