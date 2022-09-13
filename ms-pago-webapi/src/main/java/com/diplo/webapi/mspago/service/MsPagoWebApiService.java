package com.diplo.webapi.mspago.service;

import com.diplo.application.mspago.service.MsPagoApplicationService;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaHandler;
import com.diplo.application.mspago.usecase.command.deuda.vencerdeuda.VencerDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.vencerdeuda.VencerDeudaHandler;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoCommand;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoHandler;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoReturnInfoCommand;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoReturnInfoHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdQuery;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId.GetDeudaByReservaIdHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId.GetDeudaByReservaIdQuery;
import com.diplo.application.mspago.usecase.query.deuda.getFacturaById.GetFacturaByIdDeudaHandler;
import com.diplo.application.mspago.usecase.query.deuda.getFacturaById.GetFacturaByIdDeudaQuery;
import com.diplo.infraestructure.mspago.service.MsPagoInfraestructureService;
import com.diplo.sharedkernel.mediator.IMediator;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsPagoWebApiService {

	@Autowired
	MsPagoInfraestructureService _serviceInfra;

	IMediator _mediator;

	@PostConstruct
	private void postConstruct() {
		AddWebApi();
	}

	private void AddWebApi() {
		//_serviceInfra = new MsReservaInfraestructureService();
		_serviceInfra.AddInfraestructure(new MsPagoApplicationService());
		_mediator = _serviceInfra.getService().getMediator();
		registrarHandlers();
	}

	private void registrarHandlers() {
		//registro de creación de deuda
		CrearDeudaCommand _crearDeudaCommand = new CrearDeudaCommand();
		CrearDeudaHandler _CrearDeudaHandler = new CrearDeudaHandler(
			_serviceInfra.getService().getDeudaService(),
			_serviceInfra.getService().getDeudaFactory(),
			_serviceInfra.getDeudaRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(_crearDeudaCommand, _CrearDeudaHandler);

		//registro de consultar Deuda
		GetDeudaByIdQuery _getDeudaByIdQuery = new GetDeudaByIdQuery();
		GetDeudaByIdHandler _getDeudaByIdHandler = new GetDeudaByIdHandler(
			_serviceInfra.getDeudaRepository()
		);
		_mediator.registrarComando(_getDeudaByIdQuery, _getDeudaByIdHandler);

		//registro de consultar Deuda por ReservaId
		GetDeudaByReservaIdQuery _getDeudaByReservaIdQuery = new GetDeudaByReservaIdQuery();
		GetDeudaByReservaIdHandler _getDeudaByReservaIdHandler = new GetDeudaByReservaIdHandler(
			_serviceInfra.getDeudaRepository()
		);
		_mediator.registrarComando(
			_getDeudaByReservaIdQuery,
			_getDeudaByReservaIdHandler
		);

		//Registro de realizar pago retorno ID
		RealizarPagoCommand _realizarPagoCommand = new RealizarPagoCommand();
		RealizarPagoHandler _realizarPagoHandler = new RealizarPagoHandler(
			_serviceInfra.getService().getPagoService(),
			_serviceInfra.getService().getDeudaFactory(),
			_serviceInfra.getService().getPagoFactory(),
			_serviceInfra.getDeudaRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(_realizarPagoCommand, _realizarPagoHandler);

		//Registro de realizar pago retorno pagoDto
		RealizarPagoReturnInfoCommand _realizarPagoReturnInfoCommand = new RealizarPagoReturnInfoCommand();
		RealizarPagoReturnInfoHandler _realizarPagoReturnInfoHandler = new RealizarPagoReturnInfoHandler(
			_serviceInfra.getService().getPagoService(),
			_serviceInfra.getService().getDeudaFactory(),
			_serviceInfra.getService().getPagoFactory(),
			_serviceInfra.getDeudaRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(
			_realizarPagoReturnInfoCommand,
			_realizarPagoReturnInfoHandler
		);

		//Registro de vencer Deuda/reserva
		VencerDeudaCommand _vencerReservaCommand = new VencerDeudaCommand();
		VencerDeudaHandler _vencerReservaHandler = new VencerDeudaHandler(
			_serviceInfra.getDeudaRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(_vencerReservaCommand, _vencerReservaHandler);

		//Registro de generarFactura
		GetFacturaByIdDeudaQuery _getFacturaByIdDeudaQuery = new GetFacturaByIdDeudaQuery();
		GetFacturaByIdDeudaHandler _getFacturaByIdDeudaHandler = new GetFacturaByIdDeudaHandler(
			_serviceInfra.getDeudaRepository()
		);
		_mediator.registrarComando(
			_getFacturaByIdDeudaQuery,
			_getFacturaByIdDeudaHandler
		);
	}

	public IMediator getMediator() {
		return _mediator;
	}
}
