package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import javax.persistence.Embeddable;





@Embeddable
public class Question {

	private String answer;
	private String noun;
	private String q_id;
	
	// Set up variables
	private String question;
	public String getAnswer() {
		return answer;
	}

	public String getNoun() {
		return noun;
	}

	public String getQ_id() {
		return q_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public void setNoun(String noun) {
		this.noun = noun;
	}

	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}

	public void setQuestion(String question) {
		this.question =  question;
	}

}
