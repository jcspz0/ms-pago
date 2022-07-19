package com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.mediator.request.IRequestHandler;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetDeudaByReservaIdHandler
	implements IRequestHandler<GetDeudaByReservaIdQuery, DeudaDTO> {

	private final IDeudaRepository _deudaRepository;

	public GetDeudaByReservaIdHandler(IDeudaRepository deudaRepository) {
		super();
		this._deudaRepository = deudaRepository;
	}

	@Override
	public Future<DeudaDTO> Handle(GetDeudaByReservaIdQuery request)
		throws Exception {
		DeudaDTO deudaDto = null;
		CompletableFuture<Deuda> objDeuda = (CompletableFuture<Deuda>) _deudaRepository.FindByReservaIdAsync(
			request.getId()
		);
		Deuda aux = objDeuda.get();
		if (aux == null) {
			throw new Exception("No se encontro deuda");
		}
		deudaDto = new DeudaDTO(aux);

		return CompletableFuture.completedFuture(deudaDto);
	}
}
