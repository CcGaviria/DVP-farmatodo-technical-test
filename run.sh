#!/bin/bash

# Colores para la consola
RED="\e[31m"
GREEN="\e[32m"
YELLOW="\e[33m"
BLUE="\e[34m"
CYAN="\e[36m"
BOLD="\e[1m"
RESET="\e[0m"

print_title() {
    echo -e "\n${CYAN}=================================================${RESET}"
    echo -e "${BOLD}$1${RESET}"
    echo -e "${CYAN}=================================================${RESET}\n"
}

command -v docker >/dev/null 2>&1 || { echo -e "${RED}Docker no está instalado. Por favor, instálalo y vuelve a intentarlo.${RESET}"; exit 1; }
command -v docker-compose >/dev/null 2>&1 || { echo -e "${RED}docker-compose no está instalado. Por favor, instálalo y vuelve a intentarlo.${RESET}"; exit 1; }

print_title "             🚀 Double V Partners 🚀"
echo "Prueba técnica para Farmatodo - Desarrollado por: Cristian Gaviria"

print_title "🚀 Iniciando compilación y despliegue..."


# Variables
USER_SERVICE_DIR="farmatodo-user-service"
TICKET_SERVICE_DIR="farmatodo-ticket-service"
USER_SERVICE_PORT=8070
TICKET_SERVICE_PORT=8071

# 1️⃣ Compilando los microservicios
print_title "📦 Compilando $USER_SERVICE_DIR..."
cd $USER_SERVICE_DIR
./mvnw clean package -DskipTests || { echo -e "${RED}❌ Error compilando $USER_SERVICE_DIR${RESET}"; exit 1; }
cd ..

print_title "📦 Compilando $TICKET_SERVICE_DIR..."
cd $TICKET_SERVICE_DIR
./mvnw clean package -DskipTests || { echo -e "${RED}❌ Error compilando $TICKET_SERVICE_DIR${RESET}"; exit 1; }
cd ..

# 2️⃣ Construyendo las imágenes Docker
print_title "🐳 Construyendo imágenes Docker..."
docker-compose build || { echo -e "${RED}❌ Error construyendo imágenes Docker${RESET}"; exit 1; }

# 3️⃣ Levantando los contenedores
print_title "🚢 Levantando contenedores..."
docker-compose up -d || { echo -e "${RED}❌ Error levantando contenedores${RESET}"; exit 1; }

print_title "✅ Microservicios desplegados correctamente"
docker ps

echo -e "\n${GREEN}🚀 Ingresa al swagger del micro de Usuarios"
echo -e "${BLUE}http://localhost:$USER_SERVICE_PORT/swagger-ui/index.html${RESET}"

echo -e "\n${GREEN}🚀 Ingresa al swagger del micro de Tickets"
echo -e "${BLUE}http://localhost:$TICKET_SERVICE_PORT/swagger-ui/index.html${RESET}"