package com.diplo.webapi.mspago.controller;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import com.diplo.application.mspago.usecase.command.deuda.creardeuda.CrearDeudaCommand;
import com.diplo.application.mspago.usecase.command.deuda.vencerdeuda.VencerDeudaCommand;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoCommand;
import com.diplo.application.mspago.usecase.command.pago.realizarpago.RealizarPagoReturnInfoCommand;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaById.GetDeudaByIdQuery;
import com.diplo.application.mspago.usecase.query.deuda.getDeudaByReservaId.GetDeudaByReservaIdQuery;
import com.diplo.application.mspago.usecase.query.deuda.getFacturaById.GetFacturaByIdDeudaQuery;
import com.diplo.webapi.mspago.service.MsPagoWebApiService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/deuda")
@CrossOrigin(origins = "*")
public class DeudaController {

	@Autowired
	MsPagoWebApiService _deudaService;

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public DeudaDTO CreateDeuda(@RequestBody DeudaDTO deuda) {
		System.out.println("Controller->CreateDeuda");
		try {
			UUID deudaId =
				this._deudaService.getMediator()
					.Send(
						new CrearDeudaCommand(
							deuda.getReservaId(),
							deuda.getTotal()
						)
					);
			DeudaDTO deudaDTO =
				this._deudaService.getMediator()
					.Send(new GetDeudaByIdQuery(deudaId));
			return deudaDTO;
		} catch (Exception e) {
			System.out.println("Excepcion " + e);
			return null;
		}
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public DeudaDTO FindDeudaById(@RequestParam String id) {
		System.out.println("Controller->BuscarDeuda");
		DeudaDTO deudaDTO = null;
		try {
			deudaDTO =
				this._deudaService.getMediator()
					.Send(new GetDeudaByIdQuery(UUID.fromString(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (deudaDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}
		return deudaDTO;
	}

	@RequestMapping(value = "/realizarpago", method = RequestMethod.POST)
	public PagoDTO RealizarPago(@RequestBody PagoDTO pago) {
		System.out.println("Controller->RealizarPago");
		PagoDTO pagoDTO = null;
		try {
			pagoDTO =
				this._deudaService.getMediator()
					.Send(
						new RealizarPagoReturnInfoCommand(
							pago.getDeudaId(),
							pago.getMontoPagado(),
							pago.getDetalle()
						)
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (pagoDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.CONFLICT,
					"No es posible realizar el pago, revisar los datos"
				);
			}
		}
		return pagoDTO;
	}

	@RequestMapping(value = "/factura", method = RequestMethod.GET)
	public FacturaDTO GenerarFacturaById(
		@RequestParam String deudaID,
		@RequestParam String detalle,
		@RequestParam int nit
	) {
		System.out.println("Controller->GenerarFactura");
		System.out.println("Controller->deudaID:" + deudaID);
		System.out.println("Controller->Detalle:" + detalle);
		System.out.println("Controller->nit:" + nit);
		FacturaDTO facturaDTO = null;
		try {
			facturaDTO =
				this._deudaService.getMediator()
					.Send(
						new GetFacturaByIdDeudaQuery(
							UUID.fromString(deudaID),
							nit,
							detalle
						)
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (facturaDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.CONFLICT,
					"No puede generar la facuta mientras no se haya terminado de pagar la deuda o no exista la deuda"
				);
			}
		}
		return facturaDTO;
	}

	@RequestMapping(value = "/vencerdeuda", method = RequestMethod.GET)
	public DeudaDTO VencerDeudaById(@RequestParam String id) {
		System.out.println("Controller->VencerDeuda");
		UUID deudaID = null;
		try {
			deudaID =
				this._deudaService.getMediator()
					.Send(new VencerDeudaCommand(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (deudaID == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}

		DeudaDTO deudaDTO = null;
		try {
			deudaDTO =
				this._deudaService.getMediator()
					.Send(new GetDeudaByIdQuery(UUID.fromString(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (deudaDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}
		return deudaDTO;
	}

	@RequestMapping(
		value = "/buscardeudabyreservaid",
		method = RequestMethod.GET
	)
	public DeudaDTO FindDeudaByReservaId(@RequestParam String id) {
		System.out.println("Controller->BuscarDeuda");
		DeudaDTO deudaDTO = null;
		try {
			deudaDTO =
				this._deudaService.getMediator()
					.Send(new GetDeudaByReservaIdQuery(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (deudaDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}
		return deudaDTO;
	}
}
