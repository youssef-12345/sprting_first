#!/usr/bin/env powershell
<#
.SYNOPSIS
    Supply Chain Management System - Windows Setup Script (PowerShell)
.DESCRIPTION
    Automates the setup and build of the Supply Chain Management System on Windows
.EXAMPLE
    .\setup-windows.ps1
#>

# Color codes for output
function Write-Success {
    param([string]$Message)
    Write-Host "✓ $Message" -ForegroundColor Green
}

function Write-Error2 {
    param([string]$Message)
    Write-Host "✗ $Message" -ForegroundColor Red
}

function Write-Info {
    param([string]$Message)
    Write-Host "ℹ $Message" -ForegroundColor Cyan
}

function Write-Header {
    param([string]$Message)
    Write-Host "`n============================================" -ForegroundColor White
    Write-Host $Message -ForegroundColor White
    Write-Host "============================================`n" -ForegroundColor White
}

# Main Setup
Write-Header "Supply Chain Management System - Setup"

# Check Java
Write-Info "[1/4] Checking Java installation..."
try {
    $javaVersion = java -version 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Java found"
        Write-Host "      $($javaVersion[0])" -ForegroundColor Gray
    }
} catch {
    Write-Error2 "Java is not installed or not in PATH"
    Write-Host "Please install JDK 21 from https://www.oracle.com/java/technologies/downloads/" -ForegroundColor Yellow
    exit 1
}

# Check Maven
Write-Info "[2/4] Checking Maven installation..."
try {
    $mavenVersion = mvn --version 2>&1 | Select-Object -First 1
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Maven found"
    }
} catch {
    Write-Error2 "Maven is not installed or not in PATH"
    Write-Host "Please install Maven from https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    exit 1
}

# Check MySQL
Write-Info "[3/4] Checking MySQL installation..."
$skipDb = $true
try {
    $mysqlVersion = mysql --version 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Success "MySQL found"
        $skipDb = $false
    }
} catch {
    Write-Host "⚠ MySQL not found in PATH (optional for local development)" -ForegroundColor Yellow
}

# Build Project
Write-Info "[4/4] Building project with Maven..."
Write-Host "This may take a few minutes..." -ForegroundColor Gray
Write-Host ""

$buildOutput = mvn clean install -DskipTests 2>&1
if ($LASTEXITCODE -ne 0) {
    Write-Error2 "Maven build failed"
    Write-Host $buildOutput
    exit 1
}

# Check if build succeeded
if (Test-Path "target\supply-chain-management-1.0.0.jar") {
    Write-Success "Build completed successfully!"
    Write-Host "JAR file: target\supply-chain-management-1.0.0.jar" -ForegroundColor Gray
} else {
    Write-Error2 "Build failed - JAR file not found"
    exit 1
}

Write-Header "Setup Complete!"

# Display instructions
Write-Host "Choose your setup option:`n" -ForegroundColor White

Write-Host "Option 1: Run Locally (Fastest for Testing)" -ForegroundColor Cyan
Write-Host "  Prerequisites:" -ForegroundColor Gray
Write-Host "    - MySQL 8.0 running locally (or WSL MySQL)" -ForegroundColor Gray
Write-Host "  Command:" -ForegroundColor Gray
Write-Host "    mvn spring-boot:run" -ForegroundColor Yellow
Write-Host ""

Write-Host "Option 2: Run with Docker Compose (Production-like)" -ForegroundColor Cyan
Write-Host "  Prerequisites:" -ForegroundColor Gray
Write-Host "    - Docker Desktop installed and running" -ForegroundColor Gray
Write-Host "  Command:" -ForegroundColor Gray
Write-Host "    docker compose up --build -d" -ForegroundColor Yellow
Write-Host ""

Write-Host "Option 3: Run Docker Compose in WSL (Recommended)" -ForegroundColor Cyan
Write-Host "  Prerequisites:" -ForegroundColor Gray
Write-Host "    - Windows Subsystem for Linux (WSL2)" -ForegroundColor Gray
Write-Host "    - Docker installed in WSL" -ForegroundColor Gray
Write-Host "  Commands:" -ForegroundColor Gray
Write-Host "    wsl" -ForegroundColor Yellow
Write-Host "    cd /mnt/c/Users/ytare/OneDrive/Desktop/sprting_first" -ForegroundColor Yellow
Write-Host "    docker compose up --build -d" -ForegroundColor Yellow
Write-Host ""

Write-Host "Access the application at:" -ForegroundColor White
Write-Host "  API:      http://localhost:8080/api" -ForegroundColor Green
Write-Host "  Swagger:  http://localhost:8080/api/swagger-ui.html" -ForegroundColor Green
Write-Host ""

if ($skipDb) {
    Write-Host "MongoDB/MySQL Setup Required:" -ForegroundColor Yellow
    Write-Host "  1. Install MySQL 8.0: https://www.mysql.com/downloads/mysql/" -ForegroundColor Gray
    Write-Host "  2. Create database:" -ForegroundColor Gray
    Write-Host "     mysql -u root -p" -ForegroundColor Gray
    Write-Host "     CREATE DATABASE supply_chain_db;" -ForegroundColor Gray
    Write-Host "     CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass';" -ForegroundColor Gray
    Write-Host "     GRANT ALL PRIVILEGES ON supply_chain_db.* TO 'user'@'localhost';" -ForegroundColor Gray
    Write-Host "     FLUSH PRIVILEGES;" -ForegroundColor Gray
    Write-Host ""
}

Write-Info "For more information, see:"
Write-Host "  - README.md (Detailed documentation)" -ForegroundColor Gray
Write-Host "  - QUICKSTART.md (5-minute setup)" -ForegroundColor Gray
Write-Host "  - DOCKER_TROUBLESHOOTING.md (Docker issues)" -ForegroundColor Gray
Write-Host ""

Write-Host "Setup complete! You're ready to go. 🚀" -ForegroundColor Green
