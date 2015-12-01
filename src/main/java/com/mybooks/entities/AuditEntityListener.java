package com.mybooks.entities;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditEntityListener {
	
	@PrePersist
	public void prePersist(AuditableEntity e)
	{
		e.setCreatedDate(new Date());
	}
	
	@PreUpdate
	public void preUpdate(AuditableEntity e)
	{
		e.setLastModifiedDate(new Date());
	}

}
