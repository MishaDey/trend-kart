# ⚡🛒 TrendKart – A Social-Commerce Platform Where Trends Drive Sales

TrendKart is the backend for a Social + E-Commerce hybrid platform where creators can promote and sell their products through engaging content. Users can like, comment, share, and shop directly from posts.

---
---

## 📦 Multi-Database Architecture (Polyglot Persistence)

TrendKart leverages a diverse set of databases, each chosen to suit a specific use case and data pattern:

| Type                         | Database                 | Purpose                                                                        |
| ---------------------------- | ------------------------ | ------------------------------------------------------------------------------ |
| 🟦 Relational Database       | **PostgreSQL**           | Core structured data: users, products, orders, cart                            |
| 📄 Document Store (NoSQL)    | **MongoDB**              | Flexible audit logging and semi-structured activity data                       |
| 🔑 Key-Value Store (NoSQL)   | **Redis**                | High-speed caching, API rate-limiting, user sessions                           |
| 🧱 Wide-Column Store (NoSQL) | **Apache Cassandra**     | High-write, scalable tracking of likes, views, and user engagement             |
| 🕸️ Graph Store (NoSQL)       | **Neo4j**                | Modeling social relationships (follower-following) and product recommendations |
| 🔍 Search Engine DB          | **Elasticsearch**        | Full-text product/post search with autocomplete and fuzzy matching             |
| ⏱️ Time-Series DB            | **InfluxDB**             | Time-based metrics: post analytics, active users over time                     |
| 🔥 Big Data Processing       | **Apache Spark**         | Batch analysis of user behavior and rule-based homepage recommendations        |

---
---

## 🧠 Design Patterns in Action (Applied Architecture)

TrendKart goes beyond textbook design pattern theory — each pattern is applied with purpose, enhancing modularity, scalability, and readability in the backend codebase.

### 🧱 Creational Patterns
| Pattern            | Use Case                                                                                  |
|--------------------|-------------------------------------------------------------------------------------------|
| **Singleton**       | Configuration classes, token utilities, Kafka consumers                                  |
| **Factory Method**  | NotificationFactory to create `Email`, `SMS`, `Push` services                            |
| **Abstract Factory**| PaymentGatewayFactory to provide related payment-related services                        |
| **Prototype**       | Scoped beans like `InvoiceBuilder`, `OrderSummaryGenerator` for each request             |
| **Builder**         | `ProductBuilder`, `UserProfileBuilder` – to construct objects with optional parameters   |
| **Object Pool**     | Leveraged through connection pooling (HikariCP)                                          |

### 🏗️ Structural Patterns
| Pattern        | Use Case                                                                 |
|----------------|--------------------------------------------------------------------------|
| **Adapter**     | Integrating third-party payment/OAuth APIs                              |
| **Bridge**      | CDN abstraction for media delivery (`ContentDeliveryService`)           |
| **Composite**   | Category/Sub-category hierarchy in product listing                      |
| **Decorator**   | Logging and rate-limiting wrappers around services                      |
| **Facade**      | `OrderFacade`, `CartFacade`, `ProductFacade` to simplify service usage  |
| **Flyweight**   | Reuse of immutable value objects like Currency, Size, Country           |
| **Proxy**       | Redis-based caching and authorization layers                            |

### 🧠 Behavioral Patterns
| Pattern                    | Use Case                                                                 |
|----------------------------|--------------------------------------------------------------------------|
| **Observer**               | Event system for `OrderPlaced`, `UserFollowed`, etc. via Spring/Kafka    |
| **Strategy**               | Dynamic pricing, discount, and recommendation logic                      |
| **Template Method**        | Common DB or API response formatting                                     |
| **Command**                | Encapsulated actions like refunds, scheduled tasks, job retries          |
| **Chain of Responsibility**| Validation pipelines for checkout/order flow                             |
| **Mediator**               | `CartMediator` coordinates between Product, Inventory, Coupon services   |
| **Memento**                | Cart versioning or product draft history                                 |
| **State**                  | Order status transitions (`PENDING → SHIPPED → DELIVERED`)               |
| **Interpreter**            | Parsing admin-defined filter strings to query logic                      |
| **Iterator**               | Pagination in feed/analytics results                                     |
| **Visitor**                | Admin analytics/report generation from different domain models           |
| **Null Object**            | Default no-op implementations using `@ConditionalOnMissingBean`          |

---

