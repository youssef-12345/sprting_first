-- Sample Data Initialization for Supply Chain Management System
-- This SQL script provides sample data for testing the application

-- Insert sample users
INSERT INTO users (username, password, role, active, created_at, updated_at) VALUES
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/TVm', 'ADMIN', true, NOW(), NOW()),
('manager', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/TVm', 'MANAGER', true, NOW(), NOW()),
('user1', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcg7b3XeKeUxWdeS86E36P4/TVm', 'USER', true, NOW(), NOW());

-- Insert sample products
INSERT INTO products (product_code, product_name, description, category, unit_price, is_active, created_at, updated_at) VALUES
('LAPTOP-DELL-001', 'Dell XPS 13', 'Ultra-portable 13-inch laptop', 'Electronics', 1200.00, true, NOW(), NOW()),
('LAPTOP-HP-001', 'HP Pavilion 15', '15-inch laptop for professionals', 'Electronics', 850.00, true, NOW(), NOW()),
('MOUSE-LOGITECH', 'Logitech MX Master 3', 'Wireless mouse for productivity', 'Accessories', 99.99, true, NOW(), NOW()),
('KEYBOARD-MECH', 'Mechanical Gaming Keyboard', 'RGB mechanical keyboard', 'Accessories', 149.99, true, NOW(), NOW()),
('MONITOR-4K', 'LG 27-inch 4K Monitor', '4K UHD display', 'Electronics', 599.99, true, NOW(), NOW()),
('WEBCAM-HD', 'Logitech HD Webcam', '1080p HD webcam', 'Accessories', 79.99, true, NOW(), NOW()),
('HEADPHONES-SONY', 'Sony WH-1000XM5', 'Noise-cancelling headphones', 'Accessories', 399.99, true, NOW(), NOW()),
('USB-HUB', '7-Port USB Hub', 'Multi-port USB hub', 'Accessories', 49.99, true, NOW(), NOW());

-- Insert sample suppliers
INSERT INTO suppliers (supplier_code, supplier_name, description, contact_person, email, phone, address, is_active, created_at, updated_at) VALUES
('SUP-DELL', 'Dell Inc.', 'Leading computer hardware manufacturer', 'John Smith', 'contact@dell.com', '+1-800-123-4567', '1 Dell Way, Round Rock, TX', true, NOW(), NOW()),
('SUP-HP', 'HP Inc.', 'Global technology company', 'Sarah Johnson', 'sales@hp.com', '+1-888-123-4567', 'HP Building, Palo Alto, CA', true, NOW(), NOW()),
('SUP-LOGITECH', 'Logitech International', 'Computer peripheral manufacturer', 'Michel Logitech', 'partner@logitech.com', '+41-43-233-0700', 'Logitech Center, Switzerland', true, NOW(), NOW()),
('SUP-LG', 'LG Electronics', 'Display and monitor manufacturer', 'Kim Lee', 'supplies@lg.com', '+82-2-3777-1114', 'LG Twin Towers, Seoul', true, NOW(), NOW()),
('SUP-SONY', 'Sony Corporation', 'Electronics and audio equipment', 'Takeshi Yamada', 'business@sony.com', '+81-3-6748-2111', 'Sony Building, Tokyo', true, NOW(), NOW());

-- Insert sample stock entries
INSERT INTO stocks (product_id, quantity, minimum_level, maximum_level, warehouse_location, created_at, updated_at) VALUES
(1, 150, 20, 500, 'Warehouse-A-101', NOW(), NOW()),
(2, 200, 30, 600, 'Warehouse-A-102', NOW(), NOW()),
(3, 500, 100, 1000, 'Warehouse-B-201', NOW(), NOW()),
(4, 300, 50, 800, 'Warehouse-B-202', NOW(), NOW()),
(5, 75, 15, 300, 'Warehouse-A-103', NOW(), NOW()),
(6, 250, 50, 600, 'Warehouse-B-203', NOW(), NOW()),
(7, 120, 30, 400, 'Warehouse-C-301', NOW(), NOW()),
(8, 350, 100, 1000, 'Warehouse-C-302', NOW(), NOW());

-- Insert sample sales orders
INSERT INTO sales (sale_order_number, product_id, quantity, unit_price, total_amount, status, customer_name, delivery_address, created_at, updated_at) VALUES
('SO-2024-001', 1, 2, 1200.00, 2400.00, 'CONFIRMED', 'ABC Corporation', '100 Business Park, NY', NOW(), NOW()),
('SO-2024-002', 3, 5, 99.99, 499.95, 'DELIVERED', 'Tech Store XYZ', '250 Retail Ave, CA', NOW(), NOW()),
('SO-2024-003', 5, 1, 599.99, 599.99, 'PENDING', 'Design Studio', '500 Creative Blvd, TX', NOW(), NOW()),
('SO-2024-004', 2, 3, 850.00, 2550.00, 'SHIPPED', 'Enterprise Solutions', '1000 Corporate Way, FL', NOW(), NOW()),
('SO-2024-005', 7, 2, 399.99, 799.98, 'DELIVERED', 'Music Store Pro', '150 Audio Lane, IL', NOW(), NOW()),
('SO-2024-006', 4, 4, 149.99, 599.96, 'PENDING', 'Gaming Hub', '75 Gaming District, WA', NOW(), NOW()),
('SO-2024-007', 6, 10, 79.99, 799.90, 'CONFIRMED', 'Office Supplies Inc', '300 Supply Chain, OH', NOW(), NOW()),
('SO-2024-008', 8, 7, 49.99, 349.93, 'DELIVERED', 'Tech Wholesaler', '500 Trade Zone, PA', NOW(), NOW());

-- Note: Default password for users is "password" (BCrypt hashed)
-- Users: admin, manager, user1
-- All have password: "password"
