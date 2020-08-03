package org.dicio.dicio_android.util;

import java.util.Iterator;
import java.util.List;

public class StringUtils {

    private StringUtils() {
    }

    public static String join(final String delimiter, final List<String> strings) {
        final StringBuilder builder = new StringBuilder();
        final Iterator<String> iterator = strings.iterator();

        if (iterator.hasNext()) {
            builder.append(iterator.next());
        }
        while (iterator.hasNext()) {
            builder.append(delimiter);
            builder.append(iterator.next());
        }

        return builder.toString();
    }

    public static String join(final List<String> strings) {
        return join(" ", strings);
    }


    /**
     * Finds the
     * <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">Levenshtein distance</a>
     * between two strings, that is the number of characters that need to be changed to turn one
     * string into the other. Letter case is ignored.
     * @param a the first string
     * @param b the second string
     * @return the Levenshtein distance between the two strings
     */
    public static int levenshteinDistance(final String a, final String b) {
        // memory already filled with zeros, as it's the default value for int
        int[][] memory = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); ++i) {
            memory[i][0] = i;
        }
        for (int j = 0; j <= b.length(); ++j) {
            memory[0][j] = j;
        }

        for (int i = 0; i < a.length(); ++i) {
            for (int j = 0; j < b.length(); ++j) {
                int substitutionCost = Character.toLowerCase(a.codePointAt(i))
                        == Character.toLowerCase(b.codePointAt(j)) ? 0 : 1;
                memory[i+1][j+1] = Math.min(Math.min(memory[i][j+1] + 1, memory[i+1][j] + 1),
                        memory[i][j] + substitutionCost);
            }
        }

        return memory[a.length()][b.length()];
    }
}