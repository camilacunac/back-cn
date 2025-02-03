package com.example.alertas_medicas.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class RabbitMQConfigReport {

    public static final String EXCHANGE_NAME = "cn_exchange2";
    public static final String QUEUE_NAME = "cn_service2";
    public static final String ROUTING_KEY = "clave456";

    @Bean
    public Queue queueService2() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchangeService2() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingService2(Queue queueService2, DirectExchange exchangeService2) {
        return BindingBuilder.bind(queueService2).to(exchangeService2).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverterService2() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplateService2(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverterService2());
        return rabbitTemplate;
    }
}
