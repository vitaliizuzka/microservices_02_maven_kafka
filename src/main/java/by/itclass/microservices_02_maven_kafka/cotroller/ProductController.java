package by.itclass.microservices_02_maven_kafka.cotroller;

import by.itclass.microservices_02_maven_kafka.sevice.ProductService;
import by.itclass.microservices_02_maven_kafka.sevice.dto.CreateProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Object>  createProduct(@RequestBody CreateProductDto createProductDto){
        String productId = null;
        try {
            productId = productService.createProduct(createProductDto);
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
