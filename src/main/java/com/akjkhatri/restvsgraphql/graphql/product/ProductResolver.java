package com.akjkhatri.restvsgraphql.graphql.product;

import com.akjkhatri.restvsgraphql.schema.Product;
import com.akjkhatri.restvsgraphql.schema.Review;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductResolver {
    @QueryMapping
    public Product product(@Argument int id) {
        return new Product(123, "Smartphone", 699.99, List.of(
                new Review(1, "Great phone!"),
                new Review(2, "Worth the price.")
        ));
    }
}
