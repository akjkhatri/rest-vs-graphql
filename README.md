# REST vs GraphQL

A minimal Spring Boot 3 service that exposes the same data — a product and its reviews — through both a REST API and a GraphQL API, so the difference in how clients fetch data can be seen side by side.

## Why this exists

REST and GraphQL solve the same problem in different ways. With REST, the server decides the shape of each response and a client that needs related data makes several calls. With GraphQL, the client asks for exactly the fields it wants in one request. This project keeps the domain deliberately tiny so the only thing that changes between the two is the API style.

| | REST | GraphQL |
|---|---|---|
| Product + reviews | 2 requests (`/products/{id}`, `/products/{id}/reviews`) | 1 request |
| Response shape | Fixed by the server | Chosen by the client |
| Endpoint | One per resource | Single `/graphql` endpoint |
| Contract | Implicit (controller methods) | Explicit schema (`product.graphqls`) |

## Tech stack

- Java 21
- Spring Boot 3.4 (`spring-boot-starter-web`, `spring-boot-starter-graphql`)
- Java records for the domain model
- Maven (wrapper included)

## Project structure

```
src/main/java/com/akjkhatri/restvsgraphql/
├── schema/              # Product and Review records shared by both APIs
├── rest/product/        # ProductController  → REST endpoints
└── graphql/product/     # ProductResolver    → GraphQL query
src/main/resources/graphql/
└── product.graphqls     # GraphQL schema
```

## Running it

```bash
./mvnw spring-boot:run
```

The app starts on `http://localhost:8080`.

### REST

Getting a product with its reviews takes two calls:

```bash
curl http://localhost:8080/api/products/123
curl http://localhost:8080/api/products/123/reviews
```

### GraphQL

The same data in one call, with the client choosing the fields:

```bash
curl -X POST http://localhost:8080/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"{ product(id: 123) { name price reviews { comment } } }"}'
```

Drop `reviews` from the query and the server never returns them — no over-fetching, and no extra endpoint needed.

## Schema

```graphql
type Product {
    id: ID!
    name: String!
    price: Float!
    reviews: [Review]
}

type Review {
    id: ID!
    comment: String!
}

type Query {
    product(id: ID!): Product
}
```

## Notes

Data is hard-coded in memory so the focus stays on the API layer. A natural next step is backing both APIs with a shared repository and adding a field-level resolver for `reviews` to show how GraphQL avoids loading data the client didn't ask for.

## Author

**Anand Kumar** — Senior Java Engineer & Tech Lead, fintech and payments systems.
