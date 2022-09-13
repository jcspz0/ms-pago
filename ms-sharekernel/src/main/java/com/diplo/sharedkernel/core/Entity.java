package com.diplo.sharedkernel.core;

import com.diplo.sharedkernel.core.IBussinesRule.IBussinessRule;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Entity<TId> {

	protected TId Id;
	private Collection<DomainEvent> _domainEvents;
	private Collection<IntegrationEvent> _integrationEvents;

	protected Entity() {
		_domainEvents = new ArrayList<DomainEvent>();
		_integrationEvents = new ArrayList<IntegrationEvent>();
	}

	public Collection<DomainEvent> getDomainEvents() {
		return _domainEvents;
	}

	public void AddDomainEvent(DomainEvent evento) {
		_domainEvents.add(evento);
	}

	public void ClearDomainEvents() {
		_domainEvents.clear();
	}

	public Collection<IntegrationEvent> getIntegrationEvents() {
		return _integrationEvents;
	}

	public void AddIntegrationEvent(IntegrationEvent evento) {
		_integrationEvents.add(evento);
	}

	public void ClearIntegrationEvents() {
		_integrationEvents.clear();
	}

	public TId getId() {
		return Id;
	}

	protected void setId(TId id) {
		Id = id;
	}

	protected void CheckRule(IBussinessRule rule)
		throws BussinessRuleValidationException {
		if (rule == null) {
			throw new IllegalArgumentException("Rule cannot be null");
		}
		if (!rule.IsValid()) {
			throw new BussinessRuleValidationException(rule);
		}
	}
}
