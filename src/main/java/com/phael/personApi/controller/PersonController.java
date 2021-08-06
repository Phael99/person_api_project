package com.phael.personApi.controller;


import com.phael.personApi.entity.Person;
import com.phael.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageResponseDTO String createPerson(@RequestBody Person person){
        Person savedPerson = personRepository.save(person);
        return "API test";
    }

}
