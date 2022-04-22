package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Test {

	@Id
	private String id;

	@Embedded
	@ElementCollection
	private List<Question> questions;

	public String getId() {
		return id;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
