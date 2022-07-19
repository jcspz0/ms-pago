package com.diplo.application.mspago.service;

import com.diplo.application.mspago.mediator.IMediator;
import com.diplo.application.mspago.mediator.Mediator;
import com.diplo.application.mspago.service.deuda.DeudaService;
import com.diplo.application.mspago.service.deuda.IDeudaService;
import com.diplo.application.mspago.service.pago.IPagoService;
import com.diplo.application.mspago.service.pago.PagoService;
import com.diplo.mspago.factory.deuda.DeudaFactory;
import com.diplo.mspago.factory.deuda.IDeudaFactory;
import com.diplo.mspago.factory.pago.IPagoFactory;
import com.diplo.mspago.factory.pago.PagoFactory;
import org.springframework.stereotype.Service;

@Service
public class MsPagoApplicationService implements IMediatorApplicationService {

	private IMediator _mediator;
	private IDeudaService _deudaService;
	private IDeudaFactory _DeudaFactory;
	private IPagoService _pagoService;
	private IPagoFactory _PagoFactory;

	@Override
	public void AddApplication() {
		_mediator = new Mediator();
		_deudaService = new DeudaService();
		_DeudaFactory = new DeudaFactory();
		_pagoService = new PagoService();
		_PagoFactory = new PagoFactory();
	}

	public MsPagoApplicationService() {
		super();
		AddApplication();
	}

	public void AddApplication(
		IMediator _mediator,
		IDeudaService _deudaService,
		IPagoService _pagoService,
		IDeudaFactory _deudaFactory,
		IPagoFactory _pagoFactory
	) {
		this._mediator = _mediator;
		this._deudaService = _deudaService;
		this._DeudaFactory = _deudaFactory;
		this._pagoService = _pagoService;
		this._PagoFactory = _pagoFactory;
	}

	public MsPagoApplicationService(
		IMediator _mediator,
		IDeudaService _DeudaService,
		IPagoService _pagoService,
		IDeudaFactory _DeudaFactory,
		IPagoFactory _pagoFactory
	) {
		super();
		this._mediator = _mediator;
		this._deudaService = _DeudaService;
		this._DeudaFactory = _DeudaFactory;
		this._pagoService = _pagoService;
		this._PagoFactory = _pagoFactory;
	}

	@Override
	public IMediator getMediator() {
		return _mediator;
	}

	public void setMediator(IMediator _mediator) {
		this._mediator = _mediator;
	}

	public IDeudaService getDeudaService() {
		return _deudaService;
	}

	public void setDeudaService(IDeudaService _deudaService) {
		this._deudaService = _deudaService;
	}

	public IDeudaFactory getDeudaFactory() {
		return _DeudaFactory;
	}

	public void setDeudaFactory(IDeudaFactory _DeudaFactory) {
		this._DeudaFactory = _DeudaFactory;
	}

	public IPagoService getPagoService() {
		return _pagoService;
	}

	public void setPagoService(IPagoService _pagoService) {
		this._pagoService = _pagoService;
	}

	public IPagoFactory getPagoFactory() {
		return _PagoFactory;
	}

	public void setPagoFactory(IPagoFactory _PagoFactory) {
		this._PagoFactory = _PagoFactory;
	}
}
