package com.akjkhatri.restvsgraphql.graphql.product;

import com.akjkhatri.restvsgraphql.schema.Product;
import com.akjkhatri.restvsgraphql.schema.Review;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductResolver implements GraphQLQueryResolver {
    public Product getProduct(int id) {
        return new Product(123, "Smartphone", 699.99, List.of(
                new Review(1, "Great phone!"),
                new Review(2, "Worth the price.")
        ));
    }
}
