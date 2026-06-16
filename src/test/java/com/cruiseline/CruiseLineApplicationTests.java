package com.cruiseline;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Smoke test: verifies the Spring application context loads.
 * Uses the H2 in-memory database via the 'test' profile.
 */
@SpringBootTest
@ActiveProfiles("test")
class CruiseLineApplicationTests {

    @Test
    void contextLoads() {
    }
}
