# 🏥 CRUD de Citas Médicas - Arquitectura Hexagonal

Este proyecto implementa un **CRUD para la gestión de citas médicas** utilizando **Arquitectura Hexagonal (Ports & Adapters)** con **Spring Boot** y **JPA**.  
Su objetivo es demostrar una separación clara de responsabilidades y facilitar la escalabilidad y mantenibilidad del código.

---

## 🛠️ Tecnologías
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database** (en memoria para pruebas)
- **MapStruct** (mapeo de DTOs)
- **JUnit 5 + MockMvc** (tests de integración)

---

## 📂 Estructura del Proyecto
```plaintext
src/main/java/com/cita_clinica
│
├── domain
│   ├── model            # Entidades de negocio
│   └── repository       # Interfaces (puertos)
│
├── application          # Lógica de negocio (servicios)
│
├── infrastructure
│   └── repository       # Implementación con JPA (adaptador de salida)
│
├── controller           # API REST (adaptador de entrada)
│
├── dto                  # Objetos de transferencia de datos
└── mapper               # Conversión entre entidades y DTOs
