package com.hokumus.jsf.model;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member extends BaseEntity {

	private Long id;
	private String username;
	private String pasword;
	private String name;
	private String email;
	private String phoneNumber;
	private MemberType type;

	@Id
	@SequenceGenerator(name = "sq_member", sequenceName = "sq_member")
	@GeneratedValue(generator = "sq_member", strategy = GenerationType.SEQUENCE)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Enumerated
	@JoinColumn(name = "membertypeid")
	public MemberType getType() {
		return type;
	}

	public void setType(MemberType type) {
		this.type = type;
	}

}
