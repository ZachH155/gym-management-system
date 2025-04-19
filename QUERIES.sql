--CREATE TABLES

CREATE TABLE users(
username VARCHAR(20) UNIQUE,
password VARCHAR(255),
email VARCHAR(30),
phoneNumber VARCHAR(15),
address VARCHAR(30),
role VARCHAR(10)
)

CREATE TABLE memberships(
customer VARCHAR(20) UNIQUE,
cost SERIAL,
description VARCHAR(255)
)

CREATE TABLE workoutclasses(
title VARCHAR(20),
trainer VARCHAR(20),
description VARCHAR(255)
)

--USERDAO

INSERT INTO users(
username,
password,
email, phonenumber,
address,
role) VALUES (?, ?, ?, ?, ?, ?)

SELECT * FROM users

DELETE FROM users WHERE username = ?

--MEMBERSHIPDAO

INSERT INTO memberships(
customer,
cost,
description) VALUES (?, ?, ?)

SELECT * FROM memberships

DELETE FROM memberships WHERE customer = ?

--WORKOUTCALSSDAO

INSERT INTO workoutclasses(
title,
trainer,
description) VALUES (?, ?, ?)

SELECT * FROM workoutclasses

DELETE FROM workoutclasses WHERE title = ?

