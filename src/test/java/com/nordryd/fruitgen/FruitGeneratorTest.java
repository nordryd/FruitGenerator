package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitGenerator.main;
import static org.junit.contrib.java.lang.system.ExpectedSystemExit.none;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * <p>
 * Unit tests for {@link FruitGenerator}
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
}
