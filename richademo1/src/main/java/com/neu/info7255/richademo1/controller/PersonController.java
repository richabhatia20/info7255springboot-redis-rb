package com.neu.info7255.richademo1.controller;

import javax.validation.Valid;
import com.neu.info7255.richademo1.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/persons")
public class PersonController {
	
	private CrudRepository<Person, String> repository;
	
	@Autowired
	public PersonController(CrudRepository<Person, String> repository){
		
		this.repository = repository;
	}
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Person> persons(){
		
		
		return repository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Person add(@RequestBody @Valid Person person){
		
		return repository.save(person);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Person update(@RequestBody @Valid Person person){
		
		return repository.save(person);
	}
	
	@RequestMapping(value = "/{emailAddress:.+}", method = RequestMethod.GET)
    public Person getById(@PathVariable String emailAddress) {
        return repository.findOne(emailAddress);
    }

    @RequestMapping(value = "/{emailAddress:.+}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable String emailAddress) {
        repository.delete(emailAddress);
    }

}
