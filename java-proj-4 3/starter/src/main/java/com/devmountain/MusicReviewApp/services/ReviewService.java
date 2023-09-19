package com.devmountain.MusicReviewApp.services;

import com.devmountain.MusicReviewApp.dto.MusicDto;
import com.devmountain.MusicReviewApp.dto.ReviewDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ReviewService {
    @Transactional
    List<ReviewDto> getAllReviews(Long musicId);

    @Transactional
    void addReview(ReviewDto reviewDto, Long userId, Long musicId);

    @Transactional
    void deleteReview(Long reviewId);
}
