package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class generar_factura {

	String id;
	String detalle;
	int nit;
	WebClient client;
	Mono<FacturaDTO> resultadoRest;
	int estatusCode;
	String codigo_factura;

	@Given(
		"El id de la deuda a facturar es {string}, el detalle es {string} y el nit es {int}"
	)
	public void given(String unputDeudaId, String inputDetalle, int inputNit)
		throws Throwable {
		id = unputDeudaId;
		detalle = inputDetalle;
		nit = inputNit;
	}

	@When("generar factura")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.get()
				.uri(
					"/deuda/factura?deudaID={id}&detalle={detalle}&nit={nit}",
					id,
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
			resultadoRest.subscribe(resultado -> {
				codigo_factura = resultado.getCodigoFactura();
			});
			Thread.sleep(5000);
			//resultadoRest.block();
		} catch (Exception e) {}
	}

	@Then("Se genero codigo de una factura")
	public void then() throws Throwable {
		assertNotNull(codigo_factura);
	}

	@And("El codigo de respuesta al generar factura es {int}")
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
