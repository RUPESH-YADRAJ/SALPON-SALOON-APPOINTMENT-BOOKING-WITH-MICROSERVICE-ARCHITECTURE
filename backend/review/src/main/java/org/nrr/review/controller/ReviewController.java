package org.nrr.review.controller;

import org.nrr.review.mapper.ReviewMapper;
import org.nrr.review.model.Review;
import org.nrr.review.payload.dto.*;
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
    public ResponseEntity<Review> createReview(@PathVariable Long salonId,
                                               @RequestBody ReviewRequest reviewRequest,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        UserDto user= userFeignClient.getUserFromJwtToken(jwt).getBody();
        SalonDto salon=salonFeignClient.getSalonById(salonId).getBody();
        Review review=reviewService.createReview(reviewRequest,user,salon);

        return ResponseEntity.ok(review);
    }


    @GetMapping("/salon/{salonId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsBySalonId(@PathVariable Long salonId,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        SalonDto salon=salonFeignClient.getSalonById(salonId).getBody();
        List<Review> reviews=reviewService.getReviewsBySalonId(salon.getId());
        List<ReviewDTO> reviewDTOS=reviews.stream().map((review)->{
            UserDto user;
                    try{
                        user= userFeignClient.getUserById(review.getUserId()).getBody();
                    }catch (Exception e){

                        throw new RuntimeException(e);
                    }
                    return ReviewMapper.toDTO(review,user);
        }).toList();

        return ResponseEntity.ok(reviewDTOS);
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
