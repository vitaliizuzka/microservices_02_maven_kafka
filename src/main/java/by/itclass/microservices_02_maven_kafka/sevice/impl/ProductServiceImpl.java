package by.itclass.microservices_02_maven_kafka.sevice.impl;

import by.itclass.microservices_02_maven_kafka.sevice.ProductService;
import by.itclass.microservices_02_maven_kafka.sevice.dto.CreateProductDto;
import by.itclass.microservices_02_maven_kafka.sevice.event.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
class ProductServiceImpl implements ProductService {


    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(productId,
                createProductDto.getTitle(),
                createProductDto.getPrice(),
                createProductDto.getQuantity());

        //Асинхронный режим

//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =
//                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent);
//
//        future.whenComplete((result, exception) -> {
//            if (exception != null) {
//                LOGGER.error("Failed to sent message: {}", exception.getMessage());
//            } else {
//                LOGGER.info("Message sent succsessfully: {}", result.getRecordMetadata());
//            }
//        });
//        future.join();

        //Синхронный режим
       SendResult<String, ProductCreatedEvent> result =
                kafkaTemplate.send("product-created-events-topic", productId, productCreatedEvent).get();

        LOGGER.info("Topic : {}", result.getRecordMetadata().topic());
        LOGGER.info("Partition : {}", result.getRecordMetadata().partition());
        LOGGER.info("Offset : {}", result.getRecordMetadata().offset());


        LOGGER.info("Return : {}", productId);
        return productId;
    }
}
