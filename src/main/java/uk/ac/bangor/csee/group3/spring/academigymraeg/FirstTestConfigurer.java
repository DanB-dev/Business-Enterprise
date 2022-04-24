package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Question;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Test;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.TestRepository;

@Component
public class FirstTestConfigurer {

	@Autowired
	private RepositoryBasedNounImpl nounDetails;
	
	@Autowired
	private TestRepository repository;

	@Autowired
	private RepositoryBasedTestImpl testDetails;

	@PostConstruct
	public void createFirstTest() {
		try {
			testDetails.loadTestById("1");
		} catch (TestNotFoundException e) {
			Test firstTest = new Test();
			firstTest.setQuestions(generateQuestions(20));
			firstTest.setId("1");
			repository.save(firstTest);
		}
	}

	private List<Question> generateQuestions(int number) {
		List<Question> q = new ArrayList<Question>();

		for (int i = 0; i < number; i++) {
			Question w = generate();

			q.add(w);
		}
		return q;
	}

	private Question generate() {
		Noun noun = nounDetails.loadNounByCyNoun("tatws");
		Question finalQ = new Question();
		int rnd = (int) Math.floor(Math.random() * 3);
		switch (rnd) {
		case 0:
			finalQ.setQuestion("What is Welsh for " + noun.getEnNoun());
			finalQ.setAnswer(noun.getCyNoun());
			break;
		case 1:
			finalQ.setQuestion("What is English for " + noun.getCyNoun());
			finalQ.setAnswer(noun.getEnNoun());
			break;
		case 2:
			finalQ.setQuestion("What is the gender of the welsh noun " + noun.getCyNoun());
			finalQ.setAnswer(noun.getCyGender());
			break;
		default:
			break;
		}

		return finalQ;
	}
}
