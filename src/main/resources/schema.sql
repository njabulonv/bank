
CREATE TABLE BANK_ACCOUNT (
  account_number INT PRIMARY KEY,
  account_type VARCHAR(250) NOT NULL,
  amount DOUBLE NOT NULL,
  deposit DOUBLE,
  withdraw DOUBLE,
  transfers DOUBLE,
  overdraft DOUBLE
);

CREATE TABLE CLIENT (
  client_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  id_Number INT NOT NULL,
  email VARCHAR(250) NOT NULL,
  gender VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  account_number INT,
  foreign key (account_number) references bank_account(account_number)
);

CREATE TABLE BANK_MANAGER(
    manager_id INT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    surname VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE BANK_STATEMENT(
    bankstatement_id INT AUTO_INCREMENT  PRIMARY KEY,
    clientName VARCHAR(250) NOT NULL,
    clientSurname  VARCHAR(250) NOT NULL,
    bank_date DATE NOT NULL,
    bank_time TIME NOT NULL,
    description VARCHAR(250) NOT NULL,
    transaction_type VARCHAR(250) NOT NULL,
    transfers DOUBLE,
    account_number INT NOT NULL,
    foreign key (account_number) references bank_account(account_number)
);

