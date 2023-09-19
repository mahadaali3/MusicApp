package com.devmountain.MusicReviewApp.repositories;

import com.devmountain.MusicReviewApp.entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicRepository extends JpaRepository<Music, Long> {

}
