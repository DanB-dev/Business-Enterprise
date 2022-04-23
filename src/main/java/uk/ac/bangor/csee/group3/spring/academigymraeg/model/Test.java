package uk.ac.bangor.csee.group3.spring.academigymraeg.model;


import java.time.Instant;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity

public class Test {

	@CreatedDate
	private Instant createdDate;

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
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
