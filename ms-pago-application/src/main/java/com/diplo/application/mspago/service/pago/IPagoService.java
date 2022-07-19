package com.diplo.application.mspago.service.pago;

import java.util.concurrent.Future;

public interface IPagoService {
	Future<String> GenerarNroPagoAsync();
}
