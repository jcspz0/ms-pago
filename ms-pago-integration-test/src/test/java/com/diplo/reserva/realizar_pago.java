package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class realizar_pago {

	WebClient client;
	Mono<PagoDTO> resultadoRest;
	int estatusCode;
	PagoDTO request;
	PagoDTO response;

	String deudaId;
	double monto;
	String detalle;

	@Given(
		"dada la deuda {string} se requiere realizar el pago de {double} por concepto de {string}"
	)
	public void given(
		String inputDeudaId,
		double inputMonto,
		String inputDetalle
	) throws Throwable {
		deudaId = inputDeudaId;
		monto = inputMonto;
		detalle = inputDetalle;

		request = new PagoDTO(monto, detalle, deudaId);
	}

	@When("realizar el pago con los datos proporcionados")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.post()
				.uri("/deuda/realizarpago")
				.body(Mono.just(request), PagoDTO.class)
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
			response = resultadoRest.block();
		} catch (Exception e) {
			System.out.println("Entro en excepcion " + estatusCode);
		}
	}

	@Then("Se obtiene una respuesta con codigo {int} por el pago realizado")
	public void then(int codigo) throws Throwable {
		assertNotNull(response);
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
