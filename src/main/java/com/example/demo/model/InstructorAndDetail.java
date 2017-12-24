package com.example.demo.model;

/**
 * Created by Nyansus on 05.12.2017.
 */
public class InstructorAndDetail {

    private String fname;
    private String lname;
    private String email;
    private String hobby;
    private String channel;

    public InstructorAndDetail() {
    }

    public InstructorAndDetail(Instructor instructor, InstructorDetail detail) {
        fname = instructor.getFirstName();
        lname = instructor.getSecondName();
        email = instructor.getEmail();
        hobby = detail.getHobby();
        channel = detail.getYoutubeChannel();


    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
