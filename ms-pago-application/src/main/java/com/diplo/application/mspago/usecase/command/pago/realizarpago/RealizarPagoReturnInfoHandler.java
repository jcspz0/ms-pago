package com.diplo.application.mspago.usecase.command.pago.realizarpago;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
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

public class RealizarPagoReturnInfoHandler
	implements IRequestHandler<RealizarPagoReturnInfoCommand, PagoDTO> {

	private final IPagoService _pagoService;
	private final IPagoFactory _pagoFactory;
	private final IDeudaRepository _deudaRepository;
	private final IUnitOfWork _unitOfWork;

	public RealizarPagoReturnInfoHandler(
		IPagoService _pagoService,
		IDeudaFactory _deudaFactory,
		IPagoFactory _pagoFactory,
		IDeudaRepository _deudaRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._pagoService = _pagoService;
		this._pagoFactory = _pagoFactory;
		this._deudaRepository = _deudaRepository;
		this._unitOfWork = _unitOfWork;
	}

	@Override
	public Future<PagoDTO> Handle(RealizarPagoReturnInfoCommand request)
		throws Exception {
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
		String nroPago = "";

		CompletableFuture<String> futuro = (CompletableFuture<String>) _pagoService.GenerarNroPagoAsync();
		try {
			nroPago = futuro.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			System.out.println("Error al obtener el nroPago " + e);
		}
		Pago pago = _pagoFactory.Create(
			nroPago,
			request.getTotalPagado(),
			request.getDetalle(),
			deuda.getId().toString()
		);
		boolean pagado = deuda.RealizarPago(pago);
		System.out.println("Se realizó el pago? " + pagado);
		if (pagado) {
			_deudaRepository.UpdateAsync(deuda);
			_unitOfWork.Commit();
			PagoDTO pagoDTO = new PagoDTO(pago);
			return CompletableFuture.completedFuture(pagoDTO);
		}

		return CompletableFuture.completedFuture(null);
	}
}
