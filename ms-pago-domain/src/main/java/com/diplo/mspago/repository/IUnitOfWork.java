package com.diplo.mspago.repository;

import java.util.concurrent.Future;

public interface IUnitOfWork {
	Future<Void> Commit();
}
