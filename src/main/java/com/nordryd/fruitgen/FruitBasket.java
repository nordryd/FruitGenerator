package com.nordryd.fruitgen;

import static java.lang.String.format;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Basket sounds cuter than Factory.
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

        return getFruit("peach");
    }

    /**
     * Checks all given fruits exist in the basket.
     *
     * @param fruits the fruits string to validate.
     * @return {@code true} if <i>all</i> fruits are valid.
     */
    public static boolean hasFruits(final String... fruits)
    {
        if (fruits.length == 1)
        {
            return FRUITS.containsKey(fruits[0].toLowerCase());
        }

        for (final String fruit : fruits)
        {
            if (!FRUITS.containsKey(fruit.toLowerCase()))
            {
                return false;
            }
        }

        return true;
    }

    private static String getFruit(final String fruit)
    {
        return FRUITS.get(fruit.toLowerCase());
    }

    @Override
    public String toString()
    {
        final StringBuilder strBuilder = new StringBuilder();
        FRUITS.forEach((fruit, encoding) -> strBuilder.append(format("%s %s\n", fruit, encoding)));
        return strBuilder.toString();
    }

    static
    {
        FRUITS.put("watermelon", "\uD83C\uDF49");
        FRUITS.put("peach", "\uD83C\uDF51");
        FRUITS.put("pineapple", "\uD83C\uDF4D");
    }
}