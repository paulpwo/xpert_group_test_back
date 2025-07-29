#!/bin/bash

echo "🔍 Verificando Swagger/OpenAPI"
echo "=============================="

# Esperar a que la aplicación se inicie
echo "⏳ Esperando a que la aplicación se inicie..."
sleep 15

BASE_URL="http://localhost:8080"

echo ""
echo "📋 URLs de Swagger disponibles:"
echo "-------------------------------"

# Verificar Swagger UI
echo "1. Swagger UI: $BASE_URL/swagger-ui/index.html"
curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/swagger-ui/index.html"
if [ $? -eq 0 ]; then
    echo " ✅ Swagger UI está disponible"
else
    echo " ❌ Swagger UI no está disponible"
fi

# Verificar OpenAPI JSON
echo "2. OpenAPI JSON: $BASE_URL/api-docs"
curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/api-docs"
if [ $? -eq 0 ]; then
    echo " ✅ OpenAPI JSON está disponible"
else
    echo " ❌ OpenAPI JSON no está disponible"
fi

# Verificar OpenAPI YAML
echo "3. OpenAPI YAML: $BASE_URL/api-docs.yaml"
curl -s -o /dev/null -w "%{http_code}" "$BASE_URL/api-docs.yaml"
if [ $? -eq 0 ]; then
    echo " ✅ OpenAPI YAML está disponible"
else
    echo " ❌ OpenAPI YAML no está disponible"
fi

echo ""
echo "🌐 URLs de acceso:"
echo "------------------"
echo "• Aplicación: $BASE_URL"
echo "• Swagger UI: $BASE_URL/swagger-ui/index.html"
echo "• OpenAPI JSON: $BASE_URL/api-docs"
echo "• Consola H2: $BASE_URL/h2-console"

echo ""
echo "📝 Para acceder a Swagger UI, abre tu navegador y ve a:"
echo "   http://localhost:8080/swagger-ui/index.html"