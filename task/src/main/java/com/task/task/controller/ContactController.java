package com.task.task.controller;

import com.task.task.bo.CreateAddContact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class ContactController {


    List<CreateAddContact> allContact = new ArrayList<>();
    @PostMapping("/addContact")
    public ResponseEntity<String> addContact (@RequestBody CreateAddContact createAddContact) {

        for (int i = 0; i < allContact.size(); i++) {

            if (allContact.get(i).getEmail().equals( createAddContact.getEmail())) {
                return  ResponseEntity.badRequest().body("Contact Created");
            }
        }
        allContact.add(createAddContact);
        return  ResponseEntity.ok("Contact added successfully");

    }
    @GetMapping("/getContactDetails")
    public ResponseEntity<?> getContactDetails(@RequestParam String name){

        for (int i=0; i<allContact.size();i++){
if(allContact.get(i).getName().equals(name)){
    return ResponseEntity.ok(allContact.get(i));
        }

    }
return ResponseEntity.badRequest().body("Contact not found");
    }
}

