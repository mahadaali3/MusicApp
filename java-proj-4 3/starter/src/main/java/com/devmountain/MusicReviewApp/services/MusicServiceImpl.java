package com.devmountain.MusicReviewApp.services;

import com.devmountain.MusicReviewApp.dto.MusicDto;
import com.devmountain.MusicReviewApp.entities.Music;
import com.devmountain.MusicReviewApp.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicRepository musicRepository;



    public List<MusicDto> getAllMusic(){
        List<Music> musicList = musicRepository.findAll();
        return musicList.stream().map(music -> new MusicDto(music)).collect(Collectors.toList());
    }



    @Transactional
    public void addMusic(MusicDto musicDto){

        Music music = new Music(musicDto);
        musicRepository.saveAndFlush(music);


    }


    @Override
    @Transactional
    public void deleteMusicById(Long musicId) {
        Optional<Music> musicOptional = musicRepository.findById(musicId);
        musicOptional.ifPresent(music -> musicRepository.delete(music));
    }

    @Override
    public void updateMusicById(MusicDto musicDto) {
        Optional<Music> musicOptional = musicRepository.findById(musicDto.getId());
        musicOptional.ifPresent(note -> {
            note.setTitle(musicDto.getTitle());
            note.setArtist(musicDto.getArtist());
            note.setGenre(musicDto.getGenre());
            note.setYear(musicDto.getYear());
            musicRepository.saveAndFlush(note);
        });
    }


}
