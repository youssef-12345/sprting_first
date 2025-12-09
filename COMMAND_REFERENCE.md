# 🎯 COMMAND REFERENCE - Spring Boot Docker Operations

## Quick Start Commands

### 🚀 Launch Application
```powershell
# Navigate to project
cd "c:\Users\ytare\OneDrive\Desktop\sprting_first"

# Build and start everything
docker-compose up --build

# Expected output (last line):
# supply-chain-app | Started SupplyChainApplication in X.XXX seconds
```

### 🛑 Stop Application
```bash
# Keep containers (preserve database)
docker-compose stop

# Remove containers (keep volumes)
docker-compose down

# Complete cleanup (delete database)
docker-compose down -v
```

---

## 📊 Container Management

### View Container Status
```bash
# List all containers for this project
docker-compose ps

# Detailed view
docker ps -a

# Show only running containers
docker ps
```

### Container Logs
```bash
# All services logs (combined)
docker-compose logs

# Follow logs in real-time
docker-compose logs -f

# Spring Boot app logs only
docker-compose logs -f supply-chain-app

# MySQL logs only
docker-compose logs -f mysql

# Last 50 lines
docker-compose logs --tail=50

# Logs from last 10 minutes
docker-compose logs --since 10m

# Logs since specific timestamp
docker-compose logs --since 2024-12-07T10:00:00
```

### Container Restart
```bash
# Restart all services
docker-compose restart

# Restart specific service
docker-compose restart supply-chain-app
docker-compose restart mysql

# Rebuild specific service
docker-compose up -d --build supply-chain-app
```

---

## 🗄️ Database Operations

### Connect to MySQL
```bash
# Interactive MySQL shell
docker-compose exec mysql mysql -u user -p supply_chain_db
# (Password: pass)

# Or non-interactive
docker-compose exec mysql mysql -u user -ppass supply_chain_db

# Connect as root
docker-compose exec mysql mysql -u root -proot supply_chain_db
```

### Database Queries
```bash
# Show all tables
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SHOW TABLES;"

# Count records in each table
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "
SELECT COUNT(*) as Users FROM users;
SELECT COUNT(*) as Products FROM products;
SELECT COUNT(*) as Suppliers FROM suppliers;
SELECT COUNT(*) as Stocks FROM stocks;
SELECT COUNT(*) as Sales FROM sales;
"

# List all users
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SELECT username, role FROM users;"

# List all products
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SELECT product_code, product_name, unit_price FROM products;"

# View specific table structure
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "DESCRIBE products;"
```

### Database Backup & Restore
```bash
# Backup database to file
docker-compose exec mysql mysqldump -u user -ppass supply_chain_db > backup.sql

# Backup with timestamp
docker-compose exec mysql mysqldump -u user -ppass supply_chain_db > backup_$(date +%Y%m%d_%H%M%S).sql

# Restore from backup
docker-compose exec -T mysql mysql -u user -ppass supply_chain_db < backup.sql

# Backup all databases
docker-compose exec mysql mysqldump -u root -proot --all-databases > full_backup.sql
```

### Database Reset
```bash
# Delete all containers and volumes (WARNING: Data loss!)
docker-compose down -v

# Rebuild and restart (data will be fresh)
docker-compose up --build
```

---

## 🧪 API Testing

### Test API Endpoints
```bash
# Test Spring Boot health (may require endpoint configuration)
curl http://localhost:8080/api/actuator/health

# Access Swagger UI
curl http://localhost:8080/api/swagger-ui.html

# Get API documentation
curl http://localhost:8080/api/v3/api-docs | jq .

# Test authentication (if endpoints require it)
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"password"}'
```

### PowerShell Equivalents
```powershell
# Test with Invoke-WebRequest
Invoke-WebRequest http://localhost:8080/api/swagger-ui.html

# Get response headers
(Invoke-WebRequest http://localhost:8080/api/actuator/health).Headers

# Check if service is responding
$response = Invoke-WebRequest http://localhost:8080/api/swagger-ui.html -UseBasicParsing
$response.StatusCode  # Should show 200
```

---

## 🔍 Troubleshooting Commands

### Check Service Health
```bash
# Show container status
docker-compose ps

# Check MySQL health
docker-compose exec mysql mysqladmin ping -u root -proot

# Check app is running
curl -s http://localhost:8080/api/swagger-ui.html | head -20

# Show network connectivity
docker-compose exec supply-chain-app ping mysql
docker-compose exec supply-chain-app ping -c 1 mysql
```

### Detailed Logs
```bash
# Full error output
docker-compose logs supply-chain-app 2>&1 | tail -100

# Search for errors in logs
docker-compose logs | grep -i error
docker-compose logs | grep -i exception
docker-compose logs | grep -i "failed"

# Show startup time
docker-compose logs supply-chain-app | grep "Started SupplyChainApplication"

# Show MySQL initialization
docker-compose logs mysql | grep -i "init\|ready\|ready for connections"
```

### Container Shell Access
```bash
# Access Spring Boot container shell
docker-compose exec supply-chain-app /bin/sh

# Access MySQL container shell
docker-compose exec mysql /bin/bash

# Run commands inside container
docker-compose exec supply-chain-app ls -la /app
docker-compose exec mysql ls -la /var/lib/mysql
```

### Resource Usage
```bash
# Check memory/CPU usage
docker stats

# Show image sizes
docker images | grep supply-chain

# Inspect container details
docker inspect supply-chain-app
docker inspect supply-chain-mysql

# Show volume details
docker volume ls
docker volume inspect supply-chain_mysql-data
```

---

## 🏗️ Build Operations

### Maven Commands
```bash
# Clean build
mvn clean package -DskipTests

# Build with tests
mvn clean package

# Skip build and just compile
mvn compile

# Check dependencies
mvn dependency:tree

# Update dependencies
mvn versions:display-dependency-updates
```

### Docker Build
```bash
# Build images without running
docker-compose build

# Build specific service
docker-compose build supply-chain-app
docker-compose build mysql

# Build with no cache
docker-compose build --no-cache

# Build with verbose output
docker-compose build --verbose
```

---

## 🧹 Cleanup Operations

### Remove Containers
```bash
# Stop and remove all containers
docker-compose down

# Remove exited containers only
docker container prune -f

# Stop but don't remove
docker-compose stop
```

### Remove Images
```bash
# Remove unused images
docker image prune -f

# Remove specific image
docker rmi supply-chain-management:latest

# Remove dangling images
docker image prune -a -f
```

### Remove Volumes
```bash
# List volumes
docker volume ls

# Remove specific volume
docker volume rm supply-chain_mysql-data

# Remove all unused volumes
docker volume prune -f
```

### Complete Reset
```bash
# Nuclear option - removes everything for this project
docker-compose down -v

# Plus remove images
docker-compose down -v --rmi all

# Plus remove unused images globally
docker system prune -a -f
```

---

## 📈 Performance Monitoring

### View Real-time Stats
```bash
# All containers
docker stats

# Specific container
docker stats supply-chain-app
docker stats supply-chain-mysql

# With formatting
docker stats --format "table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}"
```

### Historical Performance
```bash
# Check log sizes
du -sh $(docker inspect -f='{{.LogPath}}' supply-chain-app)
du -sh $(docker inspect -f='{{.LogPath}}' supply-chain-mysql)

# Clean old logs (careful!)
docker logs --tail 0 -f supply-chain-app > /dev/null
```

---

## 🔐 Security Operations

### Change Credentials
1. Stop containers:
   ```bash
   docker-compose down
   ```

2. Update `docker-compose.yml`:
   ```yaml
   environment:
     MYSQL_PASSWORD: new-secure-password
     SPRING_DATASOURCE_PASSWORD: new-secure-password
   ```

3. Restart:
   ```bash
   docker-compose up --build
   ```

### View Running Processes
```bash
# Inside container
docker-compose exec supply-chain-app ps aux
docker-compose exec mysql ps aux

# Check ports
docker-compose exec supply-chain-app netstat -tlnp
```

---

## 📝 Common Workflow

### Daily Development Workflow
```bash
# Start of day
docker-compose up -d

# Check logs while working
docker-compose logs -f supply-chain-app

# Make code changes
# ... edit files ...

# Test changes
docker-compose restart supply-chain-app
docker-compose logs -f supply-chain-app

# End of day
docker-compose stop
```

### Before Deployment
```bash
# Full rebuild
docker-compose down -v
mvn clean package -DskipTests
docker-compose up --build

# Verify all tests pass
mvn test

# Check logs for errors
docker-compose logs supply-chain-app | grep -i error
```

### After Deployment
```bash
# Verify application is healthy
curl http://localhost:8080/api/swagger-ui.html

# Check database has data
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SELECT COUNT(*) FROM products;"

# Monitor for errors
docker-compose logs -f
```

---

## 🆘 Quick Reference

| Task | Command |
|------|---------|
| Start app | `docker-compose up --build` |
| Stop app | `docker-compose down` |
| View logs | `docker-compose logs -f` |
| Access MySQL | `docker-compose exec mysql mysql -u user -ppass supply_chain_db` |
| Rebuild | `docker-compose build --no-cache` |
| Reset everything | `docker-compose down -v && docker-compose up --build` |
| Check health | `docker-compose ps` |
| Delete all | `docker system prune -a -f` |

