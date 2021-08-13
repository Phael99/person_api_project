package com.phael.personApi.service;

import com.phael.personApi.dto.request.PersonDTO;
import com.phael.personApi.dto.response.MessageResponseDTO;
import com.phael.personApi.entity.Person;
import com.phael.personApi.exception.PersonNotFoundException;
import com.phael.personApi.repository.PersonRepository;
import mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public MessageResponseDTO createPerson(PersonDTO personDTO) {

        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson, "Created person with ID ");
    }


    public List<PersonDTO> listAll() {
       List<Person> allPeople = personRepository.findAll();
       return allPeople.stream()
               .map(personMapper::toDTO)
               .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
       Person person = verifyIfExists(id);

      return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException{

        return  personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(savedPerson, "Updated person with ID ");
    }

    private MessageResponseDTO createMessageResponse(Person savedPerson, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + savedPerson.getId())
                .build();
    }
}
