package com.Lib.Back.Student.Model;

import java.time.LocalDateTime;

import org.hibernate.annotations.NaturalId;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", initialValue = 1900, allocationSize = 1)
	private Long id;
	private String firstName;
	private String lastName;
	private String fatherName;
	private String eMail;
	@NaturalId(mutable = true)
	private String addharNumber;
	private String moNumber;
	private String parentMoNumber;
	private String CurrentAddress;
	private String ParmanentAddress;
	private String gender;
	private String DoBirth;
	@DateTimeFormat(pattern = "MM/dd/yyyy'T'HH:mm a")
	private LocalDateTime datetime;

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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getAddharNumber() {
		return addharNumber;
	}

	public void setAddharNumber(String addharNumber) {
		this.addharNumber = addharNumber;
	}

	public String getMoNumber() {
		return moNumber;
	}

	public void setMoNumber(String moNumber) {
		this.moNumber = moNumber;
	}

	public String getParentMoNumber() {
		return parentMoNumber;
	}

	public void setParentMoNumber(String parentMoNumber) {
		this.parentMoNumber = parentMoNumber;
	}

	public String getCurrentAddress() {
		return CurrentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		CurrentAddress = currentAddress;
	}

	public String getParmanentAddress() {
		return ParmanentAddress;
	}

	public void setParmanentAddress(String parmanentAddress) {
		ParmanentAddress = parmanentAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDoBirth() {
		return DoBirth;
	}

	public void setDoBirth(String doBirth) {
		DoBirth = doBirth;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

}
