package com.citrix.training.hibernate.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "user_qualification")
public class UserQualification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817840340330629782L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;

	@NotEmpty(message="Institute name cannot be null")
	@Length(min=3,max=50,message="Institute name should be have min 3 and max 50 character")
	@Column(name = "institute_name")
	private String instituteName;
	
	@NotEmpty(message="Degree name cannot be null")
	@Length(min=3,max=50,message="Degree name should be have min 3 and max 50 character")
	@Column(name = "degree_name")
	private String degreeName;
	
	@Column(name = "admission_year")
	@NotNull(message="Admission Year cannot be null")
	@Range(min=1980,max=2014,message="Admission Year should be minimum 1980 and max 2014")
	private Long admissionYear;
	
	@NotNull(message="Passout Year cannot be null")
	@Column(name = "passout_year")
	@Range(min=1980,max=2014,message="Passwout Year should be minimum 1980 and max 2014")
	private Long passoutYear;
	
	@Column(name = "percentage")
	@NotNull(message="Percentage cannot be null")
	@Range(min=60,max=100,message="Percentage should be minimum 60 and max 100")
	private BigDecimal pecentage;
	
	@Column(name = "distance")
	private Boolean distance=false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public Long getAdmissionYear() {
		return admissionYear;
	}

	public void setAdmissionYear(Long admissionYear) {
		this.admissionYear = admissionYear;
	}

	public Long getPassoutYear() {
		return passoutYear;
	}

	public void setPassoutYear(Long passoutYear) {
		this.passoutYear = passoutYear;
	}

	public BigDecimal getPecentage() {
		return pecentage;
	}

	public void setPecentage(BigDecimal pecentage) {
		this.pecentage = pecentage;
	}

	public Boolean getDistance() {
		return distance;
	}

	public void setDistance(Boolean distance) {
		this.distance = distance;
	}

}
