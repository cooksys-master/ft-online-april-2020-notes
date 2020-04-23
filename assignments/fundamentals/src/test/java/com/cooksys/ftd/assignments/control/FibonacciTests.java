package com.cooksys.ftd.assignments.control;

import org.junit.*;

import static com.cooksys.ftd.assignments.control.Fibonacci.*;
import static org.junit.Assert.*;

public class FibonacciTests {
    private static int size;
    private static int window;
    private static int[] empty;
    private static int[] seed;
    private static int[] fib;
    private static int[][] slices;
    private static int[][] prefixes;

    @BeforeClass
    public static void before() {
        size = 100;
        window = 10;

        empty = new int[]{};
        seed = new int[]{1, 1};
        fib = new int[size];

        System.arraycopy(seed, 0, fib, 0, seed.length);
        for (int i = seed.length; i < size; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }

        slices = new int[size - window][window];
        for (int i = 0; i < slices.length; i++) {
            System.arraycopy(fib, i, slices[i], 0, window);
        }

        prefixes = new int[size][];
        for (int i = 0; i < size; i++) {
            prefixes[i] = new int[i + 1];
            System.arraycopy(fib, 0, prefixes[i], 0, i + 1);
        }
    }

    @AfterClass
    public static void after() {
        size = 0;
        window = 0;
        empty = null;
        seed = null;
        fib = null;
        slices = null;
        prefixes = null;
    }

    @Test
    public void atIndexTest() {
        for (int i = 0; i < fib.length; i++) {
            assertEquals(fib[i], atIndex(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void atIndexExceptionTest() {
        atIndex(-1);
    }

    @Test
    public void sliceTest() {
        assertArrayEquals(empty, slice(0, 0));
        for (int i = 0; i < slices.length; i++) {
            assertArrayEquals(slices[i], slice(i, i + window));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void sliceExceptionTest() {
        slice(-1, 0);
        slice(0, -1);
        slice(1, 0);
    }

    @Test
    public void fibonacciTest() {
        assertArrayEquals(empty, fibonacci(0));
        for (int i = 0; i < prefixes.length; i++) {
            assertArrayEquals(prefixes[i], fibonacci(i + 1));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void fibonacciExceptionTest() {
        fibonacci(-1);
    }
}
