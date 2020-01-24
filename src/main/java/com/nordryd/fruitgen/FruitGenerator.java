package com.nordryd.fruitgen;

import static com.nordryd.fruitgen.FruitBasket.getFruits;
import static com.nordryd.fruitgen.FruitBasket.hasFruits;
import static java.awt.Toolkit.getDefaultToolkit;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.System.err;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.util.Arrays.copyOfRange;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * <p>
 * Main class. Generates a string of fruit and copies it to the clipboard because why not?
 * </p>
 * <p>
 * Coding decisions may or may not have been made to annoy some specific people. You know who you are ;)
 * </p>
 *
 * @author Nordryd
 */
public class FruitGenerator
{
    /**
     * <p>
     * Main method.
     * </p>
     * <p>
     * Usage: {@code java -jar FruitGenerator [length &gt; 0] [specific fruits (optional, separated by spaces)]}
     * </p>
     *
     * @param args command line arguments
     */
    public static void main(final String... args)
    {
        if (args.length < 1)
        {
            quit(QuitReason.INSUFFICIENT_ARGS);
        }

        if (!args[0].matches("^-?\\d+$"))
        {
            quit(("list".equalsIgnoreCase(args[0]) || "help".equalsIgnoreCase(args[0])) ?
                    QuitReason.FOR_LIST_REQ :
                    QuitReason.INVALID_LENGTH);
        }

        final int count = parseInt(args[0]);

        if (count <= 0)
        {
            quit(QuitReason.ZERO_OR_NEG_LENGTH);
        }

        final Clipboard clipboard = getDefaultToolkit().getSystemClipboard();
        final String fruitString;
        if (args.length == 1)
        {
            out.println(Messages.WHALECOME + Messages.GENERATING);
            fruitString = getFruits(count);
        }
        else
        {
            final String[] requestedFruitPool = copyOfRange(args, 1, args.length);
            if (!hasFruits(requestedFruitPool))
            {
                quit(QuitReason.INVALID_FRUIT);
            }
            out.println(Messages.WHALECOME + Messages.GENERATING);
            fruitString = getFruits(count, requestedFruitPool);
        }

        clipboard.setContents(new StringSelection(fruitString), null);
        out.println(Messages.SUCCESS);
    }

    private static void quit(final QuitReason quitReason)
    {
        if (quitReason != QuitReason.FOR_LIST_REQ)
        {
            err.println(ANSIEsc.ITALIC + "" + ANSIEsc.RED + "- OH NOES! Something bad happened! -" + ANSIEsc.RESET);
        }
        err.println(quitReason);
        exit(quitReason.code);
    }

    private enum QuitReason
    {
        FOR_LIST_REQ(2, "\nList of valid fruit values (case does not matter):\n" + new FruitBasket().toString(),
                ANSIEsc.CYAN),
        INVALID_FRUIT(3, "One or more invalid fruits were given"),
        ZERO_OR_NEG_LENGTH(4, "The given length must be at least 1. Why would you use this to NOT use it?"),
        INSUFFICIENT_ARGS(5, "No arguments were given!"),
        INVALID_LENGTH(6, "The length must be a positive integer");

        private final int code;
        private final String message;

        QuitReason(final int code, final String message, final ANSIEsc formatting)
        {
            this.code = code;
            this.message = formatting + ((code == 2) ? message : format("- %s -" + Messages.USAGE, message));
        }

        QuitReason(final int code, final String message)
        {
            this(code, message, ANSIEsc.RED);
        }

        @Override
        public String toString()
        {
            return message;
        }
    }

    private enum ANSIEsc
    {
        RESET("\u001B[0m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        CYAN("\u001B[36m"),
        BOLD("\033[1m"),
        ITALIC("\033[3m");

        private String encoding;

        ANSIEsc(final String encoding)
        {
            this.encoding = encoding;
        }

        @Override
        public String toString()
        {
            return encoding;
        }
    }

    private interface Messages
    {
        String WHALECOME = ANSIEsc.GREEN + "\n" + ANSIEsc.BOLD + "*** WHALECOME TO NORDRYD'S FRUIT GENERATOR ***" +
                "\n- Source code available at: https://github.com/nordryd/FruitGenerator" +
                "\n- pssst... co-workers should DEF check it out (y'all will LUV it uwu)\n" + ANSIEsc.RESET;
        String USAGE = ANSIEsc.BOLD +
                "\n\nUSAGE: java -jar FruitGenerator.jar [length > 0] [specific fruits (optional, separated by space)]" +
                "\n- If no specific fruits are given, then any fruit can be generated." +
                "\n- For a list of valid fruits: java -jar FruitGenerator.jar list" + ANSIEsc.RESET;
        String GENERATING = ANSIEsc.GREEN + "\nGenerating lines of sweet, juicy goodness..." + ANSIEsc.RESET;
        String SUCCESS =
                ANSIEsc.CYAN + "\n" + ANSIEsc.ITALIC + "*** SUCCESS! ***" + ANSIEsc.RESET + "\n" + ANSIEsc.CYAN +
                        "- Fruit generated and copied to clipboard! *dab* ;)" + ANSIEsc.RESET;
    }
}
