#!/bin/bash
# setup-docker.sh - Setup script for Docker build and run

set -e

echo "================================"
echo "Spring Boot Docker Setup"
echo "================================"

# Colors for output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo -e "${RED}✗ Docker is not installed${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Docker found${NC}"

# Check if docker-compose is installed
if ! command -v docker-compose &> /dev/null; then
    echo -e "${RED}✗ docker-compose is not installed${NC}"
    exit 1
fi

echo -e "${GREEN}✓ docker-compose found${NC}"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo -e "${RED}✗ Maven is not installed${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Maven found${NC}"

echo -e "\n${YELLOW}Building the Maven project...${NC}"
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Maven build failed${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Maven build successful${NC}"

echo -e "\n${YELLOW}Building Docker images...${NC}"
docker-compose build

if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Docker build failed${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Docker build successful${NC}"

echo -e "\n${YELLOW}Starting containers...${NC}"
docker-compose up -d

if [ $? -ne 0 ]; then
    echo -e "${RED}✗ Docker containers failed to start${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Containers started successfully${NC}"

echo -e "\n${YELLOW}Waiting for MySQL to be ready...${NC}"
sleep 10

echo -e "\n${GREEN}================================"
echo "Setup Complete!"
echo "================================${NC}"
echo "Application URL: http://localhost:8080/api/swagger-ui.html"
echo "MySQL: localhost:3306"
echo "Database: supply_chain_db"
echo "Username: user / Password: pass"
echo ""
echo "To stop containers: docker-compose down"
echo "To view logs: docker-compose logs -f"
