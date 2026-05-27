CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    rating INT NOT NULL,
    comment VARCHAR(255)
);