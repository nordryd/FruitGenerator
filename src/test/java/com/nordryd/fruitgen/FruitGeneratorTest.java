package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitGenerator.main;
import static org.junit.contrib.java.lang.system.ExpectedSystemExit.none;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

/**
 * Unit tests for {@link FruitGenerator}
 *
 * @author Nordryd
 */
public class FruitGeneratorTest
{
    private static final String HELP_STR = "list";

    @Rule
    public final ExpectedSystemExit sysExit = none();
    @Rule
    public final SystemOutRule sysOut = new SystemOutRule().enableLog();

    @Test
    public void testNoArgsGiven()
    {
        sysExit.expectSystemExitWithStatus(1);
        main();
    }

    @Test
    public void testInvalidLength()
    {
        sysExit.expectSystemExitWithStatus(1);
        main("");
    }

    @Test
    public void testZeroLength()
    {
        sysExit.expectSystemExitWithStatus(1);
        main("0");
    }

    @Test
    public void testNegativeLength()
    {
        sysExit.expectSystemExitWithStatus(1);
        main("-1");
    }

    @Test
    public void testValidLengthInvalidSingleFruit()
    {
        sysExit.expectSystemExitWithStatus(1);
        main("2", "blah");
    }

    @Test
    public void testValidLengthOneValidFruitOneInvalidFruit()
    {
        sysExit.expectSystemExitWithStatus(1);
        main("2", "peach", "blah");
    }

    @Test
    public void test1ArgGiveListRequested()
    {
        sysExit.expectSystemExitWithStatus(2);
        main(HELP_STR);
    }
}
