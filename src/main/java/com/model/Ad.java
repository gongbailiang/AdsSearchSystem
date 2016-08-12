package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "ad")
public class Ad {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	  
	  // The user's email
	@NotNull
	private String keyWords;
	  
	  // The user's name
	@NotNull
	private float pClick;
	  
	@NotNull
	private float bid;

	@NotNull
	private Campaign campaign;
  
	public Ad() {
		super();
	}
	
	public Ad(long id) {
		super();
		this.id = id;
	}
	
	public Ad(String keyWords, float pClick, float bid) {
		super();
		this.keyWords = keyWords;
		this.pClick = pClick;
		this.bid = bid;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getKeyWords() {
		return keyWords;
	}
	
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	public float getpClick() {
		return pClick;
	}
	
	public void setpClick(float pClick) {
		this.pClick = pClick;
	}
	
	public float getBid() {
		return bid;
	}
	
	public void setBid(float bid) {
		this.bid = bid;
	}

	@ManyToOne
    @JoinColumn(name = "id")
	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	  
	  

//   ------------------------
//   PUBLIC METHODS
//   ------------------------
  
  
  
} 
