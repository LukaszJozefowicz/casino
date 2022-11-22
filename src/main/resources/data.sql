INSERT INTO ADDRESSES (address_id, city, street, house_number, flat_number, post_code) VALUES
(1, 'New York', '1st street', 32, '1A', '59-600');
INSERT INTO ADDRESSES (address_id, city, street, house_number, flat_number, post_code) VALUES
(2, 'London', '2nd street', 34, '8', '54-300');
INSERT INTO ADDRESSES (address_id, city, street, house_number, flat_number, post_code) VALUES
(3, 'Warsaw', '3rd street', 45, '21', '52-500');
INSERT INTO ADDRESSES (address_id, city, street, house_number, flat_number, post_code) VALUES
(4, 'Wroclaw', '4th street', 56, '3A', '51-400');

INSERT INTO EMPLOYEES (employee_id, universal_id, first_name, last_name, work_position, salary, contract_start_date, contract_end_date, address_id) VALUES
(1, '123', 'John', 'Wick', 'MANAGER', 15000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1);
INSERT INTO EMPLOYEES (employee_id, universal_id, first_name, last_name, work_position, salary, contract_start_date, contract_end_date, address_id) VALUES
(2, '123', 'John', '1st', 'POKER_ATTENDANT', 8000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1);
INSERT INTO EMPLOYEES (employee_id, universal_id, first_name, last_name, work_position,  salary,contract_start_date, contract_end_date, address_id) VALUES
(3, '124', 'John', '2nd', 'SLOT_MACHINES_ATTENDANT', 8000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1);
INSERT INTO EMPLOYEES (employee_id, universal_id, first_name, last_name, work_position,  salary,contract_start_date, contract_end_date, address_id) VALUES
(4, '125', 'John', '3rd', 'MULTI_SKILL_CARDS', 10000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1);
INSERT INTO EMPLOYEES (employee_id, universal_id, first_name, last_name, work_position,  salary,contract_start_date, contract_end_date, address_id) VALUES
(5, '126', 'John', 'Best', 'MULTI_SKILL_ALL', 13000.0, '2022-01-01 00.00.01', null, 3);

INSERT INTO CUSTOMERS (customer_id, universal_id, first_name, last_name, current_balance, last_time_present, address_id) VALUES
(1, '666', 'John', 'Smith', 5000.0, '2022-11-20 14.00.00', 2);
INSERT INTO CUSTOMERS (customer_id, universal_id, first_name, last_name, current_balance, last_time_present, address_id) VALUES
(2, '667', 'Anna', 'Smith', 5000.0, '2022-11-20 14.00.00', 2);
INSERT INTO CUSTOMERS (customer_id, universal_id, first_name, last_name, current_balance, last_time_present, address_id) VALUES
(3, '668', 'James', 'Bond', 5000.0, '2022-11-20 14.00.00', 4);
INSERT INTO CUSTOMERS (customer_id, universal_id, first_name, last_name, current_balance, last_time_present, address_id) VALUES
(4, '669', 'Rocky', 'Balboa', 5000.0, '2022-11-20 14.00.00', 4);

INSERT INTO ITEMS (item_id, item_type, item_condition, is_assigned) VALUES
(1, 'SLOT_MACHINE_BOX', 'EXCELLENT', false),
(2, 'SLOT_MACHINE_BOX', 'EXCELLENT', false),
(3, 'SLOT_MACHINE_BUTTONS', 'EXCELLENT', false),
(4, 'SLOT_MACHINE_BUTTONS', 'EXCELLENT', false),
(5, 'SLOT_MACHINE_ELECTRONICS', 'EXCELLENT', false),
(6, 'SLOT_MACHINE_ELECTRONICS', 'EXCELLENT', false),
(7, 'POKER_CARDS_SET', 'EXCELLENT', false),
(8, 'POKER_CARDS_SET', 'EXCELLENT', false),
(9, 'BLACKJACK_CARDS_SET', 'EXCELLENT', false),
(10, 'BLACKJACK_CARDS_SET', 'EXCELLENT', false),
(11, 'CARD_TABLE', 'EXCELLENT', false),
(12, 'CARD_TABLE', 'EXCELLENT', false),
(13, 'CARD_TABLE', 'EXCELLENT', false),
(14, 'CARD_TABLE', 'EXCELLENT', false),
(15, 'ROULETTE_TABLE', 'EXCELLENT', false),
(16, 'ROULETTE_BALL', 'EXCELLENT', false),
(17, 'COMFY_CHAIR', 'EXCELLENT', false),
(18, 'COMFY_CHAIR', 'EXCELLENT', false),
(19, 'COMFY_CHAIR', 'EXCELLENT', false),
(20, 'COMFY_CHAIR', 'EXCELLENT', false),
(21, 'COMFY_CHAIR', 'EXCELLENT', false),
(22, 'COMFY_CHAIR', 'EXCELLENT', false),
(23, 'COMFY_CHAIR', 'EXCELLENT', false),
(24, 'COMFY_CHAIR', 'EXCELLENT', false),
(25, 'SIMPLE_CHAIR', 'EXCELLENT', false),
(26, 'SIMPLE_CHAIR', 'EXCELLENT', false),
(27, 'HIGH_BAR_CHAIR', 'EXCELLENT', false),
(28, 'HIGH_BAR_CHAIR', 'EXCELLENT', false),
(29, 'COMFY_CHAIR', 'EXCELLENT', false);


