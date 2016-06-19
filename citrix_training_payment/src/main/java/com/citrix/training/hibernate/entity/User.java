package com.citrix.training.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Handles requests for the application home page.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4493072961315191755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="First name cannot be blank")
	@Column(name = "first_name")
	@Length(min=3,max=50,message="First name should be have min 3 and max 50 character")
	private String firstName; 
	
	@Column(name = "last_name")
	@Length(min=0,max=50,message="Last name should be have min 3 and max 50 character")
	private String lastName;
	
	@NotEmpty(message="Username cannot be blank")
	@Column(name = "user_name")
	@Pattern(regexp="^[a-z0-9_-]{5,25}$", message="Username should be have min 5 and max 25 character's. Username Should only contain lowercase alphabets, number, hyphen and underscore.")
	private String userName;
	
	@NotEmpty(message="email cannot be blank")
	@Email(message="Not a valid email")
	@Column(name = "email")
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
}
