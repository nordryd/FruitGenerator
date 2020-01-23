package com.nordryd.fruitgen;

import static java.lang.Integer.parseInt;
import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.util.Arrays;

/**
 * Generates a string of fruit and copies it to the clipboard because why not?
 *
 * @author Nordryd
 */
public class FruitGenerator
{
    private static final String HELP_STR = "list";

    public static void main(final String... args)
    {
        out.println(args.length);
        if (args.length < 1)
        {
            quit(false);
        }

        if (!args[0].matches("^\\d+$"))
        {
            quit(HELP_STR.equals(args[0]));
        }

        final int length = parseInt(args[0]);

        if (length <= 0)
        {
            quit(false);
        }

        final Clipboard CLIPBOARD = Toolkit.getDefaultToolkit().getSystemClipboard();

        if (args.length == 1) // no specifics given, just randomly pick from everything
        {
            //            range(0, length).forEach(index -> out.print(FruitBasket.getFruit()));
        }
        else if (args.length == 2) // one specific was given, just get that one fruit and be done
        {
            if (!FruitBasket.hasFruits(args[1]))
            {
                quit(false);
            }
        }
        else // specific list was given
        {
            if (!FruitBasket.hasFruits(Arrays.copyOfRange(args, 1, args.length)))
            {
                quit(false);
            }
        }
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
