package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		if (nounDetails.loadAllNouns().size() < 1) {
			
			List<Noun> toReturn = null;
			
			ObjectMapper oMapper = new ObjectMapper();
			try {
				toReturn = oMapper.readValue(FirstNounConfigurer.class.getResourceAsStream("/nouns.json"), new TypeReference<List<Noun>>() {});
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			repository.saveAll(toReturn);
			
			
		}

	}
	
	public void getProducts() {

	}

}
