package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import reactor.core.publisher.Mono;

public class buscar_deuda_por_id {

	private String id;
	WebClient client;
	Mono<DeudaDTO> resultadoRest;
	int estatusCode;
	String estado;

	@Given("El id de la deuda a encontrar es {string}")
	public void given(String reserva_id) throws Throwable {
		id = reserva_id;
	}

	@When("Busco la deuda por el id")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.get()
				.uri("/deuda/buscar?id={id}", id)
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
				.bodyToMono(DeudaDTO.class);

		try {
			resultadoRest.subscribe(resultado -> {
				estado = resultado.getEstado();
			});
			resultadoRest.block();
		} catch (Exception e) {}
	}

	@Then("El estado de la deuda encontrada es {string}")
	public void then(String estadoEsperado) throws Throwable {
		assertEquals(estadoEsperado, estado);
	}

	@And("El codigo de respuesta al buscar la deuda es {int}")
	public void and(int codigo) throws Throwable {
		assertEquals(codigo, estatusCode);
	}

	private void initClientRest() {
		client =
			WebClient
				.builder()
				.baseUrl("http://localhost:8081")
				//.defaultCookie("cookieKey", "cookieValue")
				//.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				//.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				//.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
				.build();
		estatusCode = 200;
	}
}
