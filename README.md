# Order Management API

A Java backend project for managing products, orders, and stock. The project focuses on business rules, encapsulation, monetary calculations, and automated testing, with REST endpoints and database persistence planned next.

> **Status: In development.** The domain model and initial unit tests are implemented. REST endpoints, database persistence, and deployment are not yet implemented.

## Implemented Features

- Product creation with name, price, and stock validation.
- Price changes, stock replenishment, and stock removal through domain methods.
- Order items with positive quantities and a unit price recorded at creation.
- Subtotal and order total calculations using `BigDecimal`.
- Order states: `PENDENTE` (pending), `CONFIRMADO` (confirmed), and `CANCELADO` (cancelled).
- Item addition restricted to pending orders.
- Confirmation that groups quantities by product and checks all stock levels before deducting stock.
- Cancellation restricted to pending orders.
- Initial JUnit tests for item subtotals, recorded prices, and successful order confirmation.

## Domain Model

| Class | Responsibility |
| --- | --- |
| `Produto` | Maintains product details, current price, and stock rules. |
| `ItemPedido` | References a product and records the quantity and unit price for an order line. |
| `Pedido` | Holds order items, calculates the total, and controls confirmation and cancellation. |
| `StatusPedido` | Defines the available order states. |

An order contains multiple items. Each item references one product. Changing a product's current price does not change the price already recorded in an existing item.

## Confirmation Rules

1. The order must be pending and contain at least one item.
2. Quantities are grouped by product, including products appearing in multiple items.
3. Available stock is checked for every product before any stock is deducted.
4. If stock is sufficient, the required quantities are deducted and the order becomes confirmed.
5. Otherwise, confirmation is rejected and stock remains unchanged in the current single-threaded flow.

For example, two items requesting 3 and 4 units of the same product require 7 units in total. If only 5 are available, confirmation fails.

## Technology

| Technology | Usage |
| --- | --- |
| Java 17 | Domain implementation |
| Spring Boot | Application foundation |
| Maven | Build and dependency management |
| JUnit Jupiter | Unit testing |
| Spring Data JPA and PostgreSQL | Dependencies included; persistence implementation planned |

## Run Locally

### Requirements

- JDK 17.
- Maven, or the project's Maven Wrapper if included in your checkout.
- PostgreSQL if required by the current application configuration.

Clone the repository:

```bash
git clone https://github.com/ricksaud/order_management.git
cd order_management
```

Open the project in IntelliJ IDEA and import it as a Maven project.

Because the project includes Spring Data JPA and the PostgreSQL driver, starting Spring Boot may require datasource configuration even before entity mappings are implemented. If applicable, provide your local settings in `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set `DB_URL` (for example, `jdbc:postgresql://localhost:5432/order_management`), `DB_USERNAME`, and `DB_PASSWORD` in your environment or IntelliJ run configuration. The database must already exist. Do not commit database credentials.

Run `OrderManagementApplication` from IntelliJ, or use:

```bash
mvn spring-boot:run
```

The current application exercises the domain through `CommandLineRunner`; no HTTP endpoints are available yet.

## Run Unit Tests

Tests are located in `src/test/java/com/ricksaud/order_management`.

Run `ItemPedidoTest` and `PedidoTest` from IntelliJ, or execute the domain test classes with Maven:

```bash
mvn -Dtest=ItemPedidoTest,PedidoTest test
```

These domain tests instantiate Java objects directly and do not require a running Spring application or database. Other application-context tests, if present, may require datasource configuration.

## Roadmap

- [x] Product, order item, and order domain model.
- [x] Stock validation and deduction during confirmation.
- [x] Initial domain unit tests.
- [ ] Additional tests for exceptions, duplicate product quantities, and rejected state transitions.
- [ ] JPA entity mappings and PostgreSQL persistence.
- [ ] Application services and transactional order confirmation.
- [ ] REST endpoints and request/response DTOs.
- [ ] Request validation and consistent HTTP error responses.
- [ ] Database migrations and integration tests.
- [ ] OpenAPI/Swagger documentation.
- [ ] Deployment and production configuration.

## Current Limitations

- Product grouping currently relies on object identity: repeated items must reference the same `Produto` instance. Database identity handling will be revisited during persistence work.
- Stock checks and deductions currently operate in memory. The two-pass validation does not provide database transaction rollback or protection against concurrent purchases.
- Authentication, payments, shipping, and a frontend are outside the initial version's scope.

## Author

[Ricardo Souto](https://github.com/ricksaud)
