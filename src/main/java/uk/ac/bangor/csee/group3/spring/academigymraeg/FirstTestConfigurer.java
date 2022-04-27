package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		if (testDetails.loadAllTests().size() < 1) {
			Test firstTest = new Test();
			firstTest.setQuestions(generateQuestions(10));
			firstTest.setId("1");
			firstTest.setUser("admin");
			firstTest.setCreatedDate(Instant.now());
			firstTest.setStartedDate(Instant.now());
			firstTest.setResult(1);
			firstTest.setStatus("Submitted");
			repository.save(firstTest);
		}
	}

	private List<Question> generateQuestions(int number) {
		List<Noun> nouns = nounDetails.loadAllNouns();

		if (nouns.size() < number) {
			throw new IllegalArgumentException("You have too few Nouns");
		}

		List<Question> q = new ArrayList<Question>();
		List<Integer> used = new ArrayList<Integer>();

		for (int i = 0; i < number; i++) {

			int rnd = new Random().nextInt(nouns.size());
			while (used.contains(rnd)) {
				rnd = new Random().nextInt(nouns.size());
			}

			Question w = generate(nouns.get(rnd));
			q.add(w);
			used.add(rnd);

		}
		return q;
	}

	private Question generate(Noun noun) {

		Question finalQ = new Question();
		int rnd = (int) Math.floor(Math.random() * 3);
		switch (rnd) {
		case 0:
			finalQ.setQuestion("What is the Welsh noun for " + noun.getEnNoun() + "?");
			finalQ.setNoun(noun.getCyNoun());
			break;
		case 1:
			finalQ.setQuestion("What is the English noun for " + noun.getCyNoun() + "?");
			finalQ.setNoun(noun.getEnNoun());
			break;
		case 2:
			finalQ.setQuestion("What is the gender of the welsh noun " + noun.getCyNoun() + "?");
			finalQ.setNoun(noun.getCyGender());
			break;
		default:
			break;
		}

		return finalQ;
	}
}
