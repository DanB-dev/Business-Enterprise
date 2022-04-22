package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import javax.persistence.Embeddable;

@Embeddable
public class Question {

	// Set up variables
	private String question;
	private String answer;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
