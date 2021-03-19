CREATE TABLE clients (
    clients_id uuid not NULL,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    age INT NOT NULL,
    born_date DATE,
    PRIMARY KEY (clients_id)
);