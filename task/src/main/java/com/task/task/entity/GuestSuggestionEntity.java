package com.task.task.entity;

import com.task.task.util.enums.SuggestionStatus;

import javax.persistence.*;

@Entity
@Table(name = "guest_suggestion")
    public class GuestSuggestionEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public GuestSuggestionEntity( String suggestionText, long rate, SuggestionStatus status) {

        this.suggestionText = suggestionText;
        this.rate = rate;
        this.status = status;
    }

    private String suggestionText;

    private long rate;

    @Enumerated(EnumType.STRING)
    private SuggestionStatus status;

    public GuestSuggestionEntity() {

    }


    public SuggestionStatus getStatus() {
        return status;
    }

    public void setStatus(SuggestionStatus status) {
        this.status = status;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }
}