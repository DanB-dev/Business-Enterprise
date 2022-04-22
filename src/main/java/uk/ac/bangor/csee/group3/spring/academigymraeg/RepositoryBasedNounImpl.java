package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.NounRepository;

@Component
public class RepositoryBasedNounImpl {
	
	@Autowired
	private NounRepository repository;
	
	
	public Noun loadNounByCyNoun(String cyNoun) throws NounNotFoundException{
		Optional<Noun> n = repository.findById(cyNoun);

		if(n.isPresent())
			return n.get();
		throw new NounNotFoundException(cyNoun + "not found");
	}
	
	public List<Noun> loadAllNouns(){
		return (List<Noun>) repository.findAll();
	}
}
