package com.example.rabbittest;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderConf {

	@Bean
	public Queue dateQueue(){
		return new Queue("datequeue");
	}
	
	@Bean
	public Queue objQueue(){
		return new Queue("objectQueue");
	}
	
	@Bean
	public Queue jsonQueue(){
		return new Queue("jsonQueue");
	}
	
	@Bean
	FanoutExchange fanoutExchange(){
		return new FanoutExchange("exchange");
	}
	
	@Bean
	Binding bingdingExchangeA(Queue dateQueue, FanoutExchange exchange) {
		return BindingBuilder.bind(dateQueue).to(exchange);
	}
	
	@Bean
	Binding bingdingExchangeb(Queue objQueue, FanoutExchange exchange) {
		return BindingBuilder.bind(objQueue).to(exchange);
	}
}
