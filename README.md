# Transformación Digital de EcoMarket SPA - Arquitectura de 10 Microservicios

Este proyecto representa la arquitectura de microservicios desarrollada para la transformación digital de **EcoMarket SPA**, una empresa chilena dedicada a la venta de productos ecológicos y sostenibles. La solución aborda los problemas de rendimiento y disponibilidad de la antigua aplicación monolítica mediante la separación de responsabilidades en **10 microservicios independientes**.

---

## 👥 Integrantes del Equipo
* **Angel Perez y Maximiliano Villalobos**

---

## 🏗️ Arquitectura del Sistema (10 Microservicios)

El sistema se compone de 10 servicios desacoplados que cooperan a través de comunicación REST con **WebClient**:

```mermaid
graph TD
    subgraph "Clientes"
        Postman[Postman / REST Clients]
    end

    subgraph "Microservicios (Puertos)"
        direction LR
        UserService[user-service :8081]
        AuthService[auth-service :8082]
        InventoryService[inventory-service :8093]
        OrderService[order-service :8084]
        StoreService[store-service :8085]
        ShippingService[shipping-service :8086]
        SupplierService[supplier-service :8087]
        BillingService[billing-service :8088]
        ReviewService[review-service :8089]
        CouponService[coupon-service :8090]
    end

    Postman -->|Consultar/Crear| UserService
    Postman -->|Consultar Permisos| AuthService
    Postman -->|CRUD Productos| InventoryService
    Postman -->|Registrar Ventas| OrderService
    Postman -->|Gestionar Tiendas| StoreService
    Postman -->|Registrar Envíos| ShippingService
    Postman -->|Gestionar Proveedores| SupplierService
    Postman -->|Listar Facturas| BillingService
    Postman -->|Dejar Reseñas| ReviewService
    Postman -->|Validar Cupones| CouponService

    OrderService -->|WebClient: Valida Stock| InventoryService
    OrderService -->|WebClient: Reduce Stock| InventoryService
    OrderService -->|WebClient: Valida Cupón| CouponService
    OrderService -->|WebClient: Genera Factura| BillingService
```

### Detalle de los 10 Microservicios:

1. **`user-service` (Puerto: `8081`)**
   * **Responsabilidad:** Gestión de cuentas de usuario y perfiles del sistema (Administrador, Gerente, Empleado, Cliente).
   * **Base de Datos:** H2 (`userdb`).
2. **`auth-service` (Puerto: `8082`)**
   * **Responsabilidad:** Administración de permisos y privilegios por rol.
   * **Base de Datos:** H2 (`authdb`).
3. **`inventory-service` (Puerto: `8093`)**
   * **Responsabilidad:** Catálogo de productos sostenibles y control de stock.
   * **Base de Datos:** H2 (`inventorydb`).
4. **`order-service` (Puerto: `8084`)**
   * **Responsabilidad:** Procesamiento de compras y transacciones. Consume `inventory-service` para stock, `coupon-service` para descuentos y `billing-service` para emitir facturas.
   * **Base de Datos:** H2 (`orderdb`).
5. **`store-service` (Puerto: `8085`)**
   * **Responsabilidad:** Administración de las sucursales físicas (Santiago, Valdivia, Antofagasta).
   * **Base de Datos:** H2 (`storedb`).
6. **`shipping-service` (Puerto: `8086`)**
   * **Responsabilidad:** Despachos y logística de entrega.
   * **Base de Datos:** H2 (`shippingdb`).
7. **`supplier-service` (Puerto: `8087`)**
   * **Responsabilidad:** Directorio y abastecimiento de proveedores ecológicos.
   * **Base de Datos:** H2 (`supplierdb`).
8. **`billing-service` (Puerto: `8088`)**
   * **Responsabilidad:** Generación de facturas electrónicas (con cálculo automático de IVA).
   * **Base de Datos:** H2 (`billingdb`).
9. **`review-service` (Puerto: `8089`)**
   * **Responsabilidad:** Calificaciones y comentarios de productos por clientes.
   * **Base de Datos:** H2 (`reviewdb`).
10. **`coupon-service` (Puerto: `8090`)**
    * **Responsabilidad:** Administración y validación de códigos de descuento promocionales.
    * **Base de Datos:** H2 (`coupondb`).

---

## 🛠️ Tecnologías y Patrones Aplicados

1. **Persistencia JPA + Hibernate:** Implementación de entidades de dominio anotadas con definición estricta de PKs, FKs e integridad referencial.
2. **Patrón CSR (Controller-Service-Repository):** Separación rigurosa de responsabilidades.
3. **Bean Validation (JSR 380):** Validaciones en controladores sobre DTOs (`@NotBlank`, `@Email`, `@Positive`, `@Min`, `@NotEmpty`, `@Valid`) retornando respuestas de error estructuradas.
4. **Manejo Centralizado de Excepciones (@ControllerAdvice):** Captura de excepciones con respuestas en JSON estructurado (`ErrorResponse`) y HTTP status correctos.
5. **Logs estructurados con SLF4J:** Trazabilidad en consola ante creación de datos, reducción de stock, emisión de facturas y validaciones fallidas.
6. **Migración de Base de Datos con Flyway:** Cada microservicio inicializa su propia base de datos H2 en memoria de manera automática mediante scripts SQL ordenados (`V1__initial_schema.sql` y `V2__insert_sample_data.sql`).

---

## 📋 Endpoints Principales

* **User Service:** `GET /api/users` | `POST /api/users`
* **Auth Service:** `GET /api/auth/permissions/{roleName}`
* **Inventory Service:** `GET /api/products` | `PUT /api/products/reduce-stock`
* **Order Service:** `GET /api/orders` | `POST /api/orders`
* **Store Service:** `GET /api/stores` | `POST /api/stores`
* **Shipping Service:** `GET /api/shipments` | `POST /api/shipments`
* **Supplier Service:** `GET /api/suppliers` | `POST /api/suppliers`
* **Billing Service:** `GET /api/invoices` | `POST /api/invoices`
* **Review Service:** `GET /api/reviews` | `GET /api/reviews/product/{productId}`
* **Coupon Service:** `GET /api/coupons/validate/{code}`

---

## 💾 Configuración de XAMPP (MySQL)

Por defecto, los microservicios se ejecutan usando la base de datos **H2 en memoria** (no requiere instalaciones previas). Si desea conectarlos a **MySQL de XAMPP**, siga estos pasos:

1. Abra el panel de control de **XAMPP** y active el módulo **MySQL**.
2. Vaya a **phpMyAdmin** (`http://localhost/phpmyadmin`) y cree las siguientes 10 bases de datos:
   * `ecomarket_user`
   * `ecomarket_auth`
   * `ecomarket_inventory`
   * `ecomarket_order`
   * `ecomarket_store`
   * `ecomarket_shipping`
   * `ecomarket_supplier`
   * `ecomarket_billing`
   * `ecomarket_review`
   * `ecomarket_coupon`
3. En cada microservicio, acceda a su archivo `src/main/resources/application.properties`, comente las líneas de H2 y descomente las líneas del bloque **CONFIGURACIÓN PARA MYSQL / XAMPP**.
4. Al arrancar cada microservicio, **Flyway** detectará la base de datos de XAMPP y creará los esquemas e insertará los datos iniciales automáticamente.

---

## 🏃 Cómo Ejecutar el Proyecto

Abra una consola de comandos en la raíz del proyecto y ejecute los siguientes pasos:

### 1. Compilar y Construir el Proyecto
```powershell
./gradlew build -x test
```

### 2. Ejecutar un Microservicio Específico (Ejemplo: `inventory-service`)
```powershell
./gradlew :inventory-service:bootRun
```

*Puede ejecutar cada uno de los 10 servicios de forma similar reemplazando el nombre del submódulo (ej: `:order-service`, `:user-service`, etc.).*

---

## 🧪 Pruebas de Integración con Postman

En la raíz del proyecto se encuentra el archivo **`EcoMarket.postman_collection.json`**. Puede importarlo en Postman para probar el flujo completo:
1. **Crear Orden exitosa:** Envía un POST a `http://localhost:8084/api/orders` con un cupón activo (`BIENVENIDA20`). La orden se procesa, reduce stock en `inventory-service` (puerto `8093`), valida el porcentaje de descuento en `coupon-service` (puerto `8090`) y emite una factura en `billing-service` (puerto `8088`).
2. **Consultar Factura:** Envía un GET a `http://localhost:8088/api/invoices` para comprobar que la factura fue creada exitosamente con el IVA calculado.
3. **Intentar Orden sin Stock:** Envía un POST solicitando una cantidad imposible de stock, validando el retorno del error y los logs en consola.
