package com.diplo.application.mspago.usecase.query.deuda.getDeudaById;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.mediator.request.IRequestHandler;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetDeudaByIdHandler
	implements IRequestHandler<GetDeudaByIdQuery, DeudaDTO> {

	private final IDeudaRepository _deudaRepository;

	public GetDeudaByIdHandler(IDeudaRepository deudaRepository) {
		super();
		this._deudaRepository = deudaRepository;
	}

	@Override
	public Future<DeudaDTO> Handle(GetDeudaByIdQuery request) throws Exception {
		DeudaDTO deudaDto = null;
		CompletableFuture<Deuda> objDeuda = (CompletableFuture<Deuda>) _deudaRepository.FindByIdAsync(
			request.getId()
		);
		try {
			Deuda aux = objDeuda.get();
			if (aux == null) {
				return null;
			}
			deudaDto = new DeudaDTO(aux);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CompletableFuture.completedFuture(deudaDto);
	}
}
