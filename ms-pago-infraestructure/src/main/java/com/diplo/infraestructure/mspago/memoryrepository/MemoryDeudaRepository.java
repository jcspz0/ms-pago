package com.diplo.infraestructure.mspago.memoryrepository;

import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

public class MemoryDeudaRepository implements IDeudaRepository {

	private final MemoryDatabase _database;

	public MemoryDeudaRepository(MemoryDatabase _database) {
		super();
		this._database = _database;
	}

	@Override
	public Future<Deuda> FindByIdAsync(UUID id) {
		Deuda result = null;
		for (Deuda _deuda : _database.get_deudas()) {
			if (_deuda.getId().toString().equals(id.toString())) {
				result = _deuda;
				System.out.println(
					"pagos del encontrado->" + result.getListaPagos().size()
				);
			}
		}
		return CompletableFuture.completedFuture(result);
	}

	@Override
	public Future<Deuda> CreateAsync(Deuda obj) {
		_database.get_deudas().add(obj);
		System.out.println(
			"MemoryDeudaRepository->" + _database.get_deudas().size()
		);
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Deuda> UpdateAsync(Deuda obj) {
		for (int i = 0; i < _database.get_deudas().size(); i++) {
			if (
				_database
					.get_deudas()
					.get(i)
					.getId()
					.toString()
					.compareTo(obj.getId().toString()) ==
				0
			) {
				_database.get_deudas().set(i, obj);
				break;
			}
		}

		System.out.println("DeudaRepository->UpdateAsync");

		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Deuda> FindByReservaIdAsync(String reservaId) {
		Deuda result = null;
		for (Deuda _deuda : _database.get_deudas()) {
			if (_deuda.getReservaId().toString().equals(reservaId)) {
				result = _deuda;
				System.out.println(
					"pagos del encontrado->" + result.getListaPagos().size()
				);
			}
		}
		return CompletableFuture.completedFuture(result);
	}
}
