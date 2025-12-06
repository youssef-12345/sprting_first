# Supply Chain Management System

A complete **monolithic Supply Chain Management System** built with **Spring Boot 3.3.0** (compatible with JDK 21), **Spring Security with JWT authentication**, **Spring Data JPA**, **Spring AOP**, and **MySQL** database.

## Features

### Core Modules
1. **Product Management** - Manage products with CRUD operations, categories, and pricing
2. **Stock Management** - Track inventory levels, warehouse locations, and stock alerts
3. **Sales Management** - Process sales orders with status tracking and customer information
4. **Supplier Management** - Manage supplier information and contact details
5. **Analytics** - Dashboard with sales analytics and inventory insights

### Technical Features
- ✅ **Spring Security** with JWT token-based authentication
- ✅ **Role-Based Access Control** - ADMIN, MANAGER, USER roles
- ✅ **Spring Data JPA** for database operations with repositories
- ✅ **ModelMapper** for Entity-DTO mapping
- ✅ **Spring Validation** with annotations on DTOs
- ✅ **Swagger/OpenAPI 3.0** documentation for all endpoints
- ✅ **Spring AOP** for cross-cutting concerns (Logging Aspect)
- ✅ **Docker & Docker Compose** for easy deployment
- ✅ **RESTful API** with proper HTTP status codes
- ✅ **Exception handling** with meaningful error responses

## Project Structure

```
supply-chain-management/
├── src/main/java/com/example/supplychain/
│   ├── SupplyChainApplication.java       # Main application class
│   ├── aspect/
│   │   └── LoggingAspect.java           # AOP logging aspect
│   ├── config/
│   │   ├── SecurityConfig.java          # Spring Security configuration
│   │   ├── MapperConfig.java            # ModelMapper configuration
│   │   └── CustomUserDetailsService.java # Custom user details service
│   ├── security/
│   │   ├── JwtTokenProvider.java        # JWT token generation and validation
│   │   ├── JwtAuthenticationFilter.java # JWT authentication filter
│   │   └── JwtAuthenticationEntryPoint.java # JWT entry point
│   ├── auth/
│   │   ├── controller/
│   │   │   └── AuthController.java      # Login and registration endpoints
│   │   └── dto/
│   │       ├── LoginRequest.java
│   │       └── LoginResponse.java
│   ├── user/
│   │   ├── entity/User.java             # User entity with roles
│   │   └── repository/UserRepository.java
│   ├── product/
│   │   ├── entity/Product.java
│   │   ├── dto/ProductDTO.java
│   │   ├── repository/ProductRepository.java
│   │   ├── service/ProductService.java
│   │   ├── mapper/ProductMapper.java
│   │   └── controller/ProductController.java
│   ├── stock/
│   │   ├── entity/Stock.java
│   │   ├── dto/StockDTO.java
│   │   ├── repository/StockRepository.java
│   │   ├── service/StockService.java
│   │   ├── mapper/StockMapper.java
│   │   └── controller/StockController.java
│   ├── sale/
│   │   ├── entity/Sale.java
│   │   ├── dto/SaleDTO.java
│   │   ├── repository/SaleRepository.java
│   │   ├── service/SaleService.java
│   │   ├── mapper/SaleMapper.java
│   │   └── controller/SaleController.java
│   ├── supplier/
│   │   ├── entity/Supplier.java
│   │   ├── dto/SupplierDTO.java
│   │   ├── repository/SupplierRepository.java
│   │   ├── service/SupplierService.java
│   │   ├── mapper/SupplierMapper.java
│   │   └── controller/SupplierController.java
│   └── analytics/
│       ├── dto/
│       │   ├── SalesAnalyticsDTO.java
│       │   └── InventoryAnalyticsDTO.java
│       ├── service/AnalyticsService.java
│       └── controller/AnalyticsController.java
├── src/main/resources/
│   └── application.yml                  # Application configuration
├── pom.xml                              # Maven dependencies
├── Dockerfile                           # Docker image configuration
├── docker-compose.yml                   # Docker Compose orchestration
└── README.md                            # This file
```

## Prerequisites

- **JDK 21** or higher (JDK 25 compatible)
- **Maven 3.8+**
- **Docker** and **Docker Compose** (for containerized deployment)
- **Git** (for version control)

## Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd sprting_first
```

### 2. Build with Maven

```bash
# Clean and build the project
mvn clean install

# Or skip tests for faster build
mvn clean install -DskipTests
```

### 3. Create JAR File
```bash
mvn package
```

The compiled JAR will be available at:
```
target/supply-chain-management-1.0.0.jar
```

## Running the Application

### Option 1: Docker Compose (Recommended)

```bash
# Navigate to project directory
cd sprting_first

# Build and start services
docker compose up --build

# Or run in background
docker compose up -d --build

# Check logs
docker compose logs -f app

# Stop services
docker compose down
```

The application will be available at: `http://localhost:8080/api`

MySQL will be running on: `localhost:3306`

### Option 2: Local Development

#### Prerequisites:
- Install and run MySQL locally
- Create database: `supply_chain_db`

```bash
# Set environment variables (Windows PowerShell)
$env:SPRING_DATASOURCE_URL="jdbc:mysql://localhost:3306/supply_chain_db"
$env:SPRING_DATASOURCE_USERNAME="user"
$env:SPRING_DATASOURCE_PASSWORD="pass"

# Run Spring Boot application
mvn spring-boot:run
```

Or directly run the JAR:
```bash
java -jar target/supply-chain-management-1.0.0.jar
```

## Database Configuration

### MySQL Connection Details
- **Host**: `mysql` (Docker) or `localhost` (Local)
- **Port**: `3306`
- **Database**: `supply_chain_db`
- **Username**: `user`
- **Password**: `pass`

### Automatic Schema Creation
The application uses `spring.jpa.hibernate.ddl-auto=update` to automatically create tables on startup.

## Authentication & Authorization

### Default Users
The system uses JWT token-based authentication. First, register or login:

```bash
# Register new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password123"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "password123"
  }'
```

### JWT Token Usage
Include the token in request headers:
```bash
curl -H "Authorization: Bearer <your-jwt-token>" \
  http://localhost:8080/api/products
```

### User Roles
- **ADMIN**: Full access to all endpoints, can delete resources
- **MANAGER**: Can create, read, update resources; access analytics
- **USER**: Read-only access to most endpoints

## API Endpoints

### Swagger Documentation
Access interactive API documentation:
```
http://localhost:8080/api/swagger-ui.html
```

### Authentication Endpoints
```
POST   /api/auth/login              - Login and get JWT token
POST   /api/auth/register           - Register new user
```

### Product Endpoints
```
POST   /api/products                - Create product (MANAGER, ADMIN)
GET    /api/products                - Get all products (USER, MANAGER, ADMIN)
GET    /api/products/{id}           - Get product by ID
GET    /api/products/code/{code}    - Get product by code
GET    /api/products/category/{category} - Get products by category
GET    /api/products/active/all     - Get active products
PUT    /api/products/{id}           - Update product (MANAGER, ADMIN)
DELETE /api/products/{id}           - Delete product (ADMIN)
```

### Stock Endpoints
```
POST   /api/stocks                  - Create stock entry
GET    /api/stocks                  - Get all stocks
GET    /api/stocks/{id}             - Get stock by ID
GET    /api/stocks/product/{productId} - Get stock by product
GET    /api/stocks/low-stock/all    - Get low stock items (MANAGER, ADMIN)
PUT    /api/stocks/{id}             - Update stock
DELETE /api/stocks/{id}             - Delete stock
```

### Sale Endpoints
```
POST   /api/sales                   - Create sales order
GET    /api/sales                   - Get all sales
GET    /api/sales/{id}              - Get sale by ID
GET    /api/sales/order/{orderNumber} - Get sale by order number
GET    /api/sales/status/{status}   - Get sales by status
GET    /api/sales/product/{productId} - Get sales by product
PUT    /api/sales/{id}              - Update sale
DELETE /api/sales/{id}              - Delete sale
```

### Supplier Endpoints
```
POST   /api/suppliers               - Create supplier
GET    /api/suppliers               - Get all suppliers
GET    /api/suppliers/{id}          - Get supplier by ID
GET    /api/suppliers/code/{code}   - Get supplier by code
GET    /api/suppliers/email/{email} - Get supplier by email
GET    /api/suppliers/active/all    - Get active suppliers
PUT    /api/suppliers/{id}          - Update supplier
DELETE /api/suppliers/{id}          - Delete supplier
```

### Analytics Endpoints
```
GET    /api/analytics/sales         - Get sales analytics (MANAGER, ADMIN)
GET    /api/analytics/inventory     - Get inventory analytics (MANAGER, ADMIN)
GET    /api/analytics/dashboard     - Get dashboard summary (MANAGER, ADMIN)
```

## Sample API Requests

### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "manager1",
    "password": "securepassword123"
  }'
```

### 2. Login and Get JWT Token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "manager1",
    "password": "securepassword123"
  }'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "username": "manager1",
  "role": "USER"
}
```

### 3. Create a Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <jwt-token>" \
  -d '{
    "productCode": "PROD001",
    "productName": "Laptop",
    "description": "High performance laptop",
    "category": "Electronics",
    "unitPrice": 999.99,
    "isActive": true
  }'
```

### 4. Get All Products
```bash
curl -X GET http://localhost:8080/api/products \
  -H "Authorization: Bearer <jwt-token>"
```

### 5. Create a Stock Entry
```bash
curl -X POST http://localhost:8080/api/stocks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <jwt-token>" \
  -d '{
    "productId": 1,
    "quantity": 50,
    "minimumLevel": 10,
    "maximumLevel": 200,
    "warehouseLocation": "Warehouse-A-101"
  }'
```

### 6. Create a Sales Order
```bash
curl -X POST http://localhost:8080/api/sales \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <jwt-token>" \
  -d '{
    "saleOrderNumber": "SO001",
    "productId": 1,
    "quantity": 5,
    "unitPrice": 999.99,
    "totalAmount": 4999.95,
    "status": "PENDING",
    "customerName": "John Doe",
    "deliveryAddress": "123 Main St, City, State"
  }'
```

### 7. Get Analytics Dashboard
```bash
curl -X GET http://localhost:8080/api/analytics/dashboard \
  -H "Authorization: Bearer <jwt-token>"
```

## Configuration

### application.yml Configuration

Key configuration properties:

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql:3306/supply_chain_db  # Database URL
    username: user                                  # Database username
    password: pass                                  # Database password
  jpa:
    hibernate:
      ddl-auto: update                             # Auto schema creation
    show-sql: false                                # SQL logging

jwt:
  secret: "your-secret-key-..."                   # JWT secret (change in production)
  expiration: 86400000                            # Token expiration (24 hours)

server:
  port: 8080                                      # Application port
  servlet:
    context-path: /api                            # Base API path
```

## Logging

### Logging Configuration

The application uses SLF4J with Logback for logging:

```yaml
logging:
  level:
    root: INFO
    com.example.supplychain: DEBUG
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n"
```

### Accessing Logs

**Docker Compose:**
```bash
docker compose logs -f app
```

**Local Development:**
Logs are printed to console. You can also configure file logging in `application.yml`.

## Docker Deployment

### Docker Compose Services

The `docker-compose.yml` includes:

1. **MySQL Service**
   - Image: `mysql:8.0`
   - Database: `supply_chain_db`
   - Username: `user`
   - Password: `pass`
   - Volume: `mysql-data` (persistent storage)

2. **Spring Boot Application**
   - Built from `Dockerfile`
   - Depends on MySQL service
   - Exposes port `8080`
   - Uses shared network `supply-chain-network`

### Docker Commands

```bash
# Build and start services
docker compose up --build

# Run in background
docker compose up -d

# View logs
docker compose logs -f

# View specific service logs
docker compose logs -f app

# Stop services
docker compose down

# Remove volumes (data will be deleted)
docker compose down -v

# Rebuild specific service
docker compose up --build --no-deps app
```

## Development Guidelines

### Code Structure

- **Entity Classes**: Located in `*/entity/` - JPA entities
- **DTOs**: Located in `*/dto/` - Data Transfer Objects with validation
- **Repositories**: Located in `*/repository/` - Spring Data JPA interfaces
- **Services**: Located in `*/service/` - Business logic layer
- **Controllers**: Located in `*/controller/` - REST endpoints
- **Mappers**: Located in `*/mapper/` - Entity-DTO mapping

### Adding New Module

To add a new module (e.g., Purchase Orders):

1. Create package structure: `com.example.supplychain.purchaseorder`
2. Create subdirectories: `entity`, `dto`, `repository`, `service`, `mapper`, `controller`
3. Create Entity class with `@Entity` and `@Table`
4. Create DTO with validation annotations
5. Create Repository extending `JpaRepository`
6. Create Mapper using `ModelMapper`
7. Create Service with business logic
8. Create Controller with endpoints and `@PreAuthorize`
9. Add to `SecurityConfig` for endpoint protection

### Validation

All DTOs use Jakarta Validation annotations:
- `@NotNull` - Field must not be null
- `@NotBlank` - String must not be blank
- `@Email` - Valid email format
- `@Min` / `@Max` - Numeric range validation
- `@Size` - String length validation
- `@DecimalMin` / `@DecimalMax` - Decimal range validation

### AOP Logging

The `LoggingAspect` automatically logs:
- Method entry/exit in controllers and services
- Method execution time
- Exception information
- Method parameters and return values

Pointcuts are defined for:
- `com.example.supplychain.*.controller.*`
- `com.example.supplychain.*.service.*`

## Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ProductControllerTest

# Run with coverage
mvn test jacoco:report
```

### Test Coverage
Tests are located in `src/test/java/` with the same package structure as production code.

## Troubleshooting

### Common Issues

**Issue**: Application fails to connect to MySQL
```
Solution: Ensure MySQL is running and credentials in application.yml are correct
- Check Docker Compose: docker compose ps
- Check MySQL container: docker compose logs mysql
```

**Issue**: JWT token validation fails
```
Solution: Ensure JWT secret is properly configured and tokens haven't expired
- Check JWT configuration in application.yml
- Verify Authorization header format: "Bearer <token>"
```

**Issue**: Docker build fails
```
Solution: Ensure JAR file is built before Docker compose up
- Run: mvn clean install -DskipTests
- Then: docker compose up --build
```

**Issue**: Port 8080 already in use
```
Solution: Either stop the process on port 8080 or change port in docker-compose.yml
- PowerShell: Get-NetTcpConnection -LocalPort 8080 | Stop-Process -Force
- Or update docker-compose.yml: ports: - "8081:8080"
```

## Production Deployment Checklist

- [ ] Change JWT secret to a strong, random value
- [ ] Update database credentials
- [ ] Enable HTTPS/SSL
- [ ] Configure CORS appropriately
- [ ] Set up proper logging and monitoring
- [ ] Configure database backups
- [ ] Set up CI/CD pipeline
- [ ] Configure environment-specific properties
- [ ] Review security configurations
- [ ] Load test the application

## Technologies Used

- **Spring Boot 3.3.0** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database access
- **Spring AOP** - Aspect-oriented programming
- **JWT (JSON Web Tokens)** - Token-based authentication
- **Hibernate** - ORM framework
- **MySQL 8.0** - Relational database
- **ModelMapper** - Object mapping
- **Swagger/OpenAPI 3.0** - API documentation
- **Jakarta Validation** - Bean validation
- **Maven** - Build tool
- **Docker & Docker Compose** - Containerization
- **JDK 21** - Java runtime

## API Response Format

### Success Response
```json
{
  "id": 1,
  "productCode": "PROD001",
  "productName": "Laptop",
  "category": "Electronics",
  "unitPrice": 999.99,
  "isActive": true,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Error Response
```json
{
  "status": 400,
  "error": "Validation Failed",
  "message": "Product code is required",
  "path": "/api/products"
}
```

## Contributing

1. Clone the repository
2. Create a feature branch: `git checkout -b feature/new-feature`
3. Commit changes: `git commit -am 'Add new feature'`
4. Push to branch: `git push origin feature/new-feature`
5. Submit a pull request

## License

This project is licensed under the MIT License - see LICENSE file for details.

## Support

For issues, questions, or contributions, please open an issue on the GitHub repository.

---

**Happy coding! 🚀**
