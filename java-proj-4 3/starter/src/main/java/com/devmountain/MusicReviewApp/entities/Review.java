package com.devmountain.MusicReviewApp.entities;

import com.devmountain.MusicReviewApp.dto.ReviewDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference // Prevents infinite recursion when delivering resource as Json through RESTful API endpoint.
    private User user;

    @ManyToOne
    @JoinColumn(name = "music_id")
    @JsonBackReference
    private Music music;


    public Review(ReviewDto reviewDto){
        if(reviewDto.getReview() != null){
            this.review = reviewDto.getReview();
        }
    }




}
