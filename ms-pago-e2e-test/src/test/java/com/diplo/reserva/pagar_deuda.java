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

public class pagar_deuda {

	WebClient client;
	Mono<DeudaDTO> resultadoRestBuscarDeuda;
	Mono<DeudaDTO> resultadoRestCrearDeuda;
	int estatusCode;
	DeudaDTO requestDeuda;

	Mono<PagoDTO> resultadoRestPago;
	PagoDTO requestPago;

	Mono<FacturaDTO> resultadoRestFactura;

	String reservaId;
	double total;
	double montoPagado;
	String deudaId;
	String detalle;
	int nit;

	@Given(
		"dada la reserva {string} con monto de {double} se realiza el pago de {double} con detalle {string} y nit {int}"
	)
	public void CargarDatosEntrada(
		String inputReservaId,
		double inputTotal,
		double inputMontoPagado,
		String inputDetalle,
		int inputNit
	) throws Throwable {
		reservaId = inputReservaId;
		total = inputTotal;
		montoPagado = inputMontoPagado;
		detalle = inputDetalle;
		nit = inputNit;
	}

	@When("obtener la deuda de la reserva")
	public void cargarDeuda() throws Throwable {
		obtenerDeuda();
		if (estatusCode != 200) {
			crearDeuda();
		}
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

	private void crearDeuda() {
		initClientRest();

		requestDeuda = new DeudaDTO(null, reservaId, total);

		resultadoRestCrearDeuda =
			client
				.post()
				.uri("/deuda/crear")
				.body(Mono.just(requestDeuda), DeudaDTO.class)
				.retrieve()
				.onStatus(
					httpStatus -> httpStatus.is4xxClientError(),
					response -> {
						estatusCode = response.statusCode().value();
						System.out.println(
							"codigo de respuiesta " + estatusCode
						);
						return Mono.error(
							new HttpClientErrorException(response.statusCode())
						);
					}
				)
				.onStatus(
					httpStatus -> httpStatus.is5xxServerError(),
					response -> {
						estatusCode = response.statusCode().value();
						System.out.println(
							"codigo de respuiesta " + estatusCode
						);
						return Mono.error(
							new HttpServerErrorException(response.statusCode())
						);
					}
				)
				.bodyToMono(DeudaDTO.class);

		try {
			resultadoRestCrearDeuda.subscribe(resultado -> {
				deudaId = resultado.getDeudaId();
				System.out.println("Se creó la deuda " + deudaId);
			});
			resultadoRestCrearDeuda.block();
		} catch (Exception e) {
			System.out.println("Entro en excepcion " + estatusCode);
		}
	}

	@And("realizar el pago")
	public void realizarPago() {
		initClientRest();

		requestPago = new PagoDTO(montoPagado, detalle, deudaId);

		resultadoRestPago =
			client
				.post()
				.uri("/deuda/realizarpago")
				.body(Mono.just(requestPago), PagoDTO.class)
				.retrieve()
				.onStatus(
					httpStatus -> httpStatus.is4xxClientError(),
					response -> {
						estatusCode = response.statusCode().value();
						System.out.println(
							"codigo de respuiesta " + estatusCode
						);
						return Mono.error(
							new HttpClientErrorException(response.statusCode())
						);
					}
				)
				.onStatus(
					httpStatus -> httpStatus.is5xxServerError(),
					response -> {
						estatusCode = response.statusCode().value();
						System.out.println(
							"codigo de respuiesta " + estatusCode
						);
						return Mono.error(
							new HttpServerErrorException(response.statusCode())
						);
					}
				)
				.bodyToMono(PagoDTO.class);

		try {
			resultadoRestPago.subscribe(resultado -> {
				System.out.println("Se creó el pago " + resultado.getPagoId());
			});
			resultadoRestPago.block();
		} catch (Exception e) {
			System.out.println("Entro en excepcion " + estatusCode);
		}
	}

	@Then("Se obtiene una respuesta con codigo {int} por el pago")
	public void then(int codigo) throws Throwable {
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
