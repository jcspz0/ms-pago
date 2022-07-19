package com.diplo.infraestructure.mspago;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.mediator.IMediator;
import com.diplo.application.mspago.mediator.Mediator;
import com.diplo.application.mspago.service.MsPagoApplicationService;
import com.diplo.application.mspago.service.deuda.DeudaService;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.application.mspago.service.pago.PagoService;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaHandler;
import com.diplo.application.mspago.usecase.command.deuda.vencerreserva.VencerReservaCommand;
import com.diplo.application.mspago.usecase.command.deuda.vencerreserva.VencerReservaHandler;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoCommand;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdHandler;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdQuery;
import com.diplo.infraestructure.mspago.entityframework.UnitOfWork;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.DbDeudaRepository;
import com.diplo.infraestructure.mspago.entityframework.repository.DeudaRepository;
import com.diplo.infraestructure.mspago.memoryrepository.MemoryDatabase;
import com.diplo.infraestructure.mspago.memoryrepository.MemoryDeudaRepository;
import com.diplo.infraestructure.mspago.service.MsPagoInfraestructureService;
import com.diplo.mspago.factory.deuda.DeudaFactory;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.factory.pago.IPagoFactory;
import com.diplo.mspago.factory.pago.PagoFactory;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.diplo" })
public class MsPagoInfraestructureApplication implements CommandLineRunner {

	@Autowired
	DbDeudaRepository deudaEntityRepository;

	@Autowired
	MsPagoInfraestructureService _serviceInfra;

	@Autowired
	UnitOfWork _unitOfWork;

	public static void main(String[] args) {
		SpringApplication.run(MsPagoInfraestructureApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//MsReservaInfraestructureService _serviceInfra = new MsReservaInfraestructureService(new MsReservaApplicationService(),reservaEntityRepository, new UnitOfWork());
		/*	
		_serviceInfra.setDeudaRepository(deudaEntityRepository);
		_serviceInfra.set_unitOfWork(_unitOfWork);
		_serviceInfra.AddInfraestructure(new MsPagoApplicationService());
		
		
		IMediator _mediator = _serviceInfra.getService().getMediator();
		
		IDeudaService _deudaService = _serviceInfra.getService().getDeudaService();
		IDeudaFactory _deudaFactory = _serviceInfra.getService().getDeudaFactory();
		IUnitOfWork _unitOfWork = _serviceInfra.get_unitOfWork();
		
		IPagoFactory _pagoFactory = _serviceInfra.getService().getPagoFactory();
		IPagoService _pagoService = _serviceInfra.getService().getPagoService();
		
		IDeudaRepository _deudaRepository = _serviceInfra.getDeudaRepository();
		//-----
		//List<Deuda> _listaDeuda= new ArrayList<>(); 
		//IDeudaRepository _deudaRepository = new MemoryDeudaRepository(new MemoryDatabase(_listaDeuda));
		//----
		///Creación de deuda
		CrearDeudaCommand _crearDeudaCommand = new CrearDeudaCommand();
		CrearDeudaHandler _CrearDeudaHandler = new CrearDeudaHandler(_deudaService, _deudaFactory,_deudaRepository ,_unitOfWork);
		_mediator.registrarComando(_crearDeudaCommand, _CrearDeudaHandler);
		UUID deudaIdCreada = _mediator.Send(new CrearDeudaCommand(UUID.randomUUID().toString(), 100.0));
		//UUID deudaIdCreada2 = _mediator.Send(new CrearDeudaCommand(UUID.randomUUID().toString(), 200.0));
		//UUID deudaIdCreada3 = _mediator.Send(new CrearDeudaCommand(UUID.randomUUID().toString(), 300.0));
		System.out.println("main->CrearDeuda, " + deudaIdCreada.toString());
		//---------
		//Busqueda de deuda
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
		System.out.println("main->Realizar un pago");
		RealizarPagoCommand _realizarPagoCommand = new RealizarPagoCommand(deudaEncontrada.getDeudaId().toString(),20,"pago de 20");
		RealizarPagoHandler _realizarPagoHandler = new RealizarPagoHandler(_pagoService, _deudaFactory, _pagoFactory,_deudaRepository ,_unitOfWork);
		_mediator.registrarComando(_realizarPagoCommand, _realizarPagoHandler);
		_mediator.Send(_realizarPagoCommand);
		
		//Busqueda de deuda
		System.out.println("main->buscar la deuda nuevamnete");
		//GetDeudaByIdQuery _getDeudaByIdQuery2 = new GetDeudaByIdQuery(deudaIdCreada);
		GetDeudaByIdQuery _getDeudaByIdQuery2 = new GetDeudaByIdQuery(UUID.fromString(deudaEncontrada.getDeudaId()));
		_mediator.registrarComando(new GetDeudaByIdQuery(), _getDeudaByIdHandler);
		DeudaDTO deudaEncontradaConPago = _mediator.Send(_getDeudaByIdQuery2);
		System.out.println("main->GetDeuda->id, "+deudaEncontradaConPago.getDeudaId());
		System.out.println("main->GetDeuda->reservaId, "+deudaEncontradaConPago.getReservaId());
		System.out.println("main->GetDeuda->estado, "+deudaEncontradaConPago.getEstado());
		System.out.println("main->GetDeuda->total, "+deudaEncontradaConPago.getTotal());
		if(deudaEncontradaConPago.getListaPagos().size()==0) {
			System.out.println("main->GetDeuda->no tiene pagos");
		}else {
			System.out.println("main->GetDeuda->tiene " + deudaEncontradaConPago.getListaPagos().size()+ " pagos");
		}
		
		//Vencer deuda
		VencerReservaCommand _vencerReservaCommand = new VencerReservaCommand(deudaIdCreada.toString());
		VencerReservaHandler _vencerReservaHandler = new VencerReservaHandler(_deudaRepository, _unitOfWork);
		_mediator.registrarComando(_vencerReservaCommand, _vencerReservaHandler);
		_mediator.Send(_vencerReservaCommand);

		
		//Busqueda de deuda
				GetDeudaByIdQuery _getDeudaByIdQuery3 = new GetDeudaByIdQuery(deudaIdCreada);
				_mediator.registrarComando(new GetDeudaByIdQuery(), _getDeudaByIdHandler);
				DeudaDTO deudaEncontradaVencida = _mediator.Send(_getDeudaByIdQuery3);
				System.out.println("main->GetDeuda->id, "+deudaEncontradaVencida.getDeudaId());
				System.out.println("main->GetDeuda->reservaId, "+deudaEncontradaVencida.getReservaId());
				System.out.println("main->GetDeuda->estado, "+deudaEncontradaVencida.getEstado());
				System.out.println("main->GetDeuda->total, "+deudaEncontradaVencida.getTotal());
				if(deudaEncontradaVencida.getListaPagos().size()==0) {
					System.out.println("main->GetDeuda->no tiene pagos");
				}else {
					System.out.println("main->GetDeuda->tiene " + deudaEncontradaVencida.getListaPagos().size()+ " pagos");
				}
		
		
	*/
	}
}
