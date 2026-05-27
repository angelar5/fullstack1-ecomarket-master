$services = @(
  @{ name="user-service"; port=8081; h2Db="userdb"; mysqlDb="ecomarket_user"; pkg="userservice" },
  @{ name="auth-service"; port=8082; h2Db="authdb"; mysqlDb="ecomarket_auth"; pkg="authservice" },
  @{ name="inventory-service"; port=8093; h2Db="inventorydb"; mysqlDb="ecomarket_inventory"; pkg="inventoryservice" },
  @{ name="order-service"; port=8084; h2Db="orderdb"; mysqlDb="ecomarket_order"; pkg="orderservice" },
  @{ name="store-service"; port=8085; h2Db="storedb"; mysqlDb="ecomarket_store"; pkg="storeservice" },
  @{ name="shipping-service"; port=8086; h2Db="shippingdb"; mysqlDb="ecomarket_shipping"; pkg="shippingservice" },
  @{ name="supplier-service"; port=8087; h2Db="supplierdb"; mysqlDb="ecomarket_supplier"; pkg="supplierservice" },
  @{ name="billing-service"; port=8088; h2Db="billingdb"; mysqlDb="ecomarket_billing"; pkg="billingservice" },
  @{ name="review-service"; port=8089; h2Db="reviewdb"; mysqlDb="ecomarket_review"; pkg="reviewservice" },
  @{ name="coupon-service"; port=8090; h2Db="coupondb"; mysqlDb="ecomarket_coupon"; pkg="couponservice" }
)

$utf8NoBOM = New-Object System.Text.UTF8Encoding $false

foreach ($svc in $services) {
    $name = $svc.name
    $port = $svc.port
    $h2Db = $svc.h2Db
    $mysqlDb = $svc.mysqlDb
    $pkg = $svc.pkg
    
    $file = "c:\Users\Lian\Downloads\ecomarket\ecomarket\$name\src\main\resources\application.properties"
    
    $content = @"
spring.application.name=$name
server.port=$port

# H2 Database Config (Disabled)
# spring.datasource.url=jdbc:h2:mem:$h2Db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
# spring.datasource.driverClassName=org.h2.Driver
# spring.datasource.username=sa
# spring.datasource.password=
# spring.h2.console.enabled=true
# spring.h2.console.path=/h2-console
# spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# MySQL / XAMPP Configuration (Active)
spring.datasource.url=jdbc:mysql://localhost:3306/${mysqlDb}?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.flyway.clean-disabled=true

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Flyway Configuration
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

# Service integration configs
app.inventory-service.url=http://localhost:8093
app.coupon-service.url=http://localhost:8090
app.billing-service.url=http://localhost:8088

# Structured logging
logging.level.root=INFO
logging.level.com.ecomarket.$pkg=DEBUG
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
"@

    [System.IO.File]::WriteAllText($file, $content, $utf8NoBOM)
    Write-Host "Configured $name successfully."
}

Write-Host "MySQL & port configurations updated for all microservices!"
