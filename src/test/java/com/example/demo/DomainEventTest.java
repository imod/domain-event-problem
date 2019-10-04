package com.example.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@SpringBootTest
class DomainEventTest {

	@MockBean
	private TestEventHandler eventHandler;

	@Autowired
	private AggregateRepository repository;

	@Test
	public void testHandleEvents() throws Exception {

		Aggregate a = new Aggregate();
		Aggregate savedEntity = repository.save(a);

		// we save a new entity, so we expect a 'create' event to published only
		verify(eventHandler, times(1)).handleCreateEvent(any(DomainCreateEvent.class));
		verify(eventHandler, times(0)).handleUpdateEvent(any(DomainUpdateEvent.class));
		reset(eventHandler);

		savedEntity.setComment("some update");

		repository.save(savedEntity);

		// we updated an entity, so we expect a 'update' event to published only
		verify(eventHandler, times(0)).handleCreateEvent(any(DomainCreateEvent.class));
		verify(eventHandler, times(1)).handleUpdateEvent(any(DomainUpdateEvent.class));
	}

}
