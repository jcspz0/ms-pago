package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class vencer_deuda_por_reserva_id {

	int estatusCode;
	Mono<DeudaDTO> resultadoDeudaRest;
	Mono<DeudaDTO> resultadoDeudaVencidaRest;
	DeudaDTO resultado;
	WebClient client;
	String reservaId;

	@Given("Dado el id de reserva {string}")
	public void given(String inputReservaId) throws Throwable {
		reservaId = inputReservaId;
	}

	@When("Vencer la deuda asociada a la reserva")
	public void when() throws Throwable {
		initClientRest();

		resultadoDeudaRest =
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
			/*resultadoDeudaRest.subscribe(resultado -> {
					}

					);*/
			//Thread.sleep(5000);
			resultado = resultadoDeudaRest.block();
		} catch (Exception e) {}

		System.out.println("Deuda encontrada " + resultado.getDeudaId());
		resultadoDeudaVencidaRest =
			client
				.get()
				.uri("/deuda/vencerdeuda?id={reservaId}", resultado.getDeudaId())
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
			/*resultadoDeudaVencidaRest.subscribe(resultado -> {
										}
									);
							Thread.sleep(10000);*/
			resultado = resultadoDeudaVencidaRest.block();
		} catch (Exception e) {}
	}

	@Then("El estado de la reserva es {string}")
	public void then(String estado) throws Throwable {
		assertEquals(estado, resultado.getEstado());
	}

	@And("el codigo de respuesta obtenido al vencer la reserva es {int}")
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
