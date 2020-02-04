package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitGenerator.getFruitString;
import static com.nordryd.fruitgen.FruitGenerator.main;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.ExpectedSystemExit.none;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * <p>
 * Unit tests for {@link FruitGenerator}.
 * </p>
 *
 * @author Nordryd
 */
public class FruitGeneratorTest
{
    private static final String HELP_STR = "list";

    @Rule
    public final ExpectedSystemExit sysExit = none();

    @Test
    public void testValidInput()
    {
        main("5", "peach");
    }

    @Test
    public void testNoArgsGiven()
    {
        sysExit.expectSystemExitWithStatus(5);
        main();
    }

    @Test
    public void testInvalidLength()
    {
        sysExit.expectSystemExitWithStatus(6);
        main("");
    }

    @Test
    public void testZeroLength()
    {
        sysExit.expectSystemExitWithStatus(4);
        main("0");
    }

    @Test
    public void testNegativeLength()
    {
        sysExit.expectSystemExitWithStatus(4);
        main("-1");
    }

    @Test
    public void testValidLengthInvalidSingleFruit()
    {
        sysExit.expectSystemExitWithStatus(3);
        main("2", "blah");
    }

    @Test
    public void testValidLengthOneValidFruitOneInvalidFruit()
    {
        sysExit.expectSystemExitWithStatus(3);
        main("2", "peach", "blah");
    }

    @Test
    public void test1ArgGiveListRequested()
    {
        sysExit.expectSystemExitWithStatus(2);
        main(HELP_STR);
    }

    @Test
    public void testGetFruitString1Arg()
    {
        assertEquals(2, getFruitString(1).length());
    }

    @Test
    public void testGetFruitString1ArgSingleFruit()
    {
        final String actual = getFruitString(1, "pineapple");
        assertEquals(2, actual.length());
        assertTrue(actual.replaceAll("\uD83C\uDF4D", "").isEmpty());
    }

    @Test
    public void testGetFruitStringMultipleArgSingleFruit()
    {
        final String actual = getFruitString(3, "pineapple", "peach");
        assertEquals(6, actual.length());
        assertTrue(actual.replaceAll("[\uD83C\uDF4D\uD83C\uDF51]", "").isEmpty());
    }

    @Test
    public void testGetFruitStringInvalidFruit()
    {
        sysExit.expectSystemExitWithStatus(3);
        getFruitString(3, "adfaf");
    }

    @Test
    public void testGetFruitStringOneValidFruitAnotherInvalidFruit()
    {
        sysExit.expectSystemExitWithStatus(3);
        getFruitString(3, "peach", "adfaf");
    }
}
