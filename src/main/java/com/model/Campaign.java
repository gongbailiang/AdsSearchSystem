package com.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "campaign")
public class Campaign {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String name;
	  // The user's email
	@NotNull
	private String budget;
	
	private Set<Ad> Ads;
	
	public Campaign() {
		super();
	}
	public Campaign(String name, String budget) {
		super();
		this.name = name;
		this.budget = budget;
	}
	public Campaign(long id) {
		super();
		this.id = id;
	}
	
	
	public void setAds(Set<Ad> ads) {
		Ads = ads;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	
	@OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
	public Set<Ad> getAds() {
		return Ads;
	}
	  
	  // The user's name
}