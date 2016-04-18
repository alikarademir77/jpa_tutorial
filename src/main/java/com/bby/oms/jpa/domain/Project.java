package com.bby.oms.jpa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "T_PROJECT")
public class Project {
	private Long id;
	private String title;
	private List<Geek> geeks = new ArrayList<Geek>();

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ManyToMany(mappedBy="projects")
	public List<Geek> getGeeks() {
		return geeks;
	}

	public void setGeeks(List<Geek> geeks) {
		this.geeks = geeks;
	}
}
