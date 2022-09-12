package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diplo.application.mspago.dto.pago.DeudaDTO;
import com.diplo.application.mspago.dto.pago.FacturaDTO;
import com.diplo.application.mspago.dto.pago.PagoDTO;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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

	boolean deudaEncontrada;
	String estadoDeudaNoPermitidos;
	boolean pagoRealizado;

	private WebDriver driver;

	@AfterEach
	void teardown() {
		driver.quit();
	}

	@Before
	public void setUp() {
		System.setProperty(
			"webdriver.gecko.driver",
			"E:\\jc\\postgrado\\programas\\geckodriver"
		);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

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

		deudaEncontrada = false;
		estadoDeudaNoPermitidos = "PAGADA|VENCIDA";
		pagoRealizado = false;
	}

	@When("obtener la deuda de la reserva")
	public void cargarDeuda() throws Throwable {
		obtenerDeuda();
		if (!deudaEncontrada) {
			crearDeuda();
		}
	}

	private void obtenerDeuda() throws Exception {
		driver.get("http://localhost:4000/deuda");
		WebElement inputBuscarReservaId = driver.findElement(
			By.id("inputReservaId")
		);
		inputBuscarReservaId.sendKeys(reservaId);
		Thread.sleep(2000);
		WebElement botonbuscardeuda = driver.findElement(
			By.id("botonbuscardeuda")
		);
		botonbuscardeuda.click();
		Thread.sleep(5000);
		List<WebElement> tbodyDeudas = driver.findElements(By.id("tbodyDeudas"));
		List<WebElement> filas = tbodyDeudas
			.get(0)
			.findElements(By.tagName("tr"));
		WebElement botonSeleccionar;
		WebElement estado;
		for (WebElement fila : filas) {
			estado = fila.findElement(By.id("columnaestado"));
			System.out.println(
				estadoDeudaNoPermitidos.contains(estado.getText())
			);
			System.out.println(estado.getText());
			System.out.println(estadoDeudaNoPermitidos);

			if (!estadoDeudaNoPermitidos.contains(estado.getText())) {
				botonSeleccionar =
					fila.findElement(By.id("tablabotonseleccionar"));
				botonSeleccionar.click();
				deudaEncontrada = true;
				Thread.sleep(2000);
				break;
			}
		}
		Thread.sleep(2000);
	}

	private void crearDeuda() throws Exception {
		WebElement habilitarcreardeuda = driver.findElement(
			By.id("habilitarcreardeuda")
		);
		habilitarcreardeuda.click();
		Thread.sleep(2000);
		WebElement inputCrearReservaId = driver.findElement(
			By.id("inputCrearReservaId")
		);
		inputCrearReservaId.sendKeys(String.valueOf(reservaId));
		WebElement inputCrearTotal = driver.findElement(
			By.id("inputCrearTotal")
		);
		inputCrearTotal.sendKeys(String.valueOf(total));
		Thread.sleep(2000);
		WebElement botoncreardeuda = driver.findElement(
			By.id("botoncreardeuda")
		);
		botoncreardeuda.click();
		Thread.sleep(5000);
		String popup = driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		System.out.println(popup);
		if (popup.contains("deuda creada correctamente")) {
			System.out.println("deuda creada correctamente");
		}

		WebElement inputBuscarReservaId = driver.findElement(
			By.id("inputReservaId")
		);
		inputBuscarReservaId.clear();
		inputBuscarReservaId.sendKeys(reservaId);
		Thread.sleep(2000);
		WebElement botonbuscardeuda = driver.findElement(
			By.id("botonbuscardeuda")
		);
		botonbuscardeuda.click();
		Thread.sleep(5000);
		List<WebElement> tbodyDeudas = driver.findElements(By.id("tbodyDeudas"));
		List<WebElement> filas = tbodyDeudas
			.get(0)
			.findElements(By.tagName("tr"));
		WebElement botonSeleccionar;
		WebElement estado;
		for (WebElement fila : filas) {
			estado = fila.findElement(By.id("columnaestado"));
			System.out.println(
				estadoDeudaNoPermitidos.contains(estado.getText())
			);
			System.out.println(estado.getText());
			System.out.println(estadoDeudaNoPermitidos);
			if (!estadoDeudaNoPermitidos.contains(estado.getText())) {
				botonSeleccionar =
					fila.findElement(By.id("tablabotonseleccionar"));
				botonSeleccionar.click();
				deudaEncontrada = true;
				Thread.sleep(2000);
				break;
			}
		}
	}

	@And("realizar el pago")
	public void realizarPago() throws Exception {
		if (deudaEncontrada) {
			List<WebElement> tbodyDeudas = driver.findElements(
				By.id("tbodyDeudas")
			);
			List<WebElement> filas = tbodyDeudas
				.get(0)
				.findElements(By.tagName("tr"));
			WebElement botonSeleccionar;
			for (WebElement fila : filas) {
				if (
					!fila.findElements(By.id("tablabotonrealizarpago")).isEmpty()
				) {
					botonSeleccionar =
						fila.findElement(By.id("tablabotonrealizarpago"));
					botonSeleccionar.click();

					WebElement inputMontoPagado = driver.findElement(
						By.id("inputMontoPagado")
					);
					inputMontoPagado.sendKeys(String.valueOf(montoPagado));
					WebElement inputDetalle = driver.findElement(
						By.id("inputDetalle")
					);
					inputDetalle.sendKeys(String.valueOf(detalle));
					Thread.sleep(2000);
					WebElement botoncreardeuda = driver.findElement(
						By.id("botonrealizarpago")
					);
					botoncreardeuda.click();
					Thread.sleep(5000);

					String popup = driver.switchTo().alert().getText();
					Thread.sleep(2000);
					driver.switchTo().alert().accept();
					System.out.println(popup);
					if (popup.contains("pago creado correctamente")) {
						pagoRealizado = true;
					}
					break;
				}
			}
		}
	}

	@Then("Se obtiene una respuesta con codigo {int} por el pago")
	public void then(int codigo) throws Throwable {
		assertTrue(deudaEncontrada);
		assertTrue(pagoRealizado);
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
