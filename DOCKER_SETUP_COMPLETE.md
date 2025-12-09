# 🚀 Spring Boot Docker Setup - Complete Guide

## Summary of Fixes Applied

### ✅ Corrections Made

1. **docker-compose.yml** - Fixed typo in SQL initialization
   - Changed: `SPRING_SQL_INIT_DATA_LOCATIONS: classpath:datd.sql`
   - To: `SPRING_SQL_INIT_DATA_LOCATIONS: classpath:data.sql`

2. **application.properties** - Fixed incomplete comment
   - Removed malformed comment line (`# d`)
   - Properly formatted SQL initialization section

3. **Dockerfile** - Enhanced security and optimized
   - Added multi-stage build structure (for future improvements)
   - Changed from JDK to JRE for smaller runtime image
   - Added non-root user for security (appuser)
   - Improved signal handling with exec form

4. **Created .dockerignore** - Optimizes Docker build context
   - Excludes unnecessary files (target, .git, .idea, test files, etc.)
   - Reduces Docker build time and image size

5. **Created .devcontainer/devcontainer.json** - VS Code Remote Container setup
   - Configured to use docker-compose services
   - Installs essential Java/Docker extensions
   - Maps ports 8080 (App) and 3306 (MySQL)
   - Runs Maven build on container startup

---

## ✅ Verification Checklist

- [x] MySQL datasource URL correctly points to `mysql:3306`
- [x] Spring SQL init mode set to `always`
- [x] SQL initialization file correctly referenced as `data.sql`
- [x] docker-compose.yml has proper service dependencies
- [x] MySQL health checks configured
- [x] Application waits for MySQL to be ready
- [x] All Spring profiles configured (prod, h2 profiles available)
- [x] JWT configuration present
- [x] Swagger/OpenAPI enabled
- [x] Security configuration in place

---

## 🐳 Running with Docker Compose

### Prerequisites
- Docker Desktop (Windows/Mac) or Docker Engine (Linux)
- docker-compose
- Maven (optional - if building locally first)

### Option 1: Quick Start (Recommended)

```powershell
# On Windows PowerShell
cd c:\Users\ytare\OneDrive\Desktop\sprting_first

# Build and start all services
docker-compose up --build

# Wait for output like:
# supply-chain-app       | Started SupplyChainApplication in 15.234 seconds (JVM running for 16.789)
```

### Option 2: Step by Step

```powershell
# 1. Build Maven project first
mvn clean package -DskipTests

# 2. Build Docker images
docker-compose build

# 3. Start containers in background
docker-compose up -d

# 4. View logs
docker-compose logs -f supply-chain-app

# 5. Check container status
docker-compose ps
```

### Option 3: Using the Setup Script (Linux/Mac)

```bash
chmod +x setup-docker.sh
./setup-docker.sh
```

---

## 📋 Configuration Details

### Database Connection
| Property | Value |
|----------|-------|
| Host | `mysql` (Docker service name) |
| Port | `3306` |
| Database | `supply_chain_db` |
| Username | `user` |
| Password | `pass` |
| Root Password | `root` |

### Application Access
| Service | URL |
|---------|-----|
| Swagger UI | `http://localhost:8080/api/swagger-ui.html` |
| API Docs | `http://localhost:8080/api/v3/api-docs` |
| H2 Console | `http://localhost:8080/api/h2-console` (if using h2 profile) |

### Default Test Users
| Username | Password | Role |
|----------|----------|------|
| admin | password | ADMIN |
| manager | password | MANAGER |
| user1 | password | USER |

*(Passwords are BCrypt hashed in data.sql)*

---

## 🔍 Troubleshooting

### Issue: "Container keeps restarting"
```bash
# Check logs
docker-compose logs supply-chain-app

# Common causes:
# - MySQL not ready yet (increase health check timeout)
# - Port 8080 already in use
# - JAR file not found in target/
```

**Fix:**
```bash
# Rebuild and restart
docker-compose down
docker-compose up --build
```

### Issue: "MySQL connection refused"
```bash
# Wait for MySQL to initialize
docker-compose logs mysql

# Check MySQL is healthy
docker ps  # Look for "healthy" status
```

**Fix:** Increase startup delay in docker-compose or wait for health check to pass.

### Issue: "Spring SQL initialization not running"
```bash
# Verify data.sql exists in resources
ls src/main/resources/data.sql

# Check Spring logs show SQL init
docker-compose logs supply-chain-app | grep -i "init"
```

**Fix:** Rebuild JAR and container if you modified data.sql.

### Issue: "Port 8080 already in use"
```bash
# Change port in docker-compose.yml
# From: "8080:8080"
# To:   "8081:8080"  (maps host:container)

# Or find and stop existing container
docker ps
docker stop <container_id>
```

---

## 📁 File Structure

```
sprting_first/
├── .devcontainer/
│   └── devcontainer.json          ✨ NEW: VS Code remote container config
├── .dockerignore                   ✨ NEW: Docker build optimization
├── Dockerfile                      ✏️ UPDATED: Enhanced with security
├── docker-compose.yml              ✏️ FIXED: Corrected data.sql typo
├── pom.xml                         ✓ Already correct
├── setup-docker.sh                 ✨ NEW: Linux/Mac setup script
├── src/main/
│   ├── java/com/example/supplychain/
│   └── resources/
│       ├── application.properties  ✏️ FIXED: Removed incomplete comment
│       ├── application.yml         ✓ Already correct
│       ├── application-h2.yml      ✓ For H2 profile
│       └── data.sql                ✓ Sample data
└── README.md
```

---

## 🛠️ Additional Commands

### Container Management
```bash
# View running containers
docker-compose ps

# Stop containers (preserve data)
docker-compose stop

# Stop and remove containers
docker-compose down

# Remove everything including volumes
docker-compose down -v

# Rebuild specific service
docker-compose build supply-chain-app

# Restart service
docker-compose restart supply-chain-app
```

### Logs & Debugging
```bash
# All logs
docker-compose logs

# Follow logs (real-time)
docker-compose logs -f

# Specific service logs
docker-compose logs supply-chain-app

# Last 100 lines
docker-compose logs --tail=100

# Since specific time
docker-compose logs --since 10m
```

### Database Access
```bash
# Connect to MySQL container
docker-compose exec mysql mysql -u user -p supply_chain_db

# Run SQL query directly
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SELECT COUNT(*) FROM products;"

# Backup database
docker-compose exec mysql mysqldump -u user -ppass supply_chain_db > backup.sql

# Restore database
docker-compose exec -T mysql mysql -u user -ppass supply_chain_db < backup.sql
```

---

## 📊 Verification Tests

### Test 1: Spring Boot Startup
```bash
# Expected output (last line should show):
# Started SupplyChainApplication in X.XXX seconds (JVM running for Y.YYY)

docker-compose logs supply-chain-app | tail -20
```

### Test 2: API Endpoint
```bash
# Test health/actuator endpoint
curl http://localhost:8080/api/actuator

# Or use Windows PowerShell
Invoke-WebRequest http://localhost:8080/api/swagger-ui.html
```

### Test 3: Database Connection
```bash
# Check if tables were created
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SHOW TABLES;"

# Expected output should show: products, suppliers, stocks, sales, users tables
```

### Test 4: Sample Data
```bash
# Verify data was inserted
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SELECT COUNT(*) as 'Product Count' FROM products;"

# Expected output: Product Count: 8
```

---

## 🔒 Security Notes

1. **JWT Secret** - Change in application.yml before production:
   ```yaml
   jwt:
     secret: "change-this-to-a-strong-256-bit-key"
   ```

2. **Database Credentials** - Update docker-compose.yml and application properties for production

3. **Non-root User** - Dockerfile now runs as non-root `appuser` for better security

4. **MySQL Root Password** - Change `MYSQL_ROOT_PASSWORD: root` in production

---

## 📝 Summary

Your Spring Boot Supply Chain Management application is now properly configured for Docker deployment:

✅ All typos fixed  
✅ Datasource configuration aligned  
✅ SQL initialization corrected  
✅ Docker build optimized  
✅ VS Code devcontainer configured  
✅ Security enhancements added  
✅ Sample data included  

**Ready to run:** `docker-compose up --build`

