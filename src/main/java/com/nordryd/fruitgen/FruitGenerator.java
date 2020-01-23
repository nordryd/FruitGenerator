package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitBasket.getFruits;
import static com.nordryd.fruitgen.FruitBasket.hasFruits;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Integer.parseInt;
import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.util.Arrays.copyOfRange;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Main class. Generates a string of fruit and copies it to the clipboard because why not?
 * Coding decisions may or may not have been made to make some people tick. You know who you are ;)
 *
 * @author Nordryd
 */
public class FruitGenerator
{
    public static void main(final String... args)
    {
        out.println("\n***WHALECOME TO NORDRYD'S FRUIT GENERATOR***\n");
        out.println("*Source code available at: https://github.com/nordryd/FruitGenerator*");
        out.println("*pssst... co-workers should DEFINITELY check it out (y'all will LUV it :P)*\n");
        if (args.length < 1)
        {
            quit(false);
        }

        if (!args[0].matches("^\\d+$"))
        {
            quit("list".equalsIgnoreCase(args[0]) || "help".equalsIgnoreCase(args[0]));
        }

        final int count = parseInt(args[0]);

        if (count <= 0)
        {
            quit(false);
        }

        final Clipboard clipboard = getDefaultToolkit().getSystemClipboard();
        final String fruitString;
        if (args.length == 1)
        {
            out.println("Generating lines of sweet, juicy goodness...");
            fruitString = getFruits(count);
        }
        else
        {
            final String[] requestedFruitPool = copyOfRange(args, 1, args.length);
            if (!hasFruits(requestedFruitPool))
            {
                quit(false);
            }
            out.println("Generating lines of sweet, juicy goodness...");
            fruitString = getFruits(count, requestedFruitPool);
        }

        clipboard.setContents(new StringSelection(fruitString), null);
        out.println("Fruit generated and copied to clipboard! *dab* ;D");
    }

    private static void quit(final boolean isForListReq)
    {
        err.println("USAGE: java -jar FruitGenerator.jar [length > 0] [specific fruits (optional)]");
        err.println("Will pick from all possible fruits if none are specified.");
        err.println("For a list of valid fruits: java -jar FruitGenerator.jar list");
        if (isForListReq)
        {
            out.println("\nList of valid fruit values (case does not matter):\n" + new FruitBasket().toString());
            exit(2);
        }
        exit(1);
    }
}
