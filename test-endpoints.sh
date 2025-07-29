#!/bin/bash

echo "🧪 Probando endpoints del Cat API Backend"
echo "=========================================="

# Esperar a que la aplicación se inicie
echo "⏳ Esperando a que la aplicación se inicie..."
sleep 10

BASE_URL="http://localhost:8080"

echo ""
echo "📋 Probando controlador de razas de gatos:"
echo "----------------------------------------"

# Probar GET /breeds
echo "1. Probando GET /breeds..."
curl -s -X GET "$BASE_URL/breeds" | head -c 200
echo ""

# Probar GET /breeds/{breedId}
echo "2. Probando GET /breeds/abys..."
curl -s -X GET "$BASE_URL/breeds/abys" | head -c 200
echo ""

# Probar GET /breeds/search
echo "3. Probando GET /breeds/search?q=abyssinian..."
curl -s -X GET "$BASE_URL/breeds/search?q=abyssinian" | head -c 200
echo ""

echo ""
echo "🖼️  Probando controlador de imágenes:"
echo "------------------------------------"

# Probar GET /images/breed/{breedId}
echo "1. Probando GET /images/breed/abys..."
curl -s -X GET "$BASE_URL/images/breed/abys" | head -c 200
echo ""

# Probar GET /images/search
echo "2. Probando GET /images/search?q=cat..."
curl -s -X GET "$BASE_URL/images/search?q=cat" | head -c 200
echo ""

echo ""
echo "✅ Pruebas completadas!"
echo "La aplicación está corriendo en: http://localhost:8080"
echo "Consola H2 disponible en: http://localhost:8080/h2-console"