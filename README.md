# Cat API Backend

Backend desarrollado con Spring Boot que se conecta a The Cat API para obtener información sobre razas de gatos e imágenes.

## Arquitectura

El proyecto sigue los principios de **Clean Architecture** y **SOLID**:

- **Puertos de Entrada (Use Cases)**: Interfaces que definen los casos de uso
- **Puertos de Salida (Repositories)**: Interfaces que definen el acceso a datos
- **Adaptadores de Entrada (Controllers)**: Controladores REST que exponen los endpoints
- **Adaptadores de Salida (Repositories)**: Implementaciones que se conectan a The Cat API
- **Servicios**: Implementación de los casos de uso

## Estructura del Proyecto

```
src/main/java/com/xpertgroup/demo/
├── config/                    # Configuraciones
│   ├── TheCatApiConfig.java   # Configuración de RestTemplate para The Cat API
│   └── OpenApiConfig.java     # Configuración de Swagger/OpenAPI
├── controllers/               # Adaptadores de entrada (REST)
│   ├── CatBreedController.java
│   └── CatImageController.java
├── dtos/                     # Objetos de transferencia de datos
│   ├── CatBreedDto.java
│   └── CatImageDto.java
├── entities/                 # Entidades JPA
│   └── CatBreed.java
├── ports/                    # Puertos (interfaces)
│   ├── in/                   # Puertos de entrada
│   │   ├── CatBreedUseCase.java
│   │   └── CatImageUseCase.java
│   └── out/                  # Puertos de salida
│       ├── CatBreedRepository.java
│       └── CatImageRepository.java
├── services/                 # Servicios (casos de uso)
│   ├── CatBreedService.java
│   └── CatImageService.java
└── adapters/                 # Adaptadores de salida
    └── out/
        ├── TheCatApiBreedRepository.java
        └── TheCatApiImageRepository.java
```

## Endpoints Disponibles

### Controlador de Razas de Gatos (`/breeds`)

1. **GET /breeds** - Obtiene todas las razas de gatos
2. **GET /breeds/{breedId}** - Obtiene una raza específica por ID
3. **GET /breeds/search?q={query}** - Busca razas por nombre

### Controlador de Imágenes (`/images`)

1. **GET /images/breed/{breedId}** - Obtiene imágenes de una raza específica
2. **GET /images/{imageId}** - Obtiene una imagen específica por ID
3. **GET /images/search?q={query}** - Busca imágenes por término

### Controlador de Usuarios (`/api/users`)

1. **GET /api/users/login?email={email}&password={password}** - Autentica un usuario
2. **GET /api/users/register?email={email}&password={password}** - Registra un nuevo usuario

#### Características de Autenticación

- ✅ **Encriptación de contraseñas** usando BCrypt
- ✅ **Validación de usuarios existentes** en registro
- ✅ **Verificación de credenciales** en login

## Documentación de la API

### Swagger UI

La documentación interactiva de la API está disponible en:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/api-docs

### Características de la Documentación

- ✅ **Documentación completa** de todos los endpoints
- ✅ **Ejemplos de uso** para cada parámetro
- ✅ **Códigos de respuesta** documentados
- ✅ **Esquemas de datos** detallados
- ✅ **Interfaz interactiva** para probar endpoints

## Inicialización del Proyecto

### Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

### Pasos para ejecutar

1. **Clonar el repositorio** (si aplica):
   ```bash
   git clone <repository-url>
   cd demo
   ```

2. **Compilar el proyecto**:
   ```bash
   ./mvnw clean compile
   ```

3. **Ejecutar la aplicación**:
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Verificar que la aplicación esté corriendo**:
   - La aplicación se ejecutará en `http://localhost:8080`
   - Puedes acceder a la consola H2 en `http://localhost:8080/h2-console`
   - **Swagger UI** disponible en `http://localhost:8080/swagger-ui/index.html`

### Ejemplos de uso

Una vez que la aplicación esté corriendo, puedes probar los endpoints:

```bash
# Obtener todas las razas de gatos
curl http://localhost:8080/breeds

# Obtener una raza específica
curl http://localhost:8080/breeds/abys

# Buscar razas
curl http://localhost:8080/breeds/search?q=abyssinian

# Obtener imágenes de una raza
curl http://localhost:8080/images/breed/abys

# Obtener una imagen específica
curl http://localhost:8080/images/0XYvRd7oD

# Buscar imágenes
curl http://localhost:8080/images/search?q=cat
```

## Configuración

El proyecto utiliza las siguientes configuraciones en `application.properties`:

- **Puerto**: 8080
- **Base de datos**: H2 en memoria
- **The Cat API**: Configurado con la API key proporcionada
- **Swagger UI**: Disponible en `/swagger-ui/index.html`
- **OpenAPI JSON**: Disponible en `/api-docs`

## Tecnologías Utilizadas

- **Spring Boot**
- **Spring MVC** (para programación síncrona)
- **Spring Security** (para autenticación y encriptación)
- **RestTemplate** (para llamadas HTTP síncronas)
- **H2 Database** (base de datos en memoria)
- **Lombok** (para reducir código boilerplate)
- **SpringDoc OpenAPI** (para documentación de API)

## Principios Aplicados

- **SOLID**: Separación de responsabilidades, inversión de dependencias
- **Clean Architecture**: Separación clara entre capas
- **Synchronous Programming**: Uso de tipos síncronos para simplicidad
- **Dependency Injection**: Inyección de dependencias con Spring
- **Error Handling**: Manejo robusto de errores con try-catch
- **API Documentation**: Documentación completa con OpenAPI 3.0

## URLs de Acceso

Una vez ejecutada la aplicación, puedes acceder a:

- **Aplicación**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/api-docs
- **Consola H2**: http://localhost:8080/h2-console

## Comandos Docker 
# Construir
docker build -t demo-spring-boot .
# Ejecutar
docker run -d --name demo-spring-boot -p 8080:8080 demo-spring-boot

# Con Docker Compose
docker-compose up -d