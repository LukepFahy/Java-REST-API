# Java-REST-API
REST API + Java Database with CRUD Functionality 

Prerequisites

Netbeans - 15

Java 8 (1.8)

GlassFish Server - 6

-------------------

Passports contains the code in src/main/java and is a netbeans project, can place in netbeans + clean & build to run 

PassportDB is the database used - import the folder or if needed recreate doing the following;

Use a Java DB named 'PassportDB' with the login credentials

user: app
password: app

Create a table called PASSPORT with the columns ID, Name, Country and Age and execute this SQL command to fill it

INSERT INTO passport (ID, Name, Country, Age)
VALUES
  (1, 'John Smith', 'USA', 30),
  (2, 'Jane Doe', 'Canada', 25),
  (3, 'Bob Johnson', 'UK', 35),
  (4, 'Alice Smith', 'USA', 28),
  (5, 'Mike Williams', 'Canada', 32),
  (6, 'Sara Johnson', 'USA', 29),
  (7, 'Trevor Williams', 'UK', 27),
  (8, 'Emma Smith', 'USA', 31),
  (9, 'John Doe', 'Canada', 26),
  (10, 'Bob Smith', 'UK', 34);
