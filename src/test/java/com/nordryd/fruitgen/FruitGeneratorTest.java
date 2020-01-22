package com.nordryd.fruitgen;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;

/**
 * Unit tests for {@link FruitGenerator}
 */
public class FruitGeneratorTest {

    private static final String HELP_STR = "list";
    private static final String USAGE_MSG =
            "Usage: java -jar FruitGenerator.jar [output file] [length > 0] [specific fruits (optional)]\n" + "For a list of valid fruits: java -jar FruitGenerator.jar " + HELP_STR;

    @Rule
    public final ExpectedSystemExit sysExit = ExpectedSystemExit.none();
    @Rule
    public final SystemOutRule sysOut = new SystemOutRule().enableLog();
    @Rule
    public final SystemErrRule sysErr = new SystemErrRule().enableLog();

    @Test
    public void testNoArgsGiven() {
        FruitGenerator.main(new String[0]);
        assertThat(sysErr.getLog(), is(USAGE_MSG));
    }
}
