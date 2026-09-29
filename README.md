# Employee Information Management System

This is a simple terminal program made with Java, Maven, and MySQL. The code is organized into controller, DAO, model, service, utility, view, and exception packages.

## What is needed

- JDK 17 or newer
- MySQL Server and MySQL Workbench
- Maven
- VS Code Extension Pack for Java, or another Java IDE

## First-time setup

1. Open MySQL Workbench and connect to your local MySQL server.
2. Open `database/schema.sql` and execute all of it. This creates `eism_db`, `admins`, and `employees`.
3. Copy `src/main/resources/db.properties.example` to `src/main/resources/db.properties`.
4. Set the values to your own MySQL login:

```properties
db.url=jdbc:mysql://localhost:3306/eism_db
db.username=root
db.password=YOUR_MYSQL_PASSWORD
```

5. Build the project:

```text
mvn clean compile
```

6. Run `src/main/java/com/eism/Main.java`.
7. The first run asks you to create an admin username and password. You do not need to manually insert an admin row.

## Windows commands

From the project folder:

```text
cd C:\Users\YourName\Desktop\projects\EISM
mvn clean test
mvn clean package
mvn compile exec:java "-Dexec.mainClass=com.eism.Main"
```

Maven downloads MySQL Connector/J automatically from `pom.xml`.

## Program menu

After logging in, the program can view, search, add, update, and delete employees.

## Important files

- `Main.java`: starts the application and connects the components
- `controller`, `service`, and `dao`: login and employee application logic
- `model`, `view`, `utility`, and `exception`: data, console interaction, database setup, and application errors
- `PasswordUtil.java`: password hashing
- `schema.sql`: MySQL tables
- `db.properties`: local database settings; do not share this file because it contains a password
