package com.diplo.infraestructure.mspago.service;

import com.diplo.application.mspago.service.IMediatorApplicationService;
import com.diplo.application.mspago.service.MsPagoApplicationService;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.repository.IUnitOfWork;
import com.diplo.sharedkernel.core.IApplicationService;
import com.diplo.sharedkernel.event.IInfraestructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsPagoInfraestructureService implements IInfraestructureService {

	@Autowired
	MsPagoApplicationService service;

	@Autowired
	IDeudaRepository _deudaRepository;

	@Autowired
	IUnitOfWork _unitOfWork;

	//-----

	@Override
	public void AddInfraestructure(IApplicationService appService) {
		service = (MsPagoApplicationService) appService;
		service.AddApplication();
		//_reservaRepository = new MemoryReservaRepository(new MemoryDatabase(new ArrayList<Reserva>()));
		//this._reservaRepository = _reservaRepository;
		//_unitOfWork = new UnitOfWork();
	}

	public MsPagoInfraestructureService(
		IMediatorApplicationService service,
		IDeudaRepository _reservaRepository,
		IUnitOfWork _unitOfWork
	) {
		this.service = (MsPagoApplicationService) service;
		this._deudaRepository = _reservaRepository;
		this._unitOfWork = _unitOfWork;
		this.service.AddApplication();
	}

	public MsPagoInfraestructureService() {
		super();
	}

	public MsPagoApplicationService getService() {
		return service;
	}

	public void setService(MsPagoApplicationService service) {
		this.service = service;
	}

	public IDeudaRepository getDeudaRepository() {
		return _deudaRepository;
	}

	public void setDeudaRepository(IDeudaRepository _deudaRepository) {
		this._deudaRepository = _deudaRepository;
	}

	public IUnitOfWork get_unitOfWork() {
		return _unitOfWork;
	}

	public void set_unitOfWork(IUnitOfWork _unitOfWork) {
		this._unitOfWork = _unitOfWork;
	}
}
