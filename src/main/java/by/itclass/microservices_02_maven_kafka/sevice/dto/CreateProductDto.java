package by.itclass.microservices_02_maven_kafka.sevice.dto;

import java.math.BigDecimal;

public class CreateProductDto {

    private String title;
    private BigDecimal price;
    private int quantity;

    public CreateProductDto() {
    }

    public CreateProductDto(String title, BigDecimal price, int quantity) {
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
