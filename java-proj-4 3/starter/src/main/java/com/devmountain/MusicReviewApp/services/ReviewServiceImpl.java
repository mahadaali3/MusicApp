package com.devmountain.MusicReviewApp.services;

import com.devmountain.MusicReviewApp.dto.ReviewDto;
import com.devmountain.MusicReviewApp.entities.Music;
import com.devmountain.MusicReviewApp.entities.Review;
import com.devmountain.MusicReviewApp.entities.User;
import com.devmountain.MusicReviewApp.repositories.MusicRepository;
import com.devmountain.MusicReviewApp.repositories.ReviewRepository;
import com.devmountain.MusicReviewApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public List<ReviewDto> getAllReviews(Long musicId) {
        Optional<Music> musicOptional = musicRepository.findById(musicId);
        if(musicOptional.isPresent()){
            List<Review> reviewList = reviewRepository.findAllByMusicEquals(musicOptional.get());
            return reviewList.stream().map(review -> new ReviewDto(review)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addReview(ReviewDto reviewDto, Long userId, Long musicId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Music> musicOptional = musicRepository.findById(musicId);
        Review review = new Review(reviewDto);

        if(musicOptional.isPresent()){
            if(userOptional.isPresent()){
                review.setUser(userOptional.get());
                review.setMusic(musicOptional.get());
            }
        }
        reviewRepository.saveAndFlush(review);

    }

    @Override
    @Transactional
    public void deleteReview(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isPresent()){
            reviewRepository.delete(reviewOptional.get());
        }
    }
}
