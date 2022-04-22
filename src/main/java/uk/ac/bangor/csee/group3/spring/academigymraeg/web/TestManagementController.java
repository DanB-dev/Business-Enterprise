package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uk.ac.bangor.csee.group3.spring.academigymraeg.RepositoryBasedNounImpl;
import uk.ac.bangor.csee.group3.spring.academigymraeg.RepositoryBasedTestImpl;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Question;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Test;
import uk.ac.bangor.csee.group3.spring.academigymraeg.repository.TestRepository;

@Controller
public class TestManagementController {
	
	@Autowired
	private RepositoryBasedNounImpl nounDetails;

	@Autowired
	private TestRepository repository;

	@Autowired
	private RepositoryBasedTestImpl testDetails;
	
	@GetMapping("/createTest")
	public String createTest(Model model) {
		List<Question> generatedTest = generateQuestions(2);
		
		model.addAttribute("generatedTest",generatedTest);
		
		return "createTest";
	}
	
	@PostMapping("/createTest")
	public String saveTest(@ModelAttribute List<Question> questions, Model model) {
		Test toSave = new Test();
		toSave.setQuestions(questions);
		toSave.setId("3");
		repository.save(toSave);
		return "redirect:/welcome";
	}
	
	
	
	
	private List<Question> generateQuestions(int number) {
		List<Noun> nouns = nounDetails.loadAllNouns();
		
		if(nouns.size() < number) {
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
