package com.example.demo;

import org.springframework.transaction.event.TransactionalEventListener;

interface TestEventHandler {

	@TransactionalEventListener
	void handleUpdateEvent(DomainUpdateEvent event);

	@TransactionalEventListener
	void handleCreateEvent(DomainCreateEvent event);

}