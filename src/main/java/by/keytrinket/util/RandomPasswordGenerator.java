package by.keytrinket.util;

import java.util.Random;

/**
 * @author ntishkevich
 */
public class RandomPasswordGenerator {

    private static final String upperCaseChars	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lowerCaseChars 	= "abcdefghijklmnopqrstuvwxyz";
    private static final String digitChars 		= "0123456789";
    private static final String minusChars 		= "-";
    private static final String underlineChars 	= "_";
    private static final String spaceChars 		= " ";
    private static final String specialChars 	= "!\"#$%&'*+,./:;=?@\\^`";
    private static final String bracketChars 	= "[]{}()<>";

    public String generatePassword(int length, boolean upperCase, boolean lowerCase, boolean digits, boolean minus, boolean underline, boolean space, boolean specials, boolean brackets) throws IllegalArgumentException{
        if (length <= 0)
            throw new IllegalArgumentException("Illegal length of future password");

        if (!upperCase && !lowerCase && !digits && !minus && !underline && !space && !specials && !brackets)
            throw new IllegalArgumentException("Illegal types of generated password");

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

    public String getCharacterSet(boolean upperCase, boolean lowerCase, boolean digits, boolean minus, boolean underline, boolean space, boolean specials, boolean brackets) {
        StringBuilder charSet = new StringBuilder();

        if (upperCase)
            charSet.append(upperCaseChars);

        if (lowerCase)
            charSet.append(lowerCaseChars);

        if (digits)
            charSet.append(digitChars);

        if (minus)
            charSet.append(minusChars);

        if (underline)
            charSet.append(underlineChars);

        if (space)
            charSet.append(spaceChars);

        if (specials)
            charSet.append(specialChars);

        if (brackets)
            charSet.append(bracketChars);

        return charSet.toString();
    }
}
