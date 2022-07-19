package com.diplo.application.mspago.service.deuda;

import java.util.concurrent.Future;

public interface IDeudaService {
	Future<String> GenerarNroDeudaAsync();
}
