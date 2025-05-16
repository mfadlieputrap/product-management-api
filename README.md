# 🛍️ Product Management API

A RESTful API for managing products in a catalog system. Built using **Spring Boot**, this API supports full CRUD operations and integrates OpenAPI documentation and automated testing.

---

## 🚀 Features

- Create, Read, Update, Delete Products
- Input validation with `@Valid`
- Custom exception handling
- Layered architecture (Controller, Service, Repository, DTO)
- OpenAPI 3.0 documentation (Swagger UI)
- Unit & integration tests with JUnit + Mockito + MockMvc

---

## 🧱 Tech Stack

- **Java 21**
- **Spring Boot 3**
- **Spring Web**, **Spring Data JPA**, **H2** (for testing)
- **OpenAPI / Swagger**
- **JUnit 5**, **Mockito**, **MockMvc**

---

## 📦 API Endpoints

| Method | Endpoint        | Description             |
|--------|------------------|-------------------------|
| GET    | `/products`      | Get all products        |
| GET    | `/products/{id}` | Get product by ID       |
| POST   | `/products`      | Create new product      |
| PUT    | `/products/{id}` | Update existing product |
| DELETE | `/products/{id}` | Delete product          |

---

## 📄 Sample Product JSON

```json
{
  "name": "T-shirt",
  "price": 120000,
  "stock": 10,
  "description": "Kaos polos"
}
```

---

## ▶️ Run the App

```bash
./mvnw spring-boot:run
```

App will be available at: [http://localhost:8080](http://localhost:8080)

---

## 🔍 API Documentation

After running the app, access Swagger UI:

👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ✅ Running Tests

```bash
./mvnw test
```

Includes:
- Unit tests for service layer (with Mockito)
- Integration tests for controller (with MockMvc)

---

## 📁 Folder Structure

```
src
├── main
│   └── java/com/example/product_managemen_api
│       ├── controller
│       ├── service
│       ├── service/impl
│       ├── dto
│       ├── model
│       ├── repository
│       └── exception
└── test
    └── java/com/example/product_managemen_api
        ├── service
        └── controller
```

---

## 💡 Notes

- Uses H2 for testing, can be switched to MySQL/PostgreSQL
- Separate DTO layer for safety and abstraction
- Clean architecture best practices

---

## 📜 License

MIT License

---

## 🙌 Author

**Muhamad Fadlie Putra Pratama**  
[LinkedIn](https://linkedin.com/in/mfadlieputrap) | [GitHub](https://github.com/mfadlieputrap)
