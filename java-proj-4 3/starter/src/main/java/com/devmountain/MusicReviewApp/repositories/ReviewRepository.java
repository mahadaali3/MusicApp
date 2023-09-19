package com.devmountain.MusicReviewApp.repositories;

import com.devmountain.MusicReviewApp.entities.Music;
import com.devmountain.MusicReviewApp.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMusicEquals(Music music);
}
