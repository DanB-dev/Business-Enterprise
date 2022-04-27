package uk.ac.bangor.csee.group3.spring.academigymraeg.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails {

//hello
	private static final long serialVersionUID = 6353454865807995070L;

	@Column(nullable = false)
	private boolean admin = false, user = true, power = false;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	@NotNull(message = "Password is required")
	private String password;

	@NotNull(message = "Username is required")
	private String username;

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (isAdmin())
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		if (isPower())
			authorities.add(new SimpleGrantedAuthority("ROLE_POWER"));

		if (isUser())
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return authorities;
	}

	public boolean getEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isPower() {
		return power;
	}

	public boolean isUser() {
		return user;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPower(boolean power) {
		this.power = power;
	}

	public void setUser(boolean user) {
		this.user = user;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
