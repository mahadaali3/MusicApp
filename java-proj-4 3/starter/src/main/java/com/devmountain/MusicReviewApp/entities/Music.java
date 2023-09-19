package com.devmountain.MusicReviewApp.entities;

import com.devmountain.MusicReviewApp.dto.MusicDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Music")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String artist;

    private String genre;

    private int year;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Music(){

    }

    public Music(MusicDto musicDto){
        if (musicDto.getTitle() != null){
            this.title = musicDto.getTitle();
        }
        if (musicDto.getArtist() != null){
            this.artist = musicDto.getArtist();
        }
        if (musicDto.getGenre() != null){
            this.genre = musicDto.getGenre();
        }
        if (musicDto.getYear() != 0){
            this.year = musicDto.getYear();
        }
    }
}
