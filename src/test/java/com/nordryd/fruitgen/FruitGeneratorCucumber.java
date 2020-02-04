package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitGenerator.getFruitString;
import static java.lang.System.out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * <p>
 * Cucumber tests for the {@code FruitGenerator} application.
 * </p>
 *
 * @author Nordryd
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = { "classpath:features" })
public class FruitGeneratorCucumber implements En
{
    private String[] fruitPool;
    private String returnedString;

    public FruitGeneratorCucumber()
    {
        Before((final Scenario scenario) -> out.println("**** " + scenario.getName() + " ****"));

        Given("^the user wants any fruit$", () -> fruitPool = new String[0]);

        Given("^the user wants only pineapples$", () -> fruitPool = new String[] { "pineapple" });

        Given("^the user wants pineapples, coconuts, and lemons$",
                () -> fruitPool = new String[] { "pineapple", "coconut", "lemon" });

        Given("^the user wants (either red or green|only red|only green) apples$", (final String appleType) -> {
            fruitPool = new String[] {
                    (appleType.contains("only") ? appleType.replaceAll("only ", "") : "") + "apple" };
        });

        When("^the user asks for (\\d+) fruits$",
                (final Integer amount) -> returnedString = getFruitString(amount, fruitPool));

        Then("^the program returns a string with (\\d+) fruits$",
                (final Integer expectedAmount) -> assertEquals(expectedAmount * 2, returnedString.length()));

        Then("^the string contains only pineapples$",
                () -> assertTrue(returnedString.replaceAll("\uD83C\uDF4D", "").isEmpty()));

        Then("^the string contains pineapples, coconuts, and lemons$",
                () -> assertTrue(returnedString.replaceAll("[\uD83C\uDF4D\uD83E\uDD65\uD83C\uDF4B]", "").isEmpty()));

        Then("^the string contains (either red or green|only red|only green|) apples", (final String appleType) -> {
            final String redApple = "\uD83C\uDF4E", greenApple = "\uD83C\uDF4F";
            final String regex = appleType.contains("only") ?
                    (appleType.contains("red") ? redApple : greenApple) :
                    "[" + redApple + greenApple + "]";
            assertTrue(returnedString.replaceAll(regex, "").isEmpty());
        });
    }
}
