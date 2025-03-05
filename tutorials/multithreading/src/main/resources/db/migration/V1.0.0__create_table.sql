CREATE TABLE Product (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_offer_applied BOOLEAN NOT NULL,
    discount_percentage DECIMAL(5, 2) NOT NULL,
    price_after_discount DECIMAL(10, 2) NOT NULL
);