package com.example.perf.mod_l1_3;

import java.util.*;
import java.util.stream.*;

/** Generated class Service7 in mod-l1-3. */
public class Service7 {
    public record Item(String name, int qty, double price) {
        public double total() { return qty * price; }
    }

    private final Map<String, List<Item>> index = new HashMap<>();

    public long score(long seed) {
        long h = seed * 31 + 7;
        for (int i = 0; i < 50; i++) { h = h * 6364136223846793005L + 1442695040888963407L; }
        return Math.abs(h % 1000);
    }

    public long combined(long seed) {
        return score(seed) + (new com.example.perf.mod_l0_1.Service2().score(seed));
    }

    public List<Item> items(int n) {
        return IntStream.range(0, n)
            .mapToObj(i -> new Item("item-" + i, i % 7 + 1, (i * 13 % 100) / 10.0))
            .collect(Collectors.toList());
    }

    public double totalValue(int n) {
        return items(n).stream().mapToDouble(Item::total).sum();
    }

    public Map<Integer, Long> histogram(int n) {
        return items(n).stream().collect(Collectors.groupingBy(Item::qty, TreeMap::new, Collectors.counting()));
    }

    public void put(String key, Item item) {
        index.computeIfAbsent(key, k -> new ArrayList<>()).add(item);
    }

    public Optional<Item> mostExpensive(String key) {
        return index.getOrDefault(key, List.of()).stream().max(Comparator.comparingDouble(Item::total));
    }

    public String reverseWords(String s) {
        List<String> parts = new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        Collections.reverse(parts);
        return String.join(" ", parts);
    }

    public boolean isPalindrome(String s) {
        String c = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return new StringBuilder(c).reverse().toString().equals(c);
    }

    public long fib(int n) {
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) { long t = a + b; a = b; b = t; }
        return a;
    }

    public List<Integer> primesUpTo(int n) {
        boolean[] comp = new boolean[n + 1];
        List<Integer> out = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!comp[i]) { out.add(i); for (long j = (long) i * i; j <= n; j += i) comp[(int) j] = true; }
        }
        return out;
    }

public sealed interface Shape permits Circle, Square, Rect {}
public record Circle(double r) implements Shape {}
public record Square(double s) implements Shape {}
public record Rect(double w, double h) implements Shape {}

public static double area(Shape s) {
    return switch (s) {
        case Circle c -> Math.PI * c.r() * c.r();
        case Square q -> q.s() * q.s();
        case Rect(double w, double h) -> w * h;
    };
}

}
