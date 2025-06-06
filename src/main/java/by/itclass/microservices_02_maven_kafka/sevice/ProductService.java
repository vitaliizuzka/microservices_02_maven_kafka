package by.itclass.microservices_02_maven_kafka.sevice;

import by.itclass.microservices_02_maven_kafka.sevice.dto.CreateProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {

    String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException;

}
