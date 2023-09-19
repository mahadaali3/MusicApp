package com.devmountain.MusicReviewApp.controllers;

import com.devmountain.MusicReviewApp.dto.ReviewDto;
import com.devmountain.MusicReviewApp.services.MusicService;
import com.devmountain.MusicReviewApp.services.ReviewService;
import com.devmountain.MusicReviewApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private MusicService musicService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/music/{musicId}")
    public List<ReviewDto> getAllReviews(@PathVariable Long musicId){
        return reviewService.getAllReviews(musicId);
    }

    @PostMapping("{userId}/music/{musicId}")
    public void addReview(@RequestBody ReviewDto reviewDto, @PathVariable Long userId, @PathVariable Long musicId){
        reviewService.addReview(reviewDto,userId,musicId);
    }
    @DeleteMapping("/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId){
        reviewService.deleteReview(reviewId);
    }
}
