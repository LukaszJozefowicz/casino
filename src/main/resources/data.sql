INSERT INTO ADDRESSES (city, street, house_number, flat_number, post_code) VALUES
('New York', '1st street', 32, '1A', '59-600'),
('London', '2nd street', 34, '8', '54-300'),
('Warsaw', '3rd street', 45, '21', '52-500'),
('Wroclaw', '4th street', 56, '3A', '51-400');

INSERT INTO EMPLOYEES (universal_id, first_name, last_name, work_position, salary, contract_start_date, contract_end_date, address_id) VALUES
('123', 'John', 'Wick', 'MANAGER', 15000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1),
('123', 'John', '1st', 'POKER_ATTENDANT', 8000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1),
('124', 'John', '2nd', 'SLOT_MACHINES_ATTENDANT', 8000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1),
('125', 'John', '3rd', 'MULTI_SKILL_CARDS', 10000.0, '2022-01-01 00.00.01', '2022-12-30 23.59.59', 1),
('126', 'John', 'Best', 'MULTI_SKILL_ALL', 13000.0, '2022-01-01 00.00.01', null, 3);

INSERT INTO CUSTOMERS (universal_id, first_name, last_name, current_balance, last_time_present, address_id) VALUES
('666', 'John', 'Smith', 5000.0, '2022-11-20 14.00.00', 2),
('667', 'Anna', 'Smith', 5000.0, '2022-11-20 14.00.00', 2),
('668', 'James', 'Bond', 5000.0, '2022-11-20 14.00.00', 4),
('669', 'Rocky', 'Balboa', 5000.0, '2022-11-20 14.00.00', 4);

INSERT INTO ITEMS (item_type, item_condition, is_assigned) VALUES
('SLOT_MACHINE_BOX', 'EXCELLENT', false),
('SLOT_MACHINE_BOX', 'EXCELLENT', false),
('SLOT_MACHINE_BUTTONS', 'EXCELLENT', false),
('SLOT_MACHINE_BUTTONS', 'EXCELLENT', false),
('SLOT_MACHINE_ELECTRONICS', 'EXCELLENT', false),
('SLOT_MACHINE_ELECTRONICS', 'EXCELLENT', false),
('POKER_CARDS_SET', 'EXCELLENT', false),
('POKER_CARDS_SET', 'EXCELLENT', false),
('BLACKJACK_CARDS_SET', 'EXCELLENT', false),
('BLACKJACK_CARDS_SET', 'EXCELLENT', false),
('CARD_TABLE', 'EXCELLENT', false),
('CARD_TABLE', 'EXCELLENT', false),
('CARD_TABLE', 'EXCELLENT', false),
('CARD_TABLE', 'EXCELLENT', false),
('ROULETTE_TABLE', 'EXCELLENT', false),
('ROULETTE_BALL', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false),
('SIMPLE_CHAIR', 'EXCELLENT', false),
('SIMPLE_CHAIR', 'EXCELLENT', false),
('HIGH_BAR_CHAIR', 'EXCELLENT', false),
('HIGH_BAR_CHAIR', 'EXCELLENT', false),
('COMFY_CHAIR', 'EXCELLENT', false);


