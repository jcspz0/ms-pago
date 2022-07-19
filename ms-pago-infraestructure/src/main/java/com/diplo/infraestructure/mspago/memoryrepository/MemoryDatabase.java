package com.diplo.infraestructure.mspago.memoryrepository;

import com.diplo.mspago.model.deuda.Deuda;
import java.util.ArrayList;
import java.util.List;

public class MemoryDatabase {

	private final List<Deuda> _deudas;

	public List<Deuda> get_deudas() {
		return _deudas;
	}

	public MemoryDatabase(List<Deuda> _deudas) {
		this._deudas = new ArrayList<Deuda>();
	}
}
