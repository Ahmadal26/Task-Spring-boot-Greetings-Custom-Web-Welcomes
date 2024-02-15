package com.task.task.service.suggestion.functioninterface;

import com.task.task.bo.suggestion.CreateSuggestionRequest;

public interface ProcessSuggestion {
    void processSuggestion(CreateSuggestionRequest createSuggestionRequest);
}
