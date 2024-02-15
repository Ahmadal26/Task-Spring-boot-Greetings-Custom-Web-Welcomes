package com.task.task.bo.suggestion;

public class CreateSuggestionRequest {
    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    private String suggestionText;
    private int rate;
}
