package com.diplo.sharedkernel.core;

import java.util.concurrent.Future;

public interface IRepository<T extends AggregateRoot<TId>, TId> {
	Future<T> FindByIdAsync(TId id) throws Exception;

	Future<T> CreateAsync(T obj) throws Exception;

	void commit();
}
