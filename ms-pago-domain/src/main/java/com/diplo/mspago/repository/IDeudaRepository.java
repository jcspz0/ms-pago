package com.diplo.mspago.repository;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.sharekernel.core.IRepository;
import java.util.UUID;
import java.util.concurrent.Future;

public interface IDeudaRepository extends IRepository<Deuda, UUID> {
	Future<Deuda> UpdateAsync(Deuda obj);

	Future<Deuda> FindByReservaIdAsync(String reservaId);
}
