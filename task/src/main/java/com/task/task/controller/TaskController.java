package com.task.task.controller;

import com.task.task.bo.CreateFarewellRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @GetMapping("/sayHi")
    public String sayHi(){
        return "Welcome Ahmad";
    }

    @GetMapping ("/greet")

    public String showName(@RequestParam String name ){
        return "Hello: " +name;
    }
    @PostMapping ("/farewell")

    public String byeName(@RequestBody CreateFarewellRequest createFarewellRequest){

        return "Goodbye " + createFarewellRequest.getName();
    }


}
