package com.task.task;

import com.task.task.entity.GuestSuggestionEntity;
import com.task.task.entity.UserEntity;
import com.task.task.repository.GuestRepository;
import com.task.task.repository.UserRepository;
import com.task.task.service.User.UserService;
import com.task.task.service.suggestion.SuggestionsService;
import com.task.task.util.enums.SuggestionStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TaskApplicationTests {

//    @Test
//    void contextLoads() {
//    }


    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @MockBean
    private GuestRepository guestRepository;

    @Autowired
    private SuggestionsService suggestionsService;

    @Test
    public void passLargerThanEight() {
        UserEntity user1 = new UserEntity();
        user1.setName("Ahmad");
        user1.setPassword("32897648");

        UserEntity user2 = new UserEntity();
        user2.setName("Osama");
        user2.setPassword("1232311231");

        UserEntity user3 = new UserEntity();
        user3.setName("Wahab");
        user3.setPassword("23681");

        List<UserEntity> mockUsers = Arrays.asList(user1, user2, user3);

        Mockito.when(userRepository.findAll()).thenReturn(mockUsers);

        assertEquals(Arrays.asList("Ahmad", "Osama","Wahab"), userService.getAllUsersWithStrongPassword());
    }

    @Test
    public void whenGetCreateStatusSuggestions_thenSuccess() {
        List<GuestSuggestionEntity> suggestions = Arrays.asList(new GuestSuggestionEntity("note1", 10, SuggestionStatus.CREATE), new GuestSuggestionEntity("note2", 8, SuggestionStatus.CREATE) );
        Mockito.when(guestRepository.findAllCreatedSuggestions()).thenReturn(Optional.of(suggestions));

        Assertions.assertEquals(Arrays.asList(new GuestSuggestionEntity("note3", 2, SuggestionStatus.CREATE), new GuestSuggestionEntity("note4", 3, SuggestionStatus.CREATE)), guestRepository.findAllCreatedSuggestions());
    }
    @Test
    public void whenGetRemoveStatusSuggestions_thenSuccess(){
        List<GuestSuggestionEntity> suggestions = Arrays.asList(new GuestSuggestionEntity("text", 5, SuggestionStatus.CREATE), new GuestSuggestionEntity("text", 5, SuggestionStatus.CREATE), new GuestSuggestionEntity("text", 5, SuggestionStatus.REMOVE), new GuestSuggestionEntity("text", 5, SuggestionStatus.REMOVE), new GuestSuggestionEntity("text", 5, SuggestionStatus.CREATE));
        Mockito.when(guestRepository.findAll()).thenReturn(suggestions);

        Assertions.assertEquals(Arrays.asList(new GuestSuggestionEntity("text", 5, SuggestionStatus.REMOVE).getStatus(), new GuestSuggestionEntity("text", 5, SuggestionStatus.REMOVE).getStatus()), suggestionsService.findSuggestions("REMOVE").stream().map(GuestSuggestionEntity::getStatus).collect(Collectors.toList()));
    }
}

