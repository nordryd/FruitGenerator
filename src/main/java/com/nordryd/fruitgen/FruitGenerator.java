package com.nordryd.fruitgen;

import static java.lang.Integer.parseInt;
import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

import java.util.Random;

/**
 * Generates a string of fruit because why not?
 *
 * @author Nordryd
 */
public class FruitGenerator {
    private static final Random rng = new Random();
    private static final String HELP_STR = "list";

    public static void main(final String[] args) {
        out.println(args.length);
        if (args.length < 2) {
            fail();
        }

        if (HELP_STR.equals(args[0])) {
            out.println("List of valid fruit values (case does not matter):");
            stream(Fruit.values()).forEach(fruit -> out.printf("%s %s\n", fruit, fruit.toString()));
            exit(0);
        }

        final int length = parseInt(args[1]);

        if (length < 0) {
            fail();
        }

        if (args.length == 2) { // no specifics given, just randomly pick from everything
            range(0, length).forEach(index -> out.print(getFruit()));
        }
        else if (args.length == 3) { // one specific was given, just get that one fruit and be done
            final Fruit fruit = getFruit(args[2]);
            range(0, length).forEach(index -> out.print(fruit));
        }
        else {
            range(0, length).forEach(index -> out.print(getFruit(copyOfRange(args, 2, args.length))));
        }
    }

    private static Fruit getFruit() {
        return getFruit(stream(Fruit.values()).map(Fruit::toString).toArray(String[]::new));
    }

    private static Fruit getFruit(final String... fruitStrs) {
        return Fruit.WATERMELON;
    }

    private static Fruit getFruit(final String fruit) {
        return Fruit.valueOf(fruit.toUpperCase());
    }

    private static void fail() {
        err.println(
                "Usage: java -jar FruitGenerator.jar [output file] [length > 0] [specific fruits (optional)]\nFor a list of valid fruits: java -jar FruitGenerator.jar " + HELP_STR);
        throw new IllegalArgumentException();
    }
}
