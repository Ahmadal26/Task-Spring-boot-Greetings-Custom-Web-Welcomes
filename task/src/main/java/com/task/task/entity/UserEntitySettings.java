package com.task.task.entity;

import javax.persistence.*;

@Entity

@Table(name = "user_Setting")
public class UserEntitySettings {

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean receiveNewsletter;
    private String preferredLanguage;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isReceiveNewsletter() {
        return receiveNewsletter;
    }

    public void setReceiveNewsletter(boolean receiveNewsletter) {
        this.receiveNewsletter = receiveNewsletter;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
