package com.example.bookstore.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnumsTest {

    @Test
    public void userRole_values_and_valueOf() {
        assertEquals(UserRole.CUSTOMER, UserRole.valueOf("CUSTOMER"));
        assertEquals(2, UserRole.values().length); // CUSTOMER, ADMIN
    }

    @Test
    public void userRole_valueOf_invalid_shouldThrow() {
        assertThrows(IllegalArgumentException.class, () -> UserRole.valueOf("NOT_A_ROLE"));
    }

    @Test
    public void paymentMethod_values() {
        assertNotNull(PaymentMethod.valueOf("CARD"));
        assertTrue(java.util.EnumSet.allOf(PaymentMethod.class).size() >= 3);
    }

    @Test
    public void paymentStatus_values_and_invalid() {
        assertEquals(3, PaymentStatus.values().length); // PENDING, PAID, CANCELED
        assertThrows(IllegalArgumentException.class, () -> PaymentStatus.valueOf("UNKNOWN"));
    }

    @Test
    public void iterate_all_payment_methods_and_statuses() {
        for (PaymentMethod m : PaymentMethod.values()) {
            assertNotNull(m.name());
        }
        for (PaymentStatus s : PaymentStatus.values()) {
            assertTrue(s.ordinal() >= 0);
        }
    }
}
