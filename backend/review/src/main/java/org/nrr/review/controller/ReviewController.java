package org.nrr.review.controller;

import org.nrr.review.model.Review;
import org.nrr.review.payload.dto.ApiResponse;
import org.nrr.review.payload.dto.ReviewRequest;
import org.nrr.review.payload.dto.SalonDto;
import org.nrr.review.payload.dto.UserDto;
import org.nrr.review.services.ReviewService;
import org.nrr.review.services.clients.SalonFeignClient;
import org.nrr.review.services.clients.UserFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserFeignClient userFeignClient;
    private final SalonFeignClient salonFeignClient;

    public ReviewController(ReviewService reviewService, UserFeignClient userFeignClient, SalonFeignClient salonFeignClient) {
        this.reviewService = reviewService;
        this.userFeignClient = userFeignClient;
        this.salonFeignClient = salonFeignClient;
    }

    @PostMapping("/salon/{salonId}")
    public ResponseEntity<List<Review>> createReview(@PathVariable Long salonId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user= userFeignClient.getUserFromJwtToken(jwt).getBody();
        SalonDto salon=salonFeignClient.getSalonById(salonId).getBody();
        List<Review> review=reviewService.getReviewsBySalonId(salon.getId());
        return ResponseEntity.ok(review);
    }


    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<Review>> getReviewsBySalonId(@PathVariable Long salonId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user= userFeignClient.getUserFromJwtToken(jwt).getBody();
        SalonDto salon=salonFeignClient.getSalonById(salonId).getBody();
        List<Review> review=reviewService.getReviewsBySalonId(salon.getId());
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId,
                                                            @RequestBody ReviewRequest reviewRequest,
                                                            @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user= userFeignClient.getUserFromJwtToken(jwt).getBody();
        Review review=reviewService.updateReview(reviewRequest,reviewId,user.getId());
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user= userFeignClient.getUserFromJwtToken(jwt).getBody();
        reviewService.deleteReview(reviewId,user.getId());
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Review deleted");
        return ResponseEntity.ok(apiResponse);
    }
}
