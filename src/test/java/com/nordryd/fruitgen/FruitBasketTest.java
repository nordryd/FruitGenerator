package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitBasket.getFruits;
import static com.nordryd.fruitgen.FruitBasket.hasFruits;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * <p>
 * Unit tests for {@link FruitBasket}.
 * </p>
 *
 * @author Nordryd
 */
public class FruitBasketTest
{
    @Rule
    public final ErrorCollector assertMultiple = new ErrorCollector();

    @Test
    public void testHasFruitsInvalidFruit()
    {
        assertFalse(hasFruits(""));
    }

    @Test
    public void testHasFruitsFirstValidSecondInvalid()
    {
        assertFalse(hasFruits("peach", ""));
    }

    @Test
    public void testHasFruitsValidFruitLowercase()
    {
        assertMultiple.checkThat("Fruit queries are case-sensitive, & shouldn't be, dumbass (lowercase) \uD83C\uDF4D",
                hasFruits("peach"), is(true));
        assertMultiple.checkThat("Fruit queries are case-sensitive, & shouldn't be, dumbass (mult-case) \uD83C\uDF4D",
                hasFruits("PeaCH"), is(true));
        assertMultiple.checkThat("Fruit queries are case-sensitive, & shouldn't be, dumbass (uppercase) \uD83C\uDF4D",
                hasFruits("PEACH"), is(true));
    }

    @Test
    public void testHasFruitsValidForApple()
    {
        assertMultiple.checkThat("\"apple\" is not valid on its own", hasFruits("apple"), is(true));
        assertMultiple.checkThat("\"apple\" is not valid on with others", hasFruits("peach", "apple"), is(true));
    }

    @Test
    public void testGetFruitsZeroLength()
    {
        assertTrue(getFruits(0).isEmpty());
    }

    @Test
    public void testGetFruitsWithArgsZeroLength()
    {
        assertTrue(getFruits(0, "peach").isEmpty());
    }

    @Test
    public void testGetFruitsNegativeLength()
    {
        assertTrue(getFruits(-1).isEmpty());
    }

    @Test
    public void testGetFruitsWithArgsNegativeLength()
    {
        assertTrue(getFruits(-1, "peach").isEmpty());
    }

    @Test
    public void testGetFruitsSingle()
    {
        final int desiredLength = 1, expectedLength = desiredLength * 2;
        assertEquals(expectedLength, getFruits(desiredLength).length());
    }

    @Test
    public void testGetFruits()
    {
        final int desiredLength = 5, expectedLength = desiredLength * 2;
        assertEquals(expectedLength, getFruits(desiredLength).length());
    }

    @Test
    public void testGetFruitsWithArgsInvalidFruit()
    {
        assertTrue(getFruits(5, "").isEmpty());
    }

    @Test
    public void testGetFruitsWithArgsSingleForSingleArg()
    {
        final int desiredLength = 1, expectedLength = desiredLength * 2;
        final String desiredFruit = "pineapple", desiredFruitEncoding = "\uD83C\uDF4D";
        final String actual = getFruits(desiredLength, desiredFruit);
        assertEquals(format("the returned length was not %d", expectedLength), expectedLength, actual.length());
        assertEquals(format("the returned string was not a single %s (%s)", desiredFruit, desiredFruitEncoding),
                desiredFruitEncoding, actual);
    }

    @Test
    public void testGetFruitForApple()
    {
        final String redApple = "\uD83C\uDF4E", greenApple = "\uD83C\uDF4F";
        assertEquals("", getFruits(5, "apple").replaceAll("[" + redApple + greenApple + "]", ""));
    }

    @Test
    public void testGetFruitsWithArgsSingleForMultipleArgs()
    {
        final int desiredLength = 1, expectedLength = desiredLength * 2;
        final String desiredFruit = "pineapple", desiredFruitEncoding = "\uD83C\uDF4D";
        final String desiredFruitOther = "peach", desiredFruitEncodingOther = "\uD83C\uDF51";
        final String actual = getFruits(desiredLength, desiredFruit, desiredFruitOther);
        assertEquals(format("the returned length was not %d", expectedLength), expectedLength, actual.length());
        assertTrue(format("the returned string was not a single %s (%s) or %s (%s)", desiredFruit, desiredFruitEncoding,
                desiredFruitOther, desiredFruitEncodingOther),
                desiredFruitEncoding.equals(actual) || desiredFruitEncodingOther.equals(actual));
    }

    @Test
    public void testGetFruitsWithArgsOneFruit()
    {
        final int desiredLength = 5, expectedLength = desiredLength * 2;
        final String desiredFruit = "pineapple", desiredFruitEncoding = "\uD83C\uDF4D";
        final String actual = getFruits(desiredLength, desiredFruit);
        assertEquals(format("the returned length was not %d", desiredLength), expectedLength, actual.length());
        assertTrue(format("the returned string contained fruits that were not %s (%s)", desiredFruit,
                desiredFruitEncoding), actual.replaceAll(desiredFruitEncoding, "").isEmpty());
    }

    @Test
    public void testGetFruitsWithArgs()
    {
        final int desiredLength = 5, expectedLength = desiredLength * 2;
        assertEquals(expectedLength, getFruits(desiredLength, "pineapple", "peach").length());
    }
}
