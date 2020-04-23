package com.cooksys.ftd.assignments.control;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FizzBuzzTests {

    @Test
    public void dividesTest() {
        assertTrue(FizzBuzz.divides(3, 3));
        assertTrue(FizzBuzz.divides(5, 5));
        assertTrue(FizzBuzz.divides(15, 3));
        assertTrue(FizzBuzz.divides(15, 5));

        assertFalse(FizzBuzz.divides(1, 3));
        assertFalse(FizzBuzz.divides(2, 3));
        assertFalse(FizzBuzz.divides(4, 3));
        assertFalse(FizzBuzz.divides(5, 3));

        assertFalse(FizzBuzz.divides(1, 5));
        assertFalse(FizzBuzz.divides(2, 5));
        assertFalse(FizzBuzz.divides(3, 5));
        assertFalse(FizzBuzz.divides(4, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void dividesExceptionTest() {
        FizzBuzz.divides(0, 0);
    }

    @Test
    public void messageTest() {
        Assert.assertEquals("3: Fizz", FizzBuzz.message(3));
        Assert.assertEquals("5: Buzz", FizzBuzz.message(5));
        Assert.assertEquals("15: FizzBuzz", FizzBuzz.message(15));
        assertNull(FizzBuzz.message(1));
    }

    @Test
    public void messagesTest() {
        Assert.assertArrayEquals(new String[]{}, FizzBuzz.messages(3, 3));
        Assert.assertArrayEquals(new String[]{"3: Fizz"}, FizzBuzz.messages(3, 4));
        Assert.assertArrayEquals(new String[]{"3: Fizz"}, FizzBuzz.messages(3, 5));
        Assert.assertArrayEquals(new String[]{"3: Fizz", "5: Buzz"}, FizzBuzz.messages(3, 6));
        Assert.assertArrayEquals(new String[]{"3: Fizz", "5: Buzz", "6: Fizz"}, FizzBuzz.messages(3, 7));
        Assert.assertArrayEquals(new String[]{"12: Fizz", "15: FizzBuzz"}, FizzBuzz.messages(12, 16));
    }

    @Test(expected = IllegalArgumentException.class)
    public void messagesExceptionTest() {
        FizzBuzz.messages(20, 19);
    }
}
