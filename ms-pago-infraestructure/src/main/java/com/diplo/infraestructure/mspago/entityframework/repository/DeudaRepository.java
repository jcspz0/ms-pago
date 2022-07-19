package com.diplo.infraestructure.mspago.entityframework.repository;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.stereotype.Repository;

@Repository
public class DeudaRepository implements IDeudaRepository {

	@Override
	public Future<Deuda> FindByIdAsync(UUID id) {
		// TODO Auto-generated method stub
		Deuda deuda = new Deuda(id, UUID.randomUUID(), new Monto(10));

		System.out.println("DeudaRepository->FindByIdAsync");

		return CompletableFuture.completedFuture(deuda);
	}

	@Override
	public Future<Deuda> CreateAsync(Deuda obj) {
		Deuda deuda = new Deuda(obj.getId(), UUID.randomUUID(), new Monto(10));

		System.out.println("DeudaRepository->CreateAsync");

		return CompletableFuture.completedFuture(deuda);
	}

	@Override
	public Future<Deuda> UpdateAsync(Deuda obj) {
		Deuda deuda = new Deuda(obj);

		System.out.println("DeudaRepository->UpdateAsync");

		return CompletableFuture.completedFuture(deuda);
	}

	@Override
	public Future<Deuda> FindByReservaIdAsync(String reservaId) {
		Deuda deuda = new Deuda(
			UUID.randomUUID(),
			UUID.randomUUID(),
			new Monto(10)
		);

		System.out.println("DeudaRepository->FindByIdAsync");

		return CompletableFuture.completedFuture(deuda);
	}
}
