package com.akjkhatri.restvsgraphql.rest.product;

import com.akjkhatri.restvsgraphql.schema.Product;
import com.akjkhatri.restvsgraphql.schema.Review;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return new Product(123, "Smartphone", 699.99, null);
    }

    @GetMapping("/products/{id}/reviews")
    public List<Review> getProductReviews(@PathVariable int id) {
        return List.of(
                new Review(1, "Great phone!"),
                new Review(2, "Worth the price.")
        );
    }
}