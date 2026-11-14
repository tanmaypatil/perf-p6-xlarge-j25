package com.example.perf.mod_l1_3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class Service14Test {
    private Service14 svc;

    @BeforeEach
    void setUp() { svc = new Service14(); }

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
        svc.put("k", new Service14.Item("a", 1, 1.0));
        svc.put("k", new Service14.Item("b", 3, 2.0));
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
        assertEquals(4.0, Service14.area(new Service14.Square(2)), 1e-9);
    }
}
