package com.diplo.application.mspago.usecase.command.deuda.creardeuda;

import com.diplo.application.mspago.mediator.request.IRequestHandler;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CrearDeudaHandler
	implements IRequestHandler<CrearDeudaCommand, UUID> {

	private final IDeudaService _deudaService;
	private final IDeudaFactory _deudaFactory;
	private final IUnitOfWork _unitOfWork;
	private final IDeudaRepository _deudaRepository;

	public CrearDeudaHandler(
		IDeudaService _deudaService,
		IDeudaFactory _deudaFactory,
		IDeudaRepository _deudaRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._deudaService = _deudaService;
		this._deudaFactory = _deudaFactory;
		this._unitOfWork = _unitOfWork;
		this._deudaRepository = _deudaRepository;
	}

	@Override
	public Future<UUID> Handle(CrearDeudaCommand request) throws Exception {
		String nroDeuda = "";

		CompletableFuture<String> futuro = (CompletableFuture<String>) _deudaService.GenerarNroDeudaAsync();
		try {
			nroDeuda = futuro.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			System.out.println("Error al obtener el nroDeuda " + e);
		}
		Deuda objDeuda = _deudaFactory.Create(
			nroDeuda,
			request.getReservaId(),
			request.getTotal()
		);
		objDeuda.crearDeuda();
		_deudaRepository.CreateAsync(objDeuda);
		_unitOfWork.Commit();

		return CompletableFuture.completedFuture(objDeuda.getId());
	}
}
