package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

//Test Comment

@Entity
public class Noun {
	@Column(nullable = false)
	private String cyGender,enNoun;
	@Id
	private String cyNoun; // Welsh Noun

	public String getCyGender() {
		return cyGender;
	}



	public String getCyNoun() {
		return cyNoun;
	}

	public String getEnNoun() {
		return enNoun;
	}

	public void setCyGender(String cyGender) {
		this.cyGender = cyGender;
	}



	public void setCyNoun(String cyNoun) {
		this.cyNoun = cyNoun;
	}

	public void setEnNoun(String enNoun) {
		this.enNoun = enNoun;
	}

}
