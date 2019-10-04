package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
class Aggregate extends AbstractAggregateRoot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String comment;

	public Aggregate() {
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	long getId() {
		return id;
	}

	@PrePersist
	public void prePersist() {
		registerEvent(new DomainCreateEvent());
	}

	@PreUpdate
	public void preUpdate() {
		registerEvent(new DomainUpdateEvent());
	}

}