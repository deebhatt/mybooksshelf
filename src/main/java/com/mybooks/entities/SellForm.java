package com.mybooks.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SELL_YOUR_BOOK_RESPONSE")
@SequenceGenerator(name = "SEQ_SELL_BOOK")
public class SellForm extends AuditableEntity implements BaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_SELL_BOOK")
	@Column(name = "RESPONSE_ID")
	private Long id;
	
	@Column(name = "FIRST_NAME", length = 35)
	private String first_name;
	
	@Column(name = "LAST_NAME", length = 35)
	private String last_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

}
