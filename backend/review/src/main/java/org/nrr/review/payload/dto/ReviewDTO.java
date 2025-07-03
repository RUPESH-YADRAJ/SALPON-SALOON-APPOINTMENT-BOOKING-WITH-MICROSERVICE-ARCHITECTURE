package org.nrr.review.payload.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ReviewDTO {
    private long id;
    private  UserDto user;
    private SalonDto salon;
    private String reviewText;
    private double rating;
    private LocalDateTime createdAt;


}
