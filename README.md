# Bookstore TDD

Java/Maven project implementing an online bookshop **purchasing flow** with **TDD** using **JUnit 5**, **Mockito**, **JaCoCo**.

## Project layout

```
pom.xml
src/
 ├─ main/java/com/example/bookstore/
 │   ├─ model/
 │   │   ├─ User.java
 │   │   ├─ Cart.java
 │   │   ├─ CartItem.java
 │   │   ├─ Category.java
 │   │   ├─ Book.java
 │   │   ├─ Order.java
 │   │   ├─ Review.java
 │   │   └─ Payment.java
 │   ├─ model/enums/
 │   │   ├─ UserRole.java
 │   │   ├─ PaymentMethod.java
 │   │   └─ PaymentStatus.java
 │   └─ service/
 │       ├─ CartService.java
 │       └─ OrderService.java
 └─ test/java/com/example/bookstore/
     ├─ model/
     │   └─ ModelPojoTest.java
     ├─ model/enums/
     │   └─ EnumsTest.java
     └─ service/
         ├─ CartServiceTest.java
         └─ OrderServiceTest.java
```

## Build & test

```bash
# JDK 17 recommended
mvn clean verify
# Coverage report: open
# target/site/jacoco/index.html
```
