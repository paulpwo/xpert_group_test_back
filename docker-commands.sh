#!/bin/bash

# Script con comandos útiles para Docker

echo "=== Comandos Docker para la aplicación Spring Boot ==="
echo ""

case "$1" in
    "build")
        echo "Construyendo imagen Docker..."
        docker build -t demo-spring-boot .
        ;;
    "run")
        echo "Ejecutando contenedor..."
        docker run -d --name demo-spring-boot -p 8080:8080 demo-spring-boot
        ;;
    "compose")
        echo "Ejecutando con Docker Compose..."
        docker-compose up -d
        ;;
    "stop")
        echo "Deteniendo contenedor..."
        docker stop demo-spring-boot
        docker rm demo-spring-boot
        ;;
    "compose-stop")
        echo "Deteniendo Docker Compose..."
        docker-compose down
        ;;
    "logs")
        echo "Mostrando logs..."
        docker logs -f demo-spring-boot
        ;;
    "compose-logs")
        echo "Mostrando logs de Docker Compose..."
        docker-compose logs -f
        ;;
    "clean")
        echo "Limpiando contenedores e imágenes..."
        docker stop demo-spring-boot 2>/dev/null || true
        docker rm demo-spring-boot 2>/dev/null || true
        docker rmi demo-spring-boot 2>/dev/null || true
        docker-compose down 2>/dev/null || true
        ;;
    *)
        echo "Uso: $0 {build|run|compose|stop|compose-stop|logs|compose-logs|clean}"
        echo ""
        echo "Comandos disponibles:"
        echo "  build         - Construir imagen Docker"
        echo "  run           - Ejecutar contenedor"
        echo "  compose       - Ejecutar con Docker Compose"
        echo "  stop          - Detener contenedor"
        echo "  compose-stop  - Detener Docker Compose"
        echo "  logs          - Ver logs del contenedor"
        echo "  compose-logs  - Ver logs de Docker Compose"
        echo "  clean         - Limpiar contenedores e imágenes"
        ;;
esac