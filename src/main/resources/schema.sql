

CREATE TABLE IF NOT EXISTS customer (
    customer_id INTEGER UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    middle_name VARCHAR(30) NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(60) NULL,
    street_line1 VARCHAR(50) NOT NULL,
    street_line2 VARCHAR(50) NULL,
    city VARCHAR(40) NOT NULL,
    state CHAR(2) NOT NULL,
    zip MEDIUMINT UNSIGNED NOT NULL,
    phone VARCHAR(20) NOT NULL,
    date_of_birth DATE NOT NULL,
    sex ENUM('M', 'F') NOT NULL
);