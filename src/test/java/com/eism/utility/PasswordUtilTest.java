package com.eism.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {
    @Test
    void hashesAndMatchesPassword() {
        PasswordUtil utility = new PasswordUtil();
        String salt = utility.createSalt();
        String hash = utility.hash("admin123", salt);

        assertTrue(utility.matches("admin123", hash, salt));
        assertFalse(utility.matches("wrong", hash, salt));
    }
}
