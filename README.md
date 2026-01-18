# OBJETIVO DEL PROYECTO
Este proyecto tiene como objetivo mostrar de forma clara y práctica cómo integrar Redis en una aplicación moderna con Spring Boot (Java 21), utilizando tres patrones reales y muy comunes en sistemas productivos:

Redis como caché de datos

Redis como almacenamiento de sesiones

Redis como sistema de mensajería ligera (eventos)

# TECNOLOGÍAS UTILIZADAS
Java 21

Spring Boot 3.2.x

Spring Web

Spring Data JPA

Spring Data Redis

Spring Cache

Spring Session (Redis)

H2 Database (en memoria)

Lombok

Maven

# BASE DE DATOS (H2)
Se utiliza H2 en memoria para simplificar el entorno local.

Entidades principales:

User: id, name, email

AuditEvent: id, eventType, createdAt

Los datos iniciales se cargan automáticamente al iniciar la aplicación mediante: CommandLineRunner

Esto permite probar Redis sin necesidad de preparar datos manualmente.

# BUENAS PRÁCTICAS APLICADAS
Redis NO se expone al frontend

Redis se usa solo para: Datos temporales, Caché, Sesiones, Eventos

# LEVANTAR REDIS LOCAL
Descarga:
https://github.com/tporadowski/redis/releases

Archivo recomendado:
Redis-x64-5.0.x.msi

Durante instalación:
Puerto: 6379

Permitir servicio automático

Levantar Redis
En la carpeta que se haya instalado, ejecutar

redis-server.exe

redis-cli.exe

Validar si está siendo usado el puerto 6379 en este momento

netstat -ano | findstr 6379

- Si ves una línea con LISTENING, anota el PID y mata el proceso

Ejemplo

taskkill /PID <pid> /F

tasklist /FI "PID eq 17312"

taskkill /PID 17312 /F

Para validar que está funcionando

redis-cli ping

# ENDPOINTS DISPONIBLES
# Crear usuario
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"name":"Carlos","email":"carlos@mail.com"}'


# Consulta (1ra vez va a H2)
curl http://localhost:8080/users/1

# Consulta (2da vez va a Redis)
curl http://localhost:8080/users/1

# Ver sesión (Redis)
curl http://localhost:8080/session
