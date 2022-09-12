package com.diplo.application.mspago;

import com.diplo.application.mspago.service.MsPagoApplicationService;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsPagoApplication implements CommandLineRunner {

	@Autowired
	MsPagoApplicationService serviceApp;

	public static void main(String[] args) {
		SpringApplication.run(MsPagoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		serviceApp.AddApplication();
		
		IMediator _mediator = serviceApp.getMediator();
		
		IDeudaService _serverDeudaService = serviceApp.getDeudaService();
		IDeudaFactory _deudaFactory = serviceApp.getDeudaFactory();
		IPagoService _pagoService = serviceApp.getPagoService();
		IUnitOfWork _unitOfWork = new UnitOfWorkTest();
		IDeudaRepository _deudaRepository = new DeudaRepositoryTest();
		IPagoFactory _pagoFactory = serviceApp.getPagoFactory();
		
		///Creación de deuda
		CrearDeudaCommand _crearReservaCommand = new CrearDeudaCommand(UUID.randomUUID().toString(), 100);
		CrearDeudaHandler _CrearReservaHandler = new CrearDeudaHandler(_serverDeudaService, _deudaFactory,_deudaRepository ,_unitOfWork);
		_mediator.registrarComando(_crearReservaCommand, _CrearReservaHandler);
		UUID deudaIdCreada = _mediator.Send(_crearReservaCommand);
		System.out.println("main->CrearDeuda, " + deudaIdCreada.toString());
		
		//Busqueda de reserva
		GetDeudaByIdQuery _getDeudaByIdQuery = new GetDeudaByIdQuery(deudaIdCreada);
		GetDeudaByIdHandler _getDeudaByIdHandler = new GetDeudaByIdHandler(_deudaRepository);
		_mediator.registrarComando(new GetDeudaByIdQuery(), _getDeudaByIdHandler);
		DeudaDTO deudaEncontrada = _mediator.Send(_getDeudaByIdQuery);
		System.out.println("main->GetDeuda->id, "+deudaEncontrada.getDeudaId());
		System.out.println("main->GetDeuda->reservaId, "+deudaEncontrada.getReservaId());
		System.out.println("main->GetDeuda->estado, "+deudaEncontrada.getEstado());
		System.out.println("main->GetDeuda->total, "+deudaEncontrada.getTotal());
		if(deudaEncontrada.getListaPagos().size()==0) {
			System.out.println("main->GetDeuda->no tiene pagos");
		}else {
			System.out.println("main->GetDeuda->tiene " + deudaEncontrada.getListaPagos().size()+ " pagos");
		}
		
		
		//Realizacion de un pago
		RealizarPagoCommand _realizarPagoCommand = new RealizarPagoCommand(deudaIdCreada.toString(),20,"pago de 20");
		RealizarPagoHandler _realizarPagoHandler = new RealizarPagoHandler(_pagoService, _deudaFactory, _pagoFactory,_deudaRepository ,_unitOfWork);
		_mediator.registrarComando(_realizarPagoCommand, _realizarPagoHandler);
		UUID deudaIdconPago = _mediator.Send(_realizarPagoCommand);
		
		//Vencer deuda
		//Realizacion de un pago
		VencerReservaCommand _vencerReservaCommand = new VencerReservaCommand(deudaIdCreada.toString());
		VencerReservaHandler _vencerReservaHandler = new VencerReservaHandler(_deudaRepository, _unitOfWork);
		_mediator.registrarComando(_vencerReservaCommand, _vencerReservaHandler);
		UUID deudaIdVencida = _mediator.Send(_vencerReservaCommand);
		*/
	}
}
