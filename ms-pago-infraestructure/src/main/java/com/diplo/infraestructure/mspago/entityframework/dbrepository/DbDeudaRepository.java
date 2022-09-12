package com.diplo.infraestructure.mspago.entityframework.dbrepository;

import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.DeudaEntity;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.PagoEntity;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.repository.DeudaEntityRepository;
import com.diplo.infraestructure.mspago.entityframework.dbrepository.entity.repository.PagoEntityRepository;
import com.diplo.mspago.model.deuda.Deuda;
import com.diplo.mspago.model.deuda.Pago;
import com.diplo.mspago.repository.IDeudaRepository;
import com.diplo.mspago.valueobjects.DetallePago;
import com.diplo.mspago.valueobjects.Monto;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	private PagoEntityRepository _databaseChild;

	private Map<String, List<Deuda>> context = null;

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
		//_database.save(new DeudaEntity(obj));
		register(obj, Constant.createAction);
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,"create"));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Deuda> UpdateAsync(Deuda obj) {
		System.out.println("UpdateAsync DBRepository");
		register(obj, Constant.updateAction);
		//_database.save(new DeudaEntity(obj));
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

	private void register(Deuda deuda, String action) {
		if (context == null) {
			context = new HashMap<String, List<Deuda>>();
		}
		ArrayList<Deuda> deudaToOperate = (ArrayList<Deuda>) context.get(action);
		if (deudaToOperate == null) {
			deudaToOperate = new ArrayList<>();
		}
		deudaToOperate.add(deuda);
		context.put(action, deudaToOperate);
		for (DomainEvent event : deuda.getDomainEvents()) {
			this.applicationEventPublisher.publishEvent(
					new MessageEvent(event, action)
				);
		}
		for (IntegrationEvent event : deuda.getIntegrationEvents()) {
			this.applicationEventPublisher.publishEvent(event);
		}
	}

	@Override
	public void commit() {
		if (context == null || context.size() == 0) {
			return;
		}
		if (context.containsKey(Constant.createAction)) {
			commitCreate();
		}
		if (context.containsKey(Constant.updateAction)) {
			commitUpdate();
		}
	}

	private void commitCreate() {
		List<Deuda> DeudasToCreate = context.get(Constant.createAction);
		for (Deuda deuda : DeudasToCreate) {
			_database.save(new DeudaEntity(deuda));
		}
		context.remove(Constant.createAction);
	}

	private void commitUpdate() {
		System.out.println("Entro al commitUpdate");
		List<Deuda> DeudasToUpdate = context.get(Constant.updateAction);
		for (Deuda deuda : DeudasToUpdate) {
			System.out.println(
				"Se va a actualizar la deuda con id " + deuda.getId().toString()
			);
			System.out.println(
				"se tiene size pago de " + deuda.getListaPagos().size()
			);
			//deleteChild(deuda);
			_database.delete(new DeudaEntity(deuda));
			_database.save(new DeudaEntity(deuda));
		}
		context.remove(Constant.updateAction);
	}
}
