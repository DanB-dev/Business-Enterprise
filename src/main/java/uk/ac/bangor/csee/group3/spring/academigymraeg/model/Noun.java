package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

//Test Comment

@Entity
public class Noun {
	@Column(nullable = false)
	private String cyGender, enNoun;

	@NotNull(message = "You must enter a noun")
	@Column(nullable = false)
	private String cyNoun; // Welsh Noun

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	public String getCyGender() {
		return cyGender;
	}

	public String getCyNoun() {
		return cyNoun;
	}

	public String getEnNoun() {
		return enNoun;
	}

	public String getId() {
		return id;
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

	public void setId(String id) {
		this.id = id;
	}

}
