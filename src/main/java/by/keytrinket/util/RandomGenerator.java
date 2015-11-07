package by.keytrinket.util;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Random;

/**
 * Utility class for generating random Strings.
 *
 * @author ntishkevich
 */
public final class RandomGenerator {

    private static final String UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGIT_CHARS = "0123456789";
    private static final String MINUS_CHAR = "-";
    private static final String UNDER_LINE_CHAR = "_";
    private static final String SPACE_CHAR = " ";
    private static final String SPECIAL_CHAR = "!\"#$%&'*+,./:;=?@\\^`";
    private static final String BRACKET_CHARS = "[]{}()<>";
    private static final int DEF_COUNT = 20;

    private RandomGenerator() {
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword(int length, boolean upperCase, boolean lowerCase, boolean digits, boolean minus, boolean underline, boolean space, boolean specials, boolean brackets) throws IllegalArgumentException{
        if (length <= 0)
            throw new IllegalArgumentException("Illegal length of future password");

        if (!upperCase && !lowerCase && !digits && !minus && !underline && !space && !specials && !brackets)
            throw new IllegalArgumentException("Illegal options of generated password");

        String characterSet = getCharacterSet(upperCase, lowerCase, digits, minus, underline, space, specials, brackets);

        int size = characterSet.length();

        StringBuilder builder = new StringBuilder();

        Random random = new Random();
        if (size > 0) {

            for (int i = 0; i < length; i++) {
                char c = characterSet.charAt((char) random.nextInt(size));

                builder.append(c);
            }
        }

        return builder.toString();
    }

    private static String getCharacterSet(boolean upperCase, boolean lowerCase, boolean digits, boolean minus, boolean underline, boolean space, boolean specials, boolean brackets) {
        StringBuilder charSet = new StringBuilder();

        if (upperCase)
            charSet.append(UPPER_CASE_CHARS);

        if (lowerCase)
            charSet.append(LOWER_CASE_CHARS);

        if (digits)
            charSet.append(DIGIT_CHARS);

        if (minus)
            charSet.append(MINUS_CHAR);

        if (underline)
            charSet.append(UNDER_LINE_CHAR);

        if (space)
            charSet.append(SPACE_CHAR);

        if (specials)
            charSet.append(SPECIAL_CHAR);

        if (brackets)
            charSet.append(BRACKET_CHARS);

        return charSet.toString();
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * Generates a reset key.
     *
     * @return the generated reset key
     */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
}
