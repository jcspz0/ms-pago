package com.diplo.application.mspago.usecase.query.deuda.getFacturaById;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.mediator.request.IRequestHandler;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Factura;
import com.diplo.mspago.repository.IDeudaRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetFacturaByIdDeudaHandler
	implements IRequestHandler<GetFacturaByIdDeudaQuery, FacturaDTO> {

	private final IDeudaRepository _deudaRepository;

	public GetFacturaByIdDeudaHandler(IDeudaRepository deudaRepository) {
		super();
		this._deudaRepository = deudaRepository;
	}

	@Override
	public Future<FacturaDTO> Handle(GetFacturaByIdDeudaQuery request) {
		try {
			FacturaDTO facturaDto = null;
			CompletableFuture<Deuda> objDeuda = (CompletableFuture<Deuda>) _deudaRepository.FindByIdAsync(
				request.getId()
			);
			Deuda aux = objDeuda.get();
			if (aux == null) {
				return null;
			}
			Factura factura = aux.GenerarFactura(
				request.getDetalle(),
				request.getNit()
			);
			if (factura == null) {
				return CompletableFuture.completedFuture(null);
			}
			facturaDto = new FacturaDTO(factura);
			return CompletableFuture.completedFuture(facturaDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CompletableFuture.completedFuture(null);
		}
	}
}
