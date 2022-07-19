package com.diplo.infraestructure.mspago.entityframework.dbrepository;

import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.DeudaEntity;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.PagoEntity;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.repository.DeudaEntityRepository;
import com.diplo.infraestructure.mspago.entityframework.tracker.MessageEvent;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DbDeudaRepository
	implements IDeudaRepository, ApplicationEventPublisherAware {

	@Autowired
	private DeudaEntityRepository _database;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public Future<Deuda> FindByIdAsync(UUID id) {
		try {
			DeudaEntity aux = _database.findById(id.toString()).get();
			List<Pago> listaPago = new ArrayList<Pago>();
			if (aux.getListaPagos().size() > 0) {
				for (PagoEntity pagoEntity : aux.getListaPagos()) {
					listaPago.add(
						new Pago(
							UUID.fromString(pagoEntity.getPagoId()),
							new Monto(pagoEntity.getMontoPagado()),
							new DetallePago(pagoEntity.getDetalle()),
							UUID.fromString(pagoEntity.getDeuda().getDeudaId())
						)
					);
				}
			}
			Deuda result = new Deuda(
				UUID.fromString(aux.getDeudaId()),
				UUID.fromString(aux.getReservaId()),
				new Monto(aux.getTotal()),
				aux.getEstado(),
				listaPago
			);
			return CompletableFuture.completedFuture(result);
		} catch (Exception e) {
			System.out.println(
				"DbDeudaRepository->Se encontró una excepcion al tratar de obtener la deuda por ID:" +
				e
			);
			return CompletableFuture.completedFuture(null);
		}
	}

	@Override
	public Future<Deuda> CreateAsync(Deuda obj) {
		System.out.println("CreateAsync DBRepository");
		_database.save(new DeudaEntity(obj));
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,"create"));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Deuda> UpdateAsync(Deuda obj) {
		System.out.println("UpdateAsync DBRepository");
		_database.save(new DeudaEntity(obj));
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,"update"));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public Future<Deuda> FindByReservaIdAsync(String reservaId) {
		try {
			DeudaEntity aux = _database.findDeudaByReservaId(reservaId);
			List<Pago> listaPago = new ArrayList<Pago>();
			if (aux.getListaPagos().size() > 0) {
				for (PagoEntity pagoEntity : aux.getListaPagos()) {
					listaPago.add(
						new Pago(
							UUID.fromString(pagoEntity.getPagoId()),
							new Monto(pagoEntity.getMontoPagado()),
							new DetallePago(pagoEntity.getDetalle()),
							UUID.fromString(pagoEntity.getDeuda().getDeudaId())
						)
					);
				}
			}
			Deuda result = new Deuda(
				UUID.fromString(aux.getDeudaId()),
				UUID.fromString(aux.getReservaId()),
				new Monto(aux.getTotal()),
				aux.getEstado(),
				listaPago
			);
			return CompletableFuture.completedFuture(result);
		} catch (Exception e) {
			System.out.println(
				"DbDeudaRepository->Se encontró una excepcion al tratar de obtener la deuda por ID de reserva:" +
				e
			);
			return CompletableFuture.completedFuture(null);
		}
	}
}
