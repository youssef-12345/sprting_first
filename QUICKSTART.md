# Quick Start Guide - Supply Chain Management System

## ⚡ 5-Minute Quick Start

### Prerequisites
- Docker and Docker Compose installed
- Git (optional)

### Step 1: Clone/Navigate to Project
```bash
cd "C:\Users\ytare\OneDrive\Desktop\sprting_first"
```

### Step 2: Build the Project
```bash
mvn clean install -DskipTests
```

### Step 3: Run with Docker Compose
```bash
docker compose up --build -d
```

### Step 4: Wait for Services to Start
```bash
# Check status
docker compose ps

# View logs
docker compose logs -f app
```

### Step 5: Access the Application

**Swagger API Documentation:**
```
http://localhost:8080/api/swagger-ui.html
```

**API Base URL:**
```
http://localhost:8080/api
```

## 🔐 Authentication Flow

### 1. Register a User
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

### 2. Login and Get JWT Token
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "admin",
    "password": "admin123"
  }'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "type": "Bearer",
  "username": "admin",
  "role": "USER"
}
```

### 3. Use Token in Requests
```bash
curl -H "Authorization: Bearer <your-token>" \
  http://localhost:8080/api/products
```

## 🧪 Sample API Calls

### Create a Product
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "productCode": "LAPTOP-001",
    "productName": "Dell Laptop",
    "description": "High performance laptop",
    "category": "Electronics",
    "unitPrice": 1200.00,
    "isActive": true
  }'
```

### Get All Products
```bash
curl -H "Authorization: Bearer <token>" \
  http://localhost:8080/api/products
```

### Create Stock Entry
```bash
curl -X POST http://localhost:8080/api/stocks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "productId": 1,
    "quantity": 100,
    "minimumLevel": 20,
    "maximumLevel": 500,
    "warehouseLocation": "Warehouse-A"
  }'
```

### Create Sales Order
```bash
curl -X POST http://localhost:8080/api/sales \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{
    "saleOrderNumber": "SO-2024-001",
    "productId": 1,
    "quantity": 5,
    "unitPrice": 1200.00,
    "totalAmount": 6000.00,
    "status": "PENDING",
    "customerName": "John Doe",
    "deliveryAddress": "123 Main St"
  }'
```

### Get Analytics Dashboard
```bash
curl -H "Authorization: Bearer <token>" \
  http://localhost:8080/api/analytics/dashboard
```

## 🛑 Stopping the Application

```bash
# Stop services
docker compose down

# Stop and remove volumes
docker compose down -v
```

## 📊 Database Access

### MySQL Connection Details
- **Host**: `localhost`
- **Port**: `3306`
- **Database**: `supply_chain_db`
- **Username**: `user`
- **Password**: `pass`

### Connect Using MySQL Workbench or CLI
```bash
mysql -h localhost -u user -p supply_chain_db
```

## 📁 Key Files

| File | Purpose |
|------|---------|
| `pom.xml` | Maven dependencies and build configuration |
| `docker-compose.yml` | Docker services orchestration |
| `Dockerfile` | Application container image |
| `src/main/resources/application.yml` | Spring Boot configuration |
| `README.md` | Detailed documentation |

## 🔧 Local Development Setup

If you want to run without Docker:

### 1. Install MySQL Locally
- MySQL 8.0 or higher
- Create database: `supply_chain_db`
- User: `user`, Password: `pass`

### 2. Update application.yml
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/supply_chain_db
    username: user
    password: pass
```

### 3. Run Application
```bash
mvn spring-boot:run
```

## 📚 API Modules

### Product Module
- Create/Read/Update/Delete products
- Filter by category
- View active products

### Stock Module
- Manage inventory levels
- Track warehouse locations
- Monitor low stock alerts

### Sales Module
- Process sales orders
- Track order status
- View sales history

### Supplier Module
- Manage supplier information
- Contact management
- Email and phone tracking

### Analytics Module
- Sales analytics
- Inventory reports
- Dashboard summary

## 🚀 Features Summary

✅ **41 REST Endpoints** across 5 modules
✅ **JWT Authentication** with role-based access
✅ **CRUD Operations** for all entities
✅ **Spring Data JPA** with custom queries
✅ **Input Validation** with Jakarta annotations
✅ **Swagger Documentation** for all endpoints
✅ **AOP Logging** for monitoring
✅ **Docker Deployment** ready
✅ **ModelMapper** for DTO mapping
✅ **MySQL Persistence** with Hibernate

## 🆘 Troubleshooting

### Application won't start
```bash
# Check port 8080 is free
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Kill process if needed
Kill-Process -Id <PID> -Force  # Windows PowerShell
```

### MySQL connection fails
```bash
# Verify MySQL is running
docker compose logs mysql

# Check credentials in application.yml
```

### Docker build fails
```bash
# Ensure JAR is built
mvn clean package

# Rebuild image
docker compose up --build
```

## 📖 More Information

- **Full Documentation**: See `README.md`
- **API Documentation**: Visit `http://localhost:8080/api/swagger-ui.html`
- **Database Schema**: Auto-created by Hibernate

---

**Happy coding! 🎉**
