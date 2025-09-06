# Bookstore TDD

Java/Maven project implementing an online bookshop **purchasing flow** with **TDD** using **JUnit 5**, **Mockito**, **JaCoCo**.

## Project layout
pom.xml
src/
├─ main/java/com/example/bookstore/
│ ├─ model/ # User, Cart, CartItem, Category, Book, Order, Review, Payment
│ ├─ model/enums/ # UserRole, PaymentMethod, PaymentStatus
│ └─ service/ # CartService, OrderService
└─ test/java/com/example/bookstore/
├─ model/ModelPojoTest.java
├─ model/enums/EnumsTest.java
└─ service/
├─ CartServiceTest.java
└─ OrderServiceTest.java

## Build & test
```bash
# JDK 17 recommended
mvn clean verify
# Coverage report: open
# target/site/jacoco/index.html
