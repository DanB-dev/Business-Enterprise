package uk.ac.bangor.csee.group3.spring.academigymraeg.web;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.ac.bangor.csee.group3.spring.academigymraeg.AnswersCreationDto;
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
		List<Question> generatedTest = generateQuestions(3);

		model.addAttribute("generatedTest", generatedTest);
		Test toSave = new Test();
		toSave.setQuestions(generatedTest);
		toSave.setCreatedDate(Instant.now());
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		toSave.setUser(userName);
		toSave.setStatus("started");
		Test saved = repository.save(toSave);
		return "redirect:/taketest/" + saved.getId();
	}
	
	@GetMapping("/taketest/{id}")
	public String takeTest(@PathVariable("id") String id, Model model) {
		Test toShow = testDetails.loadTestById(id);
		List<Question> questions = toShow.getQuestions();

		if(toShow.getStartedDate() == null) {
			toShow.setStartedDate(Instant.now());
		}
		repository.save(toShow);
		
		
		AnswersCreationDto testForm = new AnswersCreationDto();
		for (int i=0; i < questions.size(); i++) {
			Question q = new Question();
			q.setQuestion(questions.get(i).getQuestion());
			q.setNoun(questions.get(i).getNoun());
			q.setAnswer(questions.get(i).getAnswer());
			testForm.addAnswer(q);
		}
		testForm.setId(id);
		model.addAttribute("toShow", toShow);
		model.addAttribute("questions", questions);
		model.addAttribute("form",testForm);
		return "taketest";
	}

	@RequestMapping("/tests")
	public String showTests(Model model) {
		List<Test> loadedTests = testDetails.loadAllTests();
		model.addAttribute("loadedTests", loadedTests);

		return "tests";
	}

	@GetMapping("/test/{id}")
	public String showTest(@PathVariable("id") String id, Model model) {
		Test toShow = testDetails.loadTestById(id);
		List<Question> questions = toShow.getQuestions();
		model.addAttribute("toShow", toShow);
		model.addAttribute("questions", questions);
		return "test";
	}
	
	
	// Allow users to save the test. They can come back any time and change answers.
	@RequestMapping(value="/savetest", method=RequestMethod.POST,params="action=save")
	public String saveTest(@ModelAttribute AnswersCreationDto form, Model model) {
		Test toMark = testDetails.loadTestById(form.getId());
		List<Question> questions = toMark.getQuestions();
		
		for (int i = 0; i < questions.size(); i++) {
			List<Question> answers = form.getAnswers();
			questions.get(i).setAnswer(answers.get(i).getAnswer());
		}
		repository.save(toMark);
		return "redirect:/taketest/" + form.getId();
	}
	
	//Submit Test. Users Who Submit won't be able to change answers.
	@RequestMapping(value="/savetest", method=RequestMethod.POST,params="action=submit")
	public String submitTest(@ModelAttribute AnswersCreationDto form, Model model) {
		Test toMark = testDetails.loadTestById(form.getId());
		List<Question> questions = toMark.getQuestions();
		int total = 0;
		
		for (int i = 0; i < questions.size(); i++) {
			List<Question> answers = form.getAnswers();
			questions.get(i).setAnswer(answers.get(i).getAnswer());
			answers.get(i).setQuestion(questions.get(i).getQuestion());
			answers.get(i).setNoun(questions.get(i).getNoun());
			if (answers.get(i).getAnswer().equalsIgnoreCase(questions.get(i).getNoun())) {
				answers.get(i).setIsCorrect(true);
				total++;
			}else {
				answers.get(i).setIsCorrect(false);
			}
		}
		
		//Implement Save here
		toMark.setResult(total);
		toMark.setStatus("Submitted");
		repository.save(toMark);
		
		model.addAttribute("form", form);
		model.addAttribute("total",total);
		return "result";
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
			finalQ.setQuestion("What is the Welsh noun for " + noun.getEnNoun()+"?");
			finalQ.setNoun(noun.getCyNoun());
			break;
		case 1:
			finalQ.setQuestion("What is the English noun for " + noun.getCyNoun()+"?");
			finalQ.setNoun(noun.getEnNoun());
			break;
		case 2:
			finalQ.setQuestion("What is the gender of the welsh noun " + noun.getCyNoun()+"?");
			finalQ.setNoun(noun.getCyGender());
			break;
		default:
			break;
		}

		return finalQ;
	}

}
