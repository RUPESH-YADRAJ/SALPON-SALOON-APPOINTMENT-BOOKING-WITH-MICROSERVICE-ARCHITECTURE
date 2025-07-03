package org.nrr.review.mapper;

import org.nrr.review.model.Review;
import org.nrr.review.payload.dto.ReviewDTO;
import org.nrr.review.payload.dto.UserDto;

public class ReviewMapper {
    public static ReviewDTO toDTO(Review review, UserDto user) {
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(review.getId());
        reviewDTO.setReviewText(review.getReviewText());
        reviewDTO.setUser(user);
        reviewDTO.setRating(review.getRating());
        reviewDTO.setCreatedAt(review.getCreatedAt());
        return reviewDTO;
    }
}
