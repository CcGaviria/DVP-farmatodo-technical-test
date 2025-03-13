# 🏥 MicroServices Technical Test

## 📌 Descripción del Proyecto

Este proyecto implementa una arquitectura basada en microservicios para la gestión de usuarios y tickets, permitiendo autenticación mediante JWT y comunicación entre los servicios vía HTTP.  

---

## 🏰️ **Arquitectura de Microservicios**

Este sistema consta de dos microservicios principales:

### 1️⃣ **Microservicio de Usuarios**
- **Descripción:** Maneja la autenticación y gestión de usuarios.  
- **Endpoints clave:**  
  - `/api/auth` → Autenticación  
  - `/api/users` → CRUD de usuarios  

### 2️⃣ **Microservicio de Tickets**
- **Descripción:** Gestiona la creación y consulta de tickets.  
- **Endpoints clave:**  
  - `/api/tickets` → CRUD de tickets  

---

## 🔗 **Comunicación entre Microservicios**

Los servicios se comunican entre sí a través de HTTP, permitiendo la validación de usuarios autenticados antes de interactuar con los tickets.  

**🌊 Diagrama de comunicación:**  
```
[Cliente] → [Microservicio de Usuarios]
                                      |
[Cliente] → [Microservicio de Tickets] → [Microservicio de Usuarios]
```

> **Importante:**  
> - Se requiere autenticación vía JWT para acceder a los endpoints protegidos.  
> - El microservicio de Tickets depende del de Usuarios para validar autenticación.  

---

## 🚀 **Guía de Ejecución**

### 1️⃣ **Clonar el repositorio**
```bash
git clone https://github.com/CcGaviria/DVP-farmatodo-technical-test.git
cd DVP-farmatodo-technical-test
```

### 2️⃣ **Ejecutar el proyecto con Docker**
#### 🔹 **Dar permisos de ejecución al script**
```bash
chmod +x run.sh
```

#### 🔹 **Ejecutar el script para compilar y levantar los servicios**
```bash
./run.sh
```

> **Nota:** Este script:
> - Compila los microservicios con `Maven`.
> - Construye las imágenes Docker.
> - Levanta los contenedores con `docker-compose`.

---

## 🛠️ **Requisitos Previos**
Asegúrate de tener instalado en tu máquina:
- **Docker** 🐛  
- **Docker Compose**  
- **Java 17** ☕  
- **Maven**  

---

## 🐟 **Swagger - Documentación de APIs**
Los endpoints están documentados en Swagger y accesibles desde:

- **Microservicio de Usuarios**  
  👉 [http://localhost:8070/swagger-ui/index.html](http://localhost:8070/swagger-ui/index.html)  

- **Microservicio de Tickets**  
  👉 [http://localhost:8071/swagger-ui/index.html](http://localhost:8071/swagger-ui/index.html)  

### 🔑 **Configuración de Autenticación en Swagger**
1. Inicia sesión en `/api/auth/login` para obtener un **JWT**.
2. En Swagger, haz clic en **Authorize** y pega el token
3. Ahora puedes realizar peticiones autenticadas.  

---

## 🔥 **APIs Implementadas**

### 🤝 **Usuarios - CRUD**
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `/api/users/{id}` | Obtener usuario por ID |
| `GET` | `/api/users` | Listar todos los usuarios |
| `POST` | `/api/users` | Crear un nuevo usuario |
| `PUT` | `/api/users/{id}` | Actualizar un usuario |
| `DELETE` | `/api/users/{id}` | Eliminar un usuario |

### 🔒 **Autenticación**
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `/api/auth/register` | Registrar un usuario |
| `POST` | `/api/auth/login` | Iniciar sesión y obtener JWT |
| `GET` | `/api/auth/validate-token` | Validar un token |

### 🎟️ **Tickets - CRUD**
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `GET` | `/api/tickets/{id}` | Obtener un ticket por ID |
| `GET` | `/api/tickets` | Listar todos los tickets |
| `POST` | `/api/tickets` | Crear un nuevo ticket |
| `PUT` | `/api/tickets/{id}` | Actualizar un ticket |
| `DELETE` | `/api/tickets/{id}` | Eliminar un ticket |

---

## 📌 **Notas Finales**
💚 El sistema utiliza **PostgreSQL** como base de datos.  
🛠️ `docker-compose` configura automáticamente la base de datos y los servicios.  
💪 Puedes verificar los contenedores en ejecución con:  
```bash
docker ps
```

---

🚀 **¡Listo!** 💻🔥

