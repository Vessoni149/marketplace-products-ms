package com.integrador.msproducts.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private int sellerCode;
    private String sellerName;
    private String productName;
    private String brand;
    private double price;
    private int amount;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Image> image = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return code == product.code && sellerCode == product.sellerCode && Double.compare(product.price, price) == 0 && amount == product.amount && Objects.equals(productName, product.productName) && Objects.equals(brand, product.brand) && Objects.equals(description, product.description) && Objects.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, sellerCode, productName, brand, price, amount, description, image);
    }
}
