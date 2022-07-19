package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class obtener_factura {

	WebClient client;
	Mono<DeudaDTO> resultadoRestBuscarDeuda;
	Mono<DeudaDTO> resultadoRestCrearDeuda;
	int estatusCode;
	DeudaDTO requestDeuda;

	Mono<PagoDTO> resultadoRestPago;
	PagoDTO requestPago;

	Mono<FacturaDTO> resultadoRestFactura;

	String reservaId;
	String deudaId;
	String detalle;
	int nit;

	@Given(
		"dada la reserva {string} y los datos de facturacion con detalle {string} y nit {int}"
	)
	public void CargarDatosEntrada(
		String inputReservaId,
		String inputDetalle,
		int inputNit
	) throws Throwable {
		reservaId = inputReservaId;
		detalle = inputDetalle;
		nit = inputNit;
	}

	@When("obtener la deuda de la reserva a facturar")
	public void cargarDeudaParaFactura() throws Throwable {
		obtenerDeuda();
	}

	private void obtenerDeuda() {
		initClientRest();

		resultadoRestBuscarDeuda =
			client
				.get()
				.uri("/deuda/buscardeudabyreservaid?id={reservaId}", reservaId)
				.retrieve()
				.onStatus(
					httpStatus -> httpStatus.is4xxClientError(),
					response -> {
						estatusCode = response.statusCode().value();
						return Mono.error(
							new HttpClientErrorException(response.statusCode())
						);
					}
				)
				.onStatus(
					httpStatus -> httpStatus.is5xxServerError(),
					response ->
						Mono.error(
							new HttpServerErrorException(response.statusCode())
						)
				)
				.bodyToMono(new ParameterizedTypeReference<DeudaDTO>() {});

		try {
			resultadoRestBuscarDeuda.subscribe(resultado -> {
				deudaId = resultado.getDeudaId();
				System.out.println("Se encontró la deuda " + deudaId);
			});
			resultadoRestBuscarDeuda.block();
		} catch (Exception e) {}
	}

	@And("generar la factura")
	public void generarFactura() {
		initClientRest();

		resultadoRestFactura =
			client
				.get()
				.uri(
					"/deuda/factura?deudaID={id}&detalle={detalle}&nit={nit}",
					deudaId,
					detalle,
					nit
				)
				.retrieve()
				.onStatus(
					httpStatus -> httpStatus.is4xxClientError(),
					response -> {
						estatusCode = response.statusCode().value();
						return Mono.error(
							new HttpClientErrorException(response.statusCode())
						);
					}
				)
				.onStatus(
					httpStatus -> httpStatus.is5xxServerError(),
					response ->
						Mono.error(
							new HttpServerErrorException(response.statusCode())
						)
				)
				.bodyToMono(FacturaDTO.class);

		try {
			resultadoRestFactura.subscribe(resultado -> {
				System.out.println(
					"Se generó la factura " + resultado.getCodigoFactura()
				);
			});
			resultadoRestFactura.block();
		} catch (Exception e) {}
	}

	@Then(
		"Se obtiene una respuesta con codigo {int} por la obtencion de la factura"
	)
	public void validarFactura(int codigo) throws Throwable {
		assertEquals(codigo, estatusCode);
	}

	private void initClientRest() {
		client =
			WebClient
				.builder()
				.baseUrl("http://localhost:8081")
				// .defaultCookie("cookieKey", "cookieValue")
				// .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				// .defaultUriVariables(Collections.singletonMap("url",
				// "http://localhost:8080"))
				.build();
		estatusCode = 200;
	}
}
