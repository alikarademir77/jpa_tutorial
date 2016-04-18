package com.bby.oms.jpa.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "T_PERSON")
public class Person {
	private Long id;
	private String firstName;
	private String lastName;
	private BigDecimal salary;
	private List<Phone> phones = new ArrayList();

	
	private IdCard idCard;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_CARD_ID")
	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}
	
	@Column(name = "FIRST_NAME", length = 100, nullable = false, unique = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "SALARY")
	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
	public List<Phone> getPhones() {
		return phones;
	}

	public  void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

}