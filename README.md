# ⚡🛒 TrendKart – A Social-Commerce Platform Where Trends Drive Sales

TrendKart is the backend for a Social + E-Commerce hybrid platform where creators can promote and sell their products through engaging content. Users can like, comment, share, and shop directly from posts.


📦 Multi-Database Architecture (Polyglot Persistence)

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
