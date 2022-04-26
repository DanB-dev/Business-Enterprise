package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
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

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;


	@Embedded
	@ElementCollection
	private List<Question> questions;

	@Column
	private int result = 0;
	
	@Column(nullable = true)
	private Instant startedDate;

	@Column()
	private String status = "notStarted";

	@Column
	private String user;
	
	public Instant getCreatedDate() {
		return createdDate;
	}

	public String getId() {
		return id;
	}


	public List<Question> getQuestions() {
		return questions;
	}

	public int getResult() {
		return result;
	}

	public Instant getStartedDate() {
		return startedDate;
	}

	public String getStatus() {
		return status;
	}

	public String getUser() {
		return user;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public void setId(String id) {
		this.id = id;
	}


	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public void setStartedDate(Instant startedDate) {
		this.startedDate = startedDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
