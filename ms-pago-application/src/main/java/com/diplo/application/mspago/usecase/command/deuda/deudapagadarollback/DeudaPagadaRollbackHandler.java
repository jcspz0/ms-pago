package com.diplo.application.mspago.usecase.command.deuda.deudapagadarollback;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.factory.pago.IPagoFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class DeudaPagadaRollbackHandler
	implements IRequestHandler<DeudaPagadaRollbackCommand, UUID> {

	private final IDeudaRepository _deudaRepository;
	private final IUnitOfWork _unitOfWork;

	public DeudaPagadaRollbackHandler(
		IDeudaRepository _deudaRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._deudaRepository = _deudaRepository;
		this._unitOfWork = _unitOfWork;
	}

	@Override
	public Future<UUID> Handle(DeudaPagadaRollbackCommand request)
		throws Exception {
		CompletableFuture<Deuda> objDeuda = (CompletableFuture<Deuda>) _deudaRepository.FindByReservaIdAsync(
			request.getReservaId()
		);
		Deuda deuda = new Deuda();
		try {
			deuda = objDeuda.get();
			if (deuda == null) {
				return null;
			}
		} catch (Exception e) {
			System.out.println(
				"DeudaPagadaRollbackHandler-> no se encontro la deuda de la reserva"
			);
			return null;
		}
		deuda.anularPago(request.getPagoId());
		_deudaRepository.UpdateAsync(deuda);
		_unitOfWork.Commit();

		return CompletableFuture.completedFuture(deuda.getId());
	}
}
