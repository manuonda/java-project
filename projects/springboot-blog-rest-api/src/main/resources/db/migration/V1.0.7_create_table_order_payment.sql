CREATE TABLE IF NOT EXISTS orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_tracking_number VARCHAR(255),
    total_quantity INT,
    total_price DECIMAL(10, 2),
    status VARCHAR(255),
    date_created DATETIME,
    last_updated DATETIME,
    shopping_cart_id BIGINT
);

CREATE TABLE IF NOT EXISTS payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    card_name VARCHAR(255),
    card_number VARCHAR(255),
    expiry_year INT,
    expiry_month INT,
    cvc INT,
    order_id BIGINT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);