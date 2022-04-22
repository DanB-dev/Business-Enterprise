package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Test;

import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.TestRepository;

@Component
public class RepositoryBasedTestImpl {
	
	@Autowired
	private TestRepository repository;
	
	public Test loadTestById(String id) throws TestNotFoundException{
		Optional<Test> t = repository.findById(id);
		
		if(t.isPresent())
			return t.get();
		throw new TestNotFoundException(id + "not found");
	}
	
	public List<Test> loadAllTests(){
		return (List<Test>) repository.findAll();
	}
}
