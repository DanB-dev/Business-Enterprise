package uk.ac.bangor.csee.group3.spring.academigymraeg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Question;

@Component
public class AnswersCreationDto {
	
	private List<Question> answers = new ArrayList<Question>();
	
	private String id;
	
	public void addAnswer(Question question) {
		this.answers.add(question);
	}

	public List<Question> getAnswers() {
		return answers;
	}

	public String getId() {
		return id;
	}

	public void setAnswers(List<Question> questions) {
		this.answers = questions;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
