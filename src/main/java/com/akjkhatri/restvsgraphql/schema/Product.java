package com.akjkhatri.restvsgraphql.schema;

import java.util.List;


public record Product(
        int id,
        String name,
        double price,
        List<Review> reviews
) {
}
