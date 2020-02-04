package com.nordryd.fruitgen;

import static java.util.Arrays.stream;
import static java.util.stream.IntStream.range;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * Handles actual generation of fruit emojis. Basket sounds cuter than Factory.
 * </p>
 *
 * @author Nordryd
 */
public class FruitBasket
{
    private static final Map<String, String> FRUITS = new HashMap<>();
    private static final Random RNG = new Random();

    /**
     * Generates a string of fruits of the desired length. Randomly picks from all possible fruits.
     *
     * @param length the desired length of the string.
     * @return a string of randomly generated fruit.
     */
    public static String getFruits(final int length)
    {
        return (length <= 0) ? "" : getFruits(length, FRUITS.keySet().toArray(new String[0]));
    }

    /**
     * Generates a string of fruits of the desired length. Randomly picks from <i>only the given</i> fruits.
     *
     * @param length the desired length of the string.
     * @param fruits the specific pool of fruits to randomly pick from.
     * @return a string of randomly generated fruit.
     */
    public static String getFruits(final int length, final String... fruits)
    {
        if ((length <= 0) || !hasFruits(fruits))
        {
            return "";
        }

        final StringBuilder strBuilder = new StringBuilder();
        range(0, length).forEach(index -> strBuilder.append(getFruit(fruits[RNG.nextInt(fruits.length)])));
        return strBuilder.toString();
    }

    /**
     * Checks if all given fruits are valid.
     *
     * @param fruits the fruit strings to validate.
     * @return {@code true} if <i>all</i> fruits are valid.
     */
    public static boolean hasFruits(final String... fruits)
    {
        return stream(fruits)
                .allMatch(fruit -> "apple".equalsIgnoreCase(fruit) || FRUITS.containsKey(fruit.toLowerCase()));
    }

    @Override
    public String toString()
    {
        final StringBuilder strBuilder = new StringBuilder();
        FRUITS.forEach((fruit, encoding) -> strBuilder.append(fruit).append("\n"));
        return strBuilder.append("apple (will randomly give a red or green one)").toString();
    }

    private static String getFruit(final String fruit)
    {
        return FRUITS.get("apple".equalsIgnoreCase(fruit) ?
                (RNG.nextBoolean() ? "redapple" : "greenapple") :
                fruit.toLowerCase());
    }

    static
    {
        FRUITS.put("grapes",        "\uD83C\uDF47");
        FRUITS.put("melon",         "\uD83C\uDF48");
        FRUITS.put("watermelon",    "\uD83C\uDF49");
        FRUITS.put("orange",        "\uD83C\uDF4A");
        FRUITS.put("lemon",         "\uD83C\uDF4B");
        FRUITS.put("banana",        "\uD83C\uDF4C");
        FRUITS.put("pineapple",     "\uD83C\uDF4D");
        FRUITS.put("mango",         "\uD83E\uDD6D");
        FRUITS.put("redapple",      "\uD83C\uDF4E");
        FRUITS.put("greenapple",    "\uD83C\uDF4F");
        FRUITS.put("pear",          "\uD83C\uDF50");
        FRUITS.put("peach",         "\uD83C\uDF51");
        FRUITS.put("cherry",        "\uD83C\uDF52");
        FRUITS.put("strawberry",    "\uD83C\uDF53");
        FRUITS.put("kiwi",          "\uD83E\uDD5D");
        FRUITS.put("tomato",        "\uD83C\uDF45");
        FRUITS.put("coconut",       "\uD83E\uDD65");
    }
}
