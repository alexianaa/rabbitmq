package br.com.alexia.producer.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import static constants.RabbitMQConstants.RK_PRODUCT_LOG;
import static constants.RabbitMQConstants.QUEUE_PRODUCT_LOG;
import static constants.RabbitMQConstants.EXG_NAME_MARKETPLACE;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_PRODUCT_LOG, true, false, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXG_NAME_MARKETPLACE, true, false);
    }

    //TODO: create a fanout exchange
    //TODO: create a topic exchange

    @Bean
    public Binding binding(Queue queue, DirectExchange directExchange) {
        return BindingBuilder
                .bind(queue)
                .to(directExchange)
                .with(RK_PRODUCT_LOG);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }
}