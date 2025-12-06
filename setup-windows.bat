@echo off
REM Supply Chain Management System - Windows Setup Script
REM This script helps set up the application locally on Windows

echo.
echo ============================================
echo Supply Chain Management System - Setup
echo ============================================
echo.

REM Check Java
echo [1/4] Checking Java installation...
java -version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install JDK 21 from https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
) else (
    echo ✓ Java found
)

echo.

REM Check Maven
echo [2/4] Checking Maven installation...
mvn --version >nul 2>&1
if errorlevel 1 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven from https://maven.apache.org/download.cgi
    pause
    exit /b 1
) else (
    echo ✓ Maven found
)

echo.

REM Check MySQL
echo [3/4] Checking MySQL installation...
mysql --version >nul 2>&1
if errorlevel 1 (
    echo WARNING: MySQL is not found in PATH
    echo You'll need to manually create the database or install MySQL
    echo Download from: https://www.mysql.com/downloads/mysql/
    set SKIP_DB=1
) else (
    echo ✓ MySQL found
    set SKIP_DB=0
)

echo.

REM Build project
echo [4/4] Building project with Maven...
echo This may take a few minutes...
call mvn clean install -DskipTests
if errorlevel 1 (
    echo ERROR: Maven build failed
    pause
    exit /b 1
)

echo.
echo ============================================
echo Build successful!
echo ============================================
echo.

if "%SKIP_DB%"=="1" (
    echo Manual database setup required:
    echo.
    echo 1. Install MySQL 8.0 from https://www.mysql.com/downloads/mysql/
    echo 2. Start MySQL service
    echo 3. Run these commands in MySQL:
    echo.
    echo    CREATE DATABASE supply_chain_db;
    echo    CREATE USER 'user'^@'localhost' IDENTIFIED BY 'pass';
    echo    GRANT ALL PRIVILEGES ON supply_chain_db.* TO 'user'^@'localhost';
    echo    FLUSH PRIVILEGES;
    echo.
    echo 4. Then run: mvn spring-boot:run
    echo.
) else (
    echo MySQL is installed. Setting up database...
    REM Note: This is a simplified example - actual implementation would need MySQL password
    echo Database setup skipped (manual verification recommended)
)

echo.
echo To start the application:
echo   mvn spring-boot:run
echo.
echo Then access:
echo   API: http://localhost:8080/api
echo   Swagger: http://localhost:8080/api/swagger-ui.html
echo.
echo For Docker setup, ensure Docker Desktop is running and use:
echo   docker compose up --build
echo.

pause
