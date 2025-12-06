# Docker Troubleshooting & Local Setup Guide

## Problem Identified
Docker Desktop daemon is not running on your system.

## Solutions

### Option 1: Start Docker Desktop (Recommended if you have it installed)

1. **Open Docker Desktop**
   - Press Windows key and search for "Docker"
   - Click "Docker Desktop" to launch
   - Wait 2-3 minutes for the daemon to start

2. **Verify Docker is running**
   ```powershell
   docker ps
   ```
   Should show: `CONTAINER ID   IMAGE   COMMAND   CREATED   STATUS   PORTS   NAMES`

3. **Then run Docker Compose**
   ```powershell
   docker compose up --build -d
   ```

### Option 2: Run Locally Without Docker (Fastest Alternative)

If Docker Desktop isn't available or you want to test immediately, run locally:

#### Prerequisites (Windows)
1. **Install MySQL 8.0** (if not already installed)
2. **Update application.yml** to point to local MySQL

#### Step 1: Verify MySQL is Running

```powershell
# Check if MySQL service is running
Get-Service | Where-Object {$_.Name -like '*mysql*'} | Select-Object Name, Status
```

If not running, start it:
```powershell
# Start MySQL service
Start-Service -Name MySQL80  # or your MySQL service name
```

#### Step 2: Create Database & User

```bash
# Connect to MySQL
mysql -u root -p

# Execute these commands:
CREATE DATABASE supply_chain_db;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON supply_chain_db.* TO 'user'@'localhost';
FLUSH PRIVILEGES;
EXIT;
```

#### Step 3: Update application.yml

Edit `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/supply_chain_db
    username: user
    password: pass
```

#### Step 4: Run the Application

```powershell
cd "C:\Users\ytare\OneDrive\Desktop\sprting_first"
mvn spring-boot:run
```

**Application will be available at**: http://localhost:8080/api

### Option 3: Install Docker Desktop

If you want to use Docker but don't have it installed:

1. Download: https://www.docker.com/products/docker-desktop
2. Install the MSI file
3. Restart Windows
4. Open Docker Desktop and wait for it to initialize
5. Run `docker compose up --build -d`

---

## Next Steps

**Choose the option that works best for you:**

- **Option 1** (Docker): Recommended for production-like setup
- **Option 2** (Local): Fastest way to test immediately
- **Option 3** (Install Docker): For long-term Docker development

---

## Common Issues After Starting

### Port 8080 Already In Use
```powershell
# Find and stop the process
Get-NetTcpConnection -LocalPort 8080 | Stop-Process -Force
```

### MySQL Connection Refused
```powershell
# Verify MySQL is running
Start-Service -Name MySQL80
```

### Java Not Found
```powershell
# Verify Java is installed
java -version
```

---

## Testing the Application

Once running (either via Docker or locally):

### 1. Register User
```bash
curl -X POST http://localhost:8080/api/auth/register ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
```

### 2. Login
```bash
curl -X POST http://localhost:8080/api/auth/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"admin\",\"password\":\"admin123\"}"
```

### 3. Access Swagger UI
```
http://localhost:8080/api/swagger-ui.html
```

---

## Additional Help

For more detailed information, see:
- **README.md** - Complete documentation
- **QUICKSTART.md** - Quick setup guide
- **INDEX.md** - Documentation index
