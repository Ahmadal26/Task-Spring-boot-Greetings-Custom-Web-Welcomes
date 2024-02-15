package com.task.task.service.suggestion;


import com.task.task.bo.suggestion.CreateSuggestionRequest;
import com.task.task.entity.GuestSuggestionEntity;
import com.task.task.repository.GuestRepository;
import com.task.task.service.suggestion.functioninterface.ProcessSuggestion;
import com.task.task.util.enums.SuggestionStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SuggestionsServiceImpl implements SuggestionsService{
    private final GuestRepository guestRepository;

    public SuggestionsServiceImpl(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public void createSuggestion(CreateSuggestionRequest createSuggestionRequest) {
        ProcessSuggestion function = suggestion -> {
            GuestSuggestionEntity guestEntity = new GuestSuggestionEntity();
            guestEntity.setSuggestionText(suggestion.getSuggestionText());
            guestEntity.setRate(suggestion.getRate());
            guestEntity.setStatus(SuggestionStatus.valueOf(suggestion.getStatus().toUpperCase()));
            guestRepository.save(guestEntity);
        };
    }

    @Override
    public List<GuestSuggestionEntity> findSuggestions(String status) {
        List<GuestSuggestionEntity> suggestions = guestRepository.findAll()
                .stream()
                .filter(guestEntity -> guestEntity.getStatus().toString().equals(status))
                .collect(Collectors.toList());
        return suggestions;
    }


}