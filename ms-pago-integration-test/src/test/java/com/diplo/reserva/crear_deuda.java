package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class crear_deuda {

	WebClient client;
	Mono<DeudaDTO> resultadoRest;
	int estatusCode;
	DeudaDTO request;
	DeudaDTO response;

	String reservaId;
	double total;

	@Given("dada la reserva {string} con un monto total de {double}")
	public void given(String inputReservaId, double inputTotal)
		throws Throwable {
		reservaId = inputReservaId;
		total = inputTotal;

		request = new DeudaDTO(null, reservaId, total);
	}

	@When("Crear la deuda con los datos proporcionados")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.post()
				.uri("/deuda/crear")
				.body(Mono.just(request), DeudaDTO.class)
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
			response = resultadoRest.block();
		} catch (Exception e) {
			System.out.println("Entro en excepcion " + estatusCode);
		}
	}

	@Then("Se obtiene una respuesta con codigo {int} por la creacion")
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
