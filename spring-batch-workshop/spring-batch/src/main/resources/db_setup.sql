DROP TABLE IF EXISTS PERSON;

CREATE TABLE PERSON(
id INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, 
firstName varchar(20),
lastName varchar(20),
city varchar(30)
);

INSERT INTO PERSON(firstName, lastName, city) VALUES('Allan','Mohammed','Bangalore');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Ashok','Lee','Bangalore');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Raju','Diesel','Delhi');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Ramesh','Chan','NYC');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Howard','Das','California');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Daniel','Gupta','Delhi');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Alexander','Speilberg','New Jersey');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Danny','Rowling','California');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Elizabeth','Lee','Kolkatta');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Thomas','Ali','Delhi');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Billy','Owens','Los Angeles');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Joe','Bush','Atlanta');
INSERT INTO PERSON(firstName, lastName, city) VALUES('John','Chand','San Francisco');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Sultan','Mohammed','NYC');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Harper','Khan','Hyderabad');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Charlie','Chan','Bangalore');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Jake','Beans','Delhi');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Artemis','Harper','Nabraska');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Judith','Reddy','New Jersey');
INSERT INTO PERSON(firstName, lastName, city) VALUES('Elvin','Saring','NYC');