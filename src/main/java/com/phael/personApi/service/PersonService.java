package com.phael.personApi.service;

import com.phael.personApi.dto.request.PersonDTO;
import com.phael.personApi.dto.response.MessageResponseDTO;
import com.phael.personApi.entity.Person;
import com.phael.personApi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = Person.builder().build();

        Person savedPerson = personRepository.save(personDTO);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

}
