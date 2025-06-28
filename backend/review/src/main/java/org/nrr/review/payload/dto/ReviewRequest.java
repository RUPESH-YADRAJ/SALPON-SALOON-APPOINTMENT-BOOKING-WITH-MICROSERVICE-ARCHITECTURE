package org.nrr.review.payload.dto;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ReviewRequest {
//    private Long id;
    private String reviewText;
    private double rating;
//    private Long salonId;
//   private Long userId;
//    private LocalDateTime createdAt;
}