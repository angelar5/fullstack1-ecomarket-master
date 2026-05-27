CREATE TABLE shipments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    tracking_code VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(50) NOT NULL,
    delivery_address VARCHAR(255) NOT NULL
);