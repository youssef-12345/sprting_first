# 🚀 QUICK START GUIDE - Spring Boot Docker

## Issues Fixed

| Issue | File | Fix |
|-------|------|-----|
| **Typo in SQL file reference** | `docker-compose.yml` | `datd.sql` → `data.sql` |
| **Incomplete comment** | `application.properties` | Removed malformed `# d` line |
| **Missing Docker optimization** | `.dockerignore` | ✨ Created |
| **Missing dev environment** | `.devcontainer/devcontainer.json` | ✨ Created |
| **Security & optimization** | `Dockerfile` | Enhanced with JRE, non-root user |

---

## 🎯 Running the Application

### Windows PowerShell
```powershell
cd "c:\Users\ytare\OneDrive\Desktop\sprting_first"
docker-compose up --build
```

### Result
```
[✓] MySQL Container: supply-chain-mysql (listening on 3306)
[✓] Spring Boot App: supply-chain-app (listening on 8080)
[✓] Data initialized with sample records
```

### Access Points
- **Application**: http://localhost:8080/api/swagger-ui.html
- **MySQL**: localhost:3306 (user: `user`, pass: `pass`)
- **Database**: `supply_chain_db`

---

## 🧪 Quick Verification

```bash
# Check containers are running
docker-compose ps

# View application logs
docker-compose logs supply-chain-app

# Test API
curl http://localhost:8080/api/swagger-ui.html

# Count products in database
docker-compose exec mysql mysql -u user -ppass supply_chain_db -e "SELECT COUNT(*) FROM products;"
```

---

## ⛔ Stop the Application

```powershell
# Stop containers (preserve data)
docker-compose stop

# Or stop and remove everything
docker-compose down
```

---

## 🔧 Common Issues & Fixes

| Issue | Solution |
|-------|----------|
| Port 8080 already in use | `docker ps` → `docker stop <id>` |
| MySQL connection refused | Wait 10s for MySQL to initialize |
| JAR not found | Run `mvn clean package -DskipTests` first |
| Containers keep restarting | Check logs: `docker-compose logs` |

---

## 📊 Project Structure Verified

```
✓ pom.xml - Maven dependencies correct
✓ Dockerfile - Optimized multi-stage build
✓ docker-compose.yml - Services configured correctly
✓ application.properties - Fixed SQL init path
✓ application.yml - MySQL datasource correct
✓ data.sql - Sample data included (8 products, 5 suppliers, etc.)
✓ .dockerignore - Build optimization
✓ .devcontainer/ - VS Code integration ready
```

---

## 📌 Important Notes

1. **Default Credentials**
   - Admin: `admin` / `password`
   - Manager: `manager` / `password`
   - User: `user1` / `password`

2. **Profiles Available**
   - `prod` - MySQL (default)
   - `h2` - In-memory H2 (for testing)

3. **Change Profile**
   ```yaml
   # In application.yml
   spring.profiles.active: h2  # or prod
   ```

4. **Update JWT Secret for Production**
   ```yaml
   jwt:
     secret: "use-a-strong-256-bit-key-here"
   ```

---

**✅ All configuration issues have been resolved. Your application is ready to deploy!**

