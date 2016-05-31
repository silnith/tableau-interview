package com.tableau.interview;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PalindromeFinder {
    
    public Set<String> findPalindromes(final String source) {
        final Set<String> solutions = new HashSet<>();
        final String canonicalString = Normalizer.normalize(source, Normalizer.Form.NFC);
        final List<Integer> codePoints = convertToCodePoints(canonicalString);
        for (int i = 0; i < codePoints.size(); i++) {
            /*
             * Starting at code point cp[i], find the longest palindrome with
             * index i at its center.
             */
            int offset = 0;
            /*
             * Must use .equals instead of == because this does not auto-unbox
             * unless we assign it to type integer, which we never do.  And for
             * high characters (anything outside byte range, e.g. not ASCII) the
             * auto-boxing creates new instances so the Integers are not
             * reference-equals.
             */
            while (codePoints.get(i - offset).equals(codePoints.get(i + offset))) {
                final List<Integer> subList = codePoints.subList(i - offset, i + offset + 1);
                final String substring = convertToString(subList);
                solutions.add(substring);
                
                offset++;
                if (i - offset < 0) {
                    break;
                }
                if (i + offset >= codePoints.size()) {
                    break;
                }
            }
        }
        for (int i = 0; i + 1 < codePoints.size(); i++) {
            /*
             * Starting with code points cp[i] and cp[i + 1], find the longest
             * palindrome symmetrical around the halfway point between index
             * i and i + 1.
             */
            int offset = 0;
            /*
             * Must use .equals instead of == because this does not auto-unbox
             * unless we assign it to type integer, which we never do.  And for
             * high characters (anything outside byte range, e.g. not ASCII) the
             * auto-boxing creates new instances so the Integers are not
             * reference-equals.
             */
            while (codePoints.get(i - offset).equals(codePoints.get(i + 1 + offset))) {
                final List<Integer> subList = codePoints.subList(i - offset, i + 2 + offset);
                final String substring = convertToString(subList);
                solutions.add(substring);
                
                offset++;
                if (i - offset < 0) {
                    break;
                }
                if (i + 1 + offset >= codePoints.size()) {
                    break;
                }
            }
        }
        
        return solutions;
    }

    private List<Integer> convertToCodePoints(final String str) {
        final List<Integer> codePoints = new ArrayList<>(str.codePointCount(0, str.length()));
        int index = 0;
        while (index < str.length()) {
            final int codePoint = str.codePointAt(index);
            codePoints.add(codePoint);
            index = str.offsetByCodePoints(index, 1);
        }
        return codePoints;
    }
    
    private String convertToString(final List<Integer> codePoints) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final int codePoint : codePoints) {
            final char[] chars = Character.toChars(codePoint);
            stringBuilder.append(chars);
        }
        return stringBuilder.toString();
    }

}
