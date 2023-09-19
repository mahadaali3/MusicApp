package com.devmountain.MusicReviewApp.dto;

import com.devmountain.MusicReviewApp.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Long id;
    private String review;

    private UserDto userDto;
    private MusicDto musicDto;

    public ReviewDto(Review review){
        if (review.getId() != null) {
            this.id = review.getId();
        }
        if (review.getReview() != null) {
            this.review = review.getReview();
        }
        if (review.getUser() != null) {
            this.userDto = new UserDto(review.getUser());
        }
        if (review.getMusic() != null) {
            this.musicDto = new MusicDto((review.getMusic()));
        }

    }

}
