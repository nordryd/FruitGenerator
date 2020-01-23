package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitBasket.getFruits;
import static com.nordryd.fruitgen.FruitBasket.hasFruits;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/**
 * Unit tests for {@link FruitBasket}.
 *
 * @author Nordryd
 */
public class FruitBasketTest
{
    private static final String FRUIT_QUERIES_CASE_SENS = "Fruit queries are case-sensitive, & shouldn't be, dumbass \uD83C\uDF4D";
    private static final int SINGLE_FRUIT_STR_LENGTH = 2;

    @Rule
    public final ErrorCollector multAssert = new ErrorCollector();

    @Test
    public void testHasFruitsInvalidFruit()
    {
        assertThat(hasFruits(""), is(false));
    }

    @Test
    public void testHasFruitsFirstValidSecondInvalid()
    {
        assertThat(hasFruits("pineapple", ""), is(false));
    }

    @Test
    public void testHasFruitsValidFruitLowercase()
    {
        multAssert.checkThat(FRUIT_QUERIES_CASE_SENS + "(lowercase)", hasFruits("peach"), is(true));
        multAssert.checkThat(FRUIT_QUERIES_CASE_SENS + "(mult-case)", hasFruits("PeaCH"), is(true));
        multAssert.checkThat(FRUIT_QUERIES_CASE_SENS + "(uppercase)", hasFruits("PEACH"), is(true));
    }

    @Test
    public void testGetFruitsZeroLength()
    {
        assertThat(getFruits(0), is(""));
    }

    @Test
    public void testGetFruitsWithArgsZeroLength()
    {
        assertThat(getFruits(0, "peach"), is(""));
    }

    @Test
    public void testGetFruitsNegativeLength()
    {
        assertThat(getFruits(-1), is(""));
    }

    @Test
    public void testGetFruitsWithArgsNegativeLength()
    {
        assertThat(getFruits(-1, "peach"), is(""));
    }

    @Test
    public void testGetFruitsSingle()
    {
        assertThat(getFruits(1).length(), is(SINGLE_FRUIT_STR_LENGTH));
    }

    @Test
    public void testGetFruits()
    {
        final int desiredLength = 5;
        assertThat(getFruits(desiredLength).length(), is(desiredLength * SINGLE_FRUIT_STR_LENGTH));
    }

    @Test
    public void testGetFruitsWithArgsInvalidFruit()
    {
        assertThat(getFruits(5, ""), is(""));
    }

    @Test
    public void testGetFruitsWithArgsSingleForSingleArg()
    {
        final String desiredFruit = "pineapple", desiredFruitEncoding = "\uD83C\uDF4D";
        final String actual = getFruits(1, desiredFruit);
        assertThat(format("the returned length was not %d", SINGLE_FRUIT_STR_LENGTH), actual.length(),
                is(SINGLE_FRUIT_STR_LENGTH));
        assertThat(format("the returned string was not a single %s (%s)", desiredFruit, desiredFruitEncoding),
                desiredFruitEncoding.equals(actual), is(true));
    }

    @Test
    public void testGetFruitsWithArgsSingleForMultipleArgs()
    {
        final String desiredFruit = "pineapple", desiredFruitEncoding = "\uD83C\uDF4D";
        final String desiredFruitOther = "peach", desiredFruitEncodingOther = "\uD83C\uDF51";
        final String actual = getFruits(1, desiredFruit, desiredFruitOther);
        assertThat(format("the returned length was not %d", SINGLE_FRUIT_STR_LENGTH), actual.length(),
                is(SINGLE_FRUIT_STR_LENGTH));
        assertThat(format("the returned string was not a single %s (%s) or %s (%s)", desiredFruit, desiredFruitEncoding,
                desiredFruitOther, desiredFruitEncodingOther),
                desiredFruitEncoding.equals(actual) || desiredFruitEncodingOther.equals(actual), is(true));
    }

    @Test
    public void testGetFruitsWithArgsOneFruit()
    {
        final int desiredLength = 5;
        final String desiredFruit = "pineapple", desiredFruitEncoding = "\uD83C\uDF4D";
        final String actual = getFruits(desiredLength, desiredFruit);
        assertThat(format("the returned length was not %d", desiredLength), actual.length(),
                is(desiredLength * SINGLE_FRUIT_STR_LENGTH));
        assertThat(format("the returned string contained fruits that were not %s (%s)", desiredFruit,
                desiredFruitEncoding), actual.replaceAll(desiredFruitEncoding, ""), is(""));
    }

    @Test
    public void testGetFruitsWithArgs()
    {
        final int desiredLength = 5;
        assertThat(getFruits(desiredLength, "pineapple", "peach").length(),
                is(desiredLength * SINGLE_FRUIT_STR_LENGTH));
    }
}
