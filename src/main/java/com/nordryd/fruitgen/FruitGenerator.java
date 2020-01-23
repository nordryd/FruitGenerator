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
 *
 * @author Nordryd
 */
public class FruitGenerator
{
    private static final String HELP_STR = "list";

    public static void main(final String... args)
    {
        if (args.length < 1)
        {
            quit(false);
        }

        if (!args[0].matches("^\\d+$"))
        {
            quit(HELP_STR.equals(args[0]));
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
            fruitString = getFruits(count);
            out.println(".........");
        }
        else
        {
            final String[] requestedFruitPool = copyOfRange(args, 1, args.length);
            if (!hasFruits(requestedFruitPool))
            {
                quit(false);
            }
            out.println(".........");
            fruitString = getFruits(count, requestedFruitPool);
        }

        clipboard.setContents(new StringSelection(fruitString), null);
        out.println("Fruit generated and copied to clipboard! *dab* ;D");
    }

    private static void quit(final boolean isForListReq)
    {
        if (isForListReq)
        {
            out.println("List of valid fruit values (case does not matter):\n" + new FruitBasket().toString());
            exit(2);
        }
        err.println(
                "Usage: java -jar FruitGenerator.jar [length > 0] [specific fruits (optional)]\nFor a list of valid fruits: java -jar FruitGenerator.jar " +
                        HELP_STR);
        exit(1);
    }
}
