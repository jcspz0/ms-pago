package com.diplo.mspago.factory.deuda;

import com.diplo.mspago.model.deuda.Deuda;

public interface IDeudaFactory {
	Deuda Create(String deudaID, String reservaId, double total);
}
