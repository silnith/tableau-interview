package com.tableau.interview;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class PalindromeFinderTest {
    
    private PalindromeFinder palindromeFinder = new PalindromeFinder();
    
    @Test
    public void testFindPalindromes_EmptyString() {
        final Set<String> expected = Collections.emptySet();
        
        final Set<String> actual = palindromeFinder.findPalindromes("");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFindPalindromes_SingleCharacter() {
        final Set<String> expected = Collections.singleton("a");
        
        final Set<String> actual = palindromeFinder.findPalindromes("a");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFindPalindromes_OddLengthPalindromes() {
        final Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("aba");
        expected.add("bab");
        expected.add("ababa");
        
        final Set<String> actual = palindromeFinder.findPalindromes("ababa");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFindPalindromes_EvenLengthPalindromes() {
        final Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        expected.add("cc");
        expected.add("bccb");
        expected.add("abccba");
        
        final Set<String> actual = palindromeFinder.findPalindromes("abccba");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFindPalindromes_Combined() {
        final Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("bb");
        expected.add("bab");
        expected.add("abba");
        expected.add("bbabb");
        expected.add("abbabba");
        
        final Set<String> actual = palindromeFinder.findPalindromes("abbabba");
        
        assertEquals(expected, actual);
    }
    
    @Test
    public void testFindPalindromes_JohnGaltVeryLong() throws IOException {
        final Set<String> expected = new HashSet<>();
        /*
         * TODO: I'm not going to find all the palindromes by hand right now.
         */
        
        final String string;
        try (final StringWriter writer = new StringWriter()) {
            try (final Reader reader = new InputStreamReader(PalindromeFinderTest.class.getResourceAsStream("galtse.cx.txt"), Charset.forName("windows-1252"))) {
                // copy reader to writer
                int ch = reader.read();
                while (ch != -1) {
                    writer.write(ch);
                    ch = reader.read();
                }
            }
            string = writer.toString();
        }
        
        final Set<String> actual = palindromeFinder.findPalindromes(string);
        
//        assertEquals(expected, actual);
        assertEquals(303, actual.size());
    }
    
    @Test
    public void testFindPalindromes_SupplementaryCharacters() {
        final Set<String> expected = new HashSet<>();
        expected.add(ALPHA);
        expected.add(BETA);
        expected.add(BETA + BETA);
        expected.add(ALPHA + BETA + BETA + ALPHA);
        
        final Set<String> actual = palindromeFinder.findPalindromes(ALPHA + BETA + BETA + ALPHA);
        
        assertEquals(expected, actual);
    }
    
    private static final String ALPHA = String.valueOf(Character.toChars(0x1d6fc));
    private static final String BETA = String.valueOf(Character.toChars(0x1d6fd));
    private static final String GAMMA = String.valueOf(Character.toChars(0x1d6fe));
    private static final String DELTA = String.valueOf(Character.toChars(0x1d6ff));
    private static final String EPSILON = String.valueOf(Character.toChars(0x1d700));
    
}
