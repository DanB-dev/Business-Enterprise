package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;






@Embeddable
public class Question {

	// Set up variables
	private String question;
	private String answer;
	
	private String q_id;

	public String getQuestion() {
		return question;
	}
	public String getQ_id() {
		return q_id;
	}
	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}


	public void setQuestion(String question) {
		this.question =  question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
