package com.nequissimus.library.util;

/**
 * Utility for common tasks with strings.
 * @author Tim Steinbach
 */
public final class StringUtil {

    /**
     * Hide the constructor to force access to static methods.
     */
    private StringUtil() {
    }

    /**
     * Multiply a string.
     * @param string String to be multiplied
     * @param times Number of times for string to be repeated
     * @return String * Times
     */
    public static String multiply(final String string, final int times) {

        final StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < times; i++) {

            buffer.append(string);

        }

        return buffer.toString();

    }

    /**
     * This method capitalizes the first letter of the word and turns the rest
     * of it into lower case.<br />
     * It also replaces underscores (_) with spaces. E.g.: fOo becomes Foo, foo
     * becomes Foo, Foo stays Foo, F_oo becomes F oo.<br />
     * For example, this can be used to present values of an enumeration to the
     * user.
     * @param word Word to transform
     * @return Word in name form
     */
    public static String asName(final String word) {

        final StringBuffer result = new StringBuffer();

        if (null != word) {

            final char first = Character.toUpperCase(word.charAt(0));

            // Make sure first is not a symbol
            if (Character.isUpperCase(first)) {

                final String withSpaces =
                    word.substring(1).toLowerCase().replace("_", " ");

                result.append(first).append(withSpaces);

            }

        }

        return result.toString();

    }

}
