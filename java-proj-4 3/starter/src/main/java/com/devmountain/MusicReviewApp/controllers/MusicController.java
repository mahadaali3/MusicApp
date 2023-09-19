package com.devmountain.MusicReviewApp.controllers;

import com.devmountain.MusicReviewApp.dto.MusicDto;
import com.devmountain.MusicReviewApp.repositories.MusicRepository;
import com.devmountain.MusicReviewApp.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class MusicController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/music")
    public List<MusicDto> getAllMusic(){
        return musicService.getAllMusic();
    }

    @PostMapping("/music")
    public void addMusic(@RequestBody MusicDto musicDto){
        musicService.addMusic(musicDto);
    }

    @DeleteMapping("/music/{musicId}")
    public void deleteMusicById(@PathVariable Long musicId){
        musicService.deleteMusicById(musicId);
    }

    @PutMapping("/music")
    public void updateMusicById(@RequestBody MusicDto musicDto){
        musicService.updateMusicById(musicDto);
    }


}
