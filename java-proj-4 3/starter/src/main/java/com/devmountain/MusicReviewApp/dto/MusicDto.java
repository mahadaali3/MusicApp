package com.devmountain.MusicReviewApp.dto;

import com.devmountain.MusicReviewApp.entities.Music;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicDto implements Serializable {
    private Long id;
    private String title;
    private String artist;
    private String genre;
    private int year;

    private UserDto userDto;

    public MusicDto(Music music){
        if(music.getId() != null){
            this.id = music.getId();
        }
        if(music.getTitle() != null){
            this.title = music.getTitle();
        }
        if(music.getArtist() != null){
            this.artist = music.getArtist();
        }
        if(music.getGenre() != null){
            this.genre = music.getGenre();
        }
        if(music.getYear() != 0){
            this.year = music.getYear();
        }
    }

}
