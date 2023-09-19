package com.devmountain.MusicReviewApp.services;

import com.devmountain.MusicReviewApp.dto.MusicDto;

import javax.transaction.Transactional;
import java.util.List;

public interface MusicService {


    List<MusicDto> getAllMusic();




   @Transactional
    void addMusic(MusicDto musicDto);

   @Transactional
    void deleteMusicById(Long musicId);

    @Transactional
    void updateMusicById(MusicDto musicDto);
}


