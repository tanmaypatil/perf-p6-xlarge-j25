package com.example.perf.mod_l2_6;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class Service106Test {
    private Service106 svc;

    @BeforeEach
    void setUp() { svc = new Service106(); }

    @Test
    void scoreIsBounded() {
        for (long s = 0; s < 2_000; s++) { long v = svc.score(s); assertTrue(v >= 0 && v < 1000); }
    }

    @Test
    void combinedIsDeterministic() { assertEquals(svc.combined(7), svc.combined(7)); }

    @Test
    void totalsAndHistogram() {
        assertEquals(500, svc.histogram(500).values().stream().mapToLong(Long::longValue).sum());
        assertTrue(svc.totalValue(1_000) > 0);
    }

    @Test
    void mostExpensive() {
        svc.put("k", new Service106.Item("a", 1, 1.0));
        svc.put("k", new Service106.Item("b", 3, 2.0));
        assertEquals("b", svc.mostExpensive("k").orElseThrow().name());
        assertTrue(svc.mostExpensive("missing").isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Racecar", "A man, a plan, a canal: Panama", "Was it a car or a cat I saw"})
    void palindromes(String s) { assertTrue(svc.isPalindrome(s)); }

    @Test
    void stringsAndMath() {
        assertEquals("c b a", svc.reverseWords("a b  c"));
        assertEquals(6765, svc.fib(20));
        assertEquals(1229, svc.primesUpTo(10_000).size());
    }

    @Test
    void shapes() {
        assertEquals(4.0, Service106.area(new Service106.Square(2)), 1e-9);
    }
}
