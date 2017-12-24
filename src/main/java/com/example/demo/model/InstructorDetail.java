package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
   /* @NotNull
    @Size(min=2)*/
    @Column(name = "hobby")
    private String hobby;
    @Column(name = "youtube_channel")
    private String youtubeChannel;


    public InstructorDetail() {
    }

    public InstructorDetail(String hobby, String youtubeChannel) {
        this.hobby = hobby;
        this.youtubeChannel = youtubeChannel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", hobby='" + hobby + '\'' +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                '}';
    }
}
