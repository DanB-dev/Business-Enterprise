package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import javax.persistence.Embeddable;

@Embeddable
public class Question {

	private String answer;
	private Boolean isCorrect;
	private String noun;

	// Set up variables
	private String question;

	public String getAnswer() {
		return answer;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public String getNoun() {
		return noun;
	}

	public String getQuestion() {
		return question;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
