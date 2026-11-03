package br.com.alexia.consumer.rabbitmq.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static constants.RabbitMQConstants.RK_PRODUCT_LOG;
import dtos.ProductDTO;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ProductConsumer {

    @RabbitListener(queues = {RK_PRODUCT_LOG})
    public void consume(ProductDTO message) {
        log.info("Received message: " + message.toString());
    }
}
