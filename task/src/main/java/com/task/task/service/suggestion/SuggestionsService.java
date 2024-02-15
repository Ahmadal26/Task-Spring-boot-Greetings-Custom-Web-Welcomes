package com.task.task.service.suggestion;


import com.task.task.bo.suggestion.CreateSuggestionRequest;
import com.task.task.entity.GuestSuggestionEntity;

import java.util.List;

public interface SuggestionsService {
    void createSuggestion(CreateSuggestionRequest createSuggestionRequest);

    List<GuestSuggestionEntity> findSuggestions(String status);



}