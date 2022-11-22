--DROP TABLE IF EXISTS ADDRESSES;
--DROP TABLE IF EXISTS EMPLOYEES;
--DROP TABLE IF EXISTS CUSTOMERS;
--DROP TABLE IF EXISTS ITEMS;
--DROP TABLE IF EXISTS GAMES;

CREATE TABLE IF NOT EXISTS ADDRESSES (
    id INT PRIMARY KEY,
    city VARCHAR(50),
    street VARCHAR(10),
    house_number INT,
    flat_number VARCHAR(50),
    post_code VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS EMPLOYEES (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    universal_id VARCHAR(10),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    work_position VARCHAR(50),
    contract_start_date TIMESTAMP,
    contract_end_date TIMESTAMP,
    address_id INT,
    FOREIGN KEY (address_id) REFERENCES ADDRESSES (address_id)
);

ALTER TABLE EMPLOYEES DROP COLUMN IF EXISTS contract_start_date;
ALTER TABLE EMPLOYEES DROP COLUMN IF EXISTS contract_end_date;
ALTER TABLE EMPLOYEES ADD COLUMN IF NOT EXISTS contract_start_date TIMESTAMP;
ALTER TABLE EMPLOYEES ADD COLUMN IF NOT EXISTS contract_end_date TIMESTAMP;

CREATE TABLE IF NOT EXISTS GAMES (
    game_id INT AUTO_INCREMENT PRIMARY KEY,
    game_type VARCHAR(10),
    assigned_employee_employee_id INT,
    amount_of_players INT,
    times_played INT,
    total_income DECIMAL(50),
    average_income_per_game DECIMAL(50),
    FOREIGN KEY (assigned_employee_employee_id) REFERENCES EMPLOYEES (employee_id)
);

CREATE TABLE IF NOT EXISTS CUSTOMERS (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    universal_id VARCHAR(10),
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    current_balance DECIMAL(50),
    last_time_present TIMESTAMP,
    address_id INT,
    currently_played_game INT,
    FOREIGN KEY (address_id) REFERENCES ADDRESSES (address_id),
    FOREIGN KEY (currently_played_game) REFERENCES GAMES (game_id)
);

CREATE TABLE IF NOT EXISTS ITEMS (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    item_type VARCHAR(20),
    item_condition VARCHAR(15),
    is_assigned BOOLEAN
);

CREATE SEQUENCE "employees_sequence"
START WITH 5
INCREMENT BY 1;
CREATE SEQUENCE "customers_sequence"
START WITH 6
INCREMENT BY 1;