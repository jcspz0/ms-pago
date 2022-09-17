package com.diplo.infraestructure.mspago.amqp;

import java.text.SimpleDateFormat;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMQConfig {

	@Value("pago.deudavencida.reserva.vencerreserva")
	private String deudaVencidaRoutingkey;

	@Value("pago.deudapagada.reserva.confirmarreserva")
	private String deudaPagadaRoutingkey;

	@Bean
	Queue deudaVencidaToReserva() {
		return new Queue("pago.deudavencida.reserva.vencerreserva", true);
	}

	@Bean
	Queue deudaPagadaToReserva() {
		return new Queue("pago.deudapagada.reserva.confirmarreserva", true);
	}

	@Bean
	TopicExchange deudaVencidaExchange() {
		return new TopicExchange("pago.deudavencida.exchange");
	}

	@Bean
	TopicExchange deudaPagadaExchange() {
		return new TopicExchange("pago.deudapagada.exchange");
	}

	/*@Bean
	Binding pagoBinding(Queue crearReservaToDeuda, TopicExchange exchange) {
		return BindingBuilder.bind(crearReservaToDeuda).to(exchange).with(reservaCreadaRoutingkey);
	}*/

	@Bean
	Binding deudaVencidaBinding(
		Queue deudaVencidaToReserva,
		TopicExchange deudaVencidaExchange
	) {
		return BindingBuilder
			.bind(deudaVencidaToReserva)
			.to(deudaVencidaExchange)
			.with(deudaVencidaRoutingkey);
	}

	@Bean
	Binding deudaPagadaBinding(
		Queue deudaPagadaToReserva,
		TopicExchange deudaPagadaExchange
	) {
		return BindingBuilder
			.bind(deudaPagadaToReserva)
			.to(deudaPagadaExchange)
			.with(deudaPagadaRoutingkey);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	/*
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	    builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	    return builder;
	}
*/

	@Bean
	public AmqpTemplate myRabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(
			connectionFactory
		);
		//rabbitTemplate.setMessageConverter(jsonMessageConverter());
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
