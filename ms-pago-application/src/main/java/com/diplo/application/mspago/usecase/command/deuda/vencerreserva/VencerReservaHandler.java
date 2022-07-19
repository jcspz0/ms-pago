package com.diplo.application.mspago.usecase.command.deuda.vencerreserva;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.mediator.request.IRequestHandler;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.factory.pago.IPagoFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.mspago.valueobjects.Monto;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class VencerReservaHandler
	implements IRequestHandler<VencerReservaCommand, UUID> {

	private final IDeudaRepository _deudaRepository;
	private final IUnitOfWork _unitOfWork;

	public VencerReservaHandler(
		IDeudaRepository _deudaRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._deudaRepository = _deudaRepository;
		this._unitOfWork = _unitOfWork;
	}

	@Override
	public Future<UUID> Handle(VencerReservaCommand request) throws Exception {
		CompletableFuture<Deuda> objDeuda = (CompletableFuture<Deuda>) _deudaRepository.FindByIdAsync(
			UUID.fromString(request.getDeudaId())
		);
		Deuda deuda = new Deuda();
		try {
			deuda = objDeuda.get();
			if (deuda == null) {
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(
				"RealizarPagoHandler-> no se encontró la deuda relacionada al pago"
			);
			return null;
		}
		System.out.println("Command VencerDeuda:" + deuda.getEstado());
		deuda.VencerDeuda();
		System.out.println("Command VencerDeuda despues:" + deuda.getEstado());
		_deudaRepository.UpdateAsync(deuda);
		_unitOfWork.Commit();

		return CompletableFuture.completedFuture(deuda.getId());
	}
}
