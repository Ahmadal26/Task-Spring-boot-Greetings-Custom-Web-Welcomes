package com.task.task.controller;

import com.task.task.bo.suggestion.CreateSuggestionRequest;
import com.task.task.entity.GuestSuggestionEntity;
import com.task.task.service.suggestion.SuggestionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guest")
public class SuggestionController {
    private final SuggestionsService suggestionsService;

    public SuggestionController(SuggestionsService suggestionsService) {
        this.suggestionsService = suggestionsService;
    }

    @PostMapping("/create-suggestion")
    public ResponseEntity<Void> processSuggestion(@RequestBody CreateSuggestionRequest createSuggestionRequest){
        suggestionsService.createSuggestion(createSuggestionRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<GuestSuggestionEntity>> getSuggestions(@RequestParam String status){
        List<GuestSuggestionEntity> suggestions = suggestionsService.findSuggestions(status.toUpperCase());
        return ResponseEntity.ok().body(suggestions);
    }


}
