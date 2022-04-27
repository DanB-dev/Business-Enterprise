package uk.ac.bangor.csee.group3.spring.academigymraeg;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.NounRepository;

@Component
public class FirstNounConfigurer {

	@Autowired
	private NounRepository repository;

	@Autowired
	private RepositoryBasedNounImpl nounDetails;

	@PostConstruct
	public void createFirstNoun() {
		try {
			Noun loaded = nounDetails.loadNounByCyNoun("tatws");
			System.out.println(loaded);
		} catch (NounNotFoundException e) {
			Noun firstNoun = new Noun();
			firstNoun.setCyNoun("tatws");
			firstNoun.setCyGender("feminine");
			firstNoun.setEnNoun("potato");
			repository.save(firstNoun);
		}
	}

}
