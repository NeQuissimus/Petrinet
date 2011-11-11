package com.nequissimus.university.k1584.util;

/**
 * Class that provides common methods when working with {@link String}.
 * @author Tim Steinbach
 */
public final class StringUtils {

    /**
     * Hide constructor.
     */
    private StringUtils() {
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
