//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: (descriptive title of the program making use of this file)
// Files: (a list of all source files used by that program)
// Course: CS 200, Fall 2018
//
// Author: Yash Sukhwani
// Email: sukhwani@wsic.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do. If you received no outside help from either type of
// source, then please explicitly indicate NONE.
//
// Persons: (identify each person and describe their help in detail)
// Online Sources: (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * @author yashsukhwani
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as they are developed.
 * These methods are private since they are only intended for use within this class.
 * 
 * @author Jim Williams
 * @author Yash Sukhwani
 *
 */

public class ElizaTests {

    /**
     * This is the main method that runs the various tests. Uncomment the tests when you are ready
     * for them to run.
     * 
     * @param args (unused)
     */
    public static void main(String[] args) {

        // Milestone 1: Process User Input
        // M1: main nothing to do
        testSeparatePhrases();
        testFoundQuitWord();
        testSelectPhrase();
        testReplaceWord();
        testAssemblePhrase();

        // Milestone 2:
        // M2: implement parts of main as described in main method comments
        testSwapWords();
        testPrepareInput();
        testLoadResponseTable();

        // Milestone 3:
        // main: implement the rest of main as described in the main method comments
        testFindKeyWordsInPhrase();
        testSelectResponse();
        testInputAndResponse();
        testSaveDialog();
    }

    /**
     * This runs some tests on the separatePhrases method. 
     * Test 1 checks with phrase: "No.  I'm talking  to my dog! Bye."
     * Test 2 checks with phrase: "What? This isn't the 4th messy-sentence!"
     * Test 3 checks with phrase: "NO"
     * Test 4 checks with phrase: "this tab"
     * Test 5 checks with phrase: "Thank you, but no, I have to go. Goodbye!!!"
     * Test 6 check with null argument.
     */
    private static void testSeparatePhrases() {
        boolean error = false;

        // 1.

        {

            // phrases stores the result when separatePhrases is called.
            ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
            ArrayList<String> expected = new ArrayList<>(); // what separatePhrases should return
            expected.add("no");
            expected.add("i'm talking to my dog");
            expected.add("bye");

            if (phrases.size() != expected.size()) { 
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }

            for (int i = 0; i < expected.size(); i++) { // checking is all results are correct
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }

        }

        // -------------------

        // 2.

        {

            ArrayList<String> phrases =
                Eliza.separatePhrases("What? This isn't the 4th messy-sentence!");
            ArrayList<String> expected = new ArrayList<>();
            expected.add("what");
            expected.add("this isn't the 4th messy sentence");


            if (phrases.size() != expected.size()) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }

            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }

        }

        // -------------------

        // 3.

        {

            ArrayList<String> phrases = Eliza.separatePhrases("NO");
            ArrayList<String> expected = new ArrayList<>();
            expected.add("no");

            if (phrases.size() != expected.size()) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }

            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }

        }

        // -------------------

        // 3.

        {

            ArrayList<String> phrases = Eliza.separatePhrases("this tab");
            ArrayList<String> expected = new ArrayList<>();
            expected.add("this tab");

            if (phrases.size() != expected.size()) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }

            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }

        }

        // -------------------

        // 4.

        {

            ArrayList<String> phrases = Eliza.separatePhrases("What?");
            ArrayList<String> expected = new ArrayList<>();
            expected.add("what");

            if (phrases.size() != expected.size()) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }

            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }

        }

        // -------------------

        // 5.
        
        {

            ArrayList<String> phrases =
                Eliza.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
            ArrayList<String> expected = new ArrayList<>();
            expected.add("thank you");
            expected.add("but no");
            expected.add("i have to go");
            expected.add("goodbye");

            if (phrases.size() != expected.size()) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected.size()
                    + " but found " + phrases.size() + " phrases.");
            }

            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(phrases.get(i))) {
                    error = true;
                    System.out.println("testSeparatePhrases: phrases not the same");
                    System.out.println("  " + expected.get(i));
                    System.out.println("  " + phrases.get(i));
                }
            }

        }

        // -------------------
        
        // 6.
        
        {

            ArrayList<String> phrases = Eliza.separatePhrases(null);
            ArrayList<String> expected = null;

            if (phrases != expected) {
                error = true;
                System.out.println("testSeparatePhrases: expected " + expected
                    + " but found " + phrases + " phrases.");
            }

        }
        
        // ---------------

        if (error) {
            System.out.println("testSeparatePhrases failed");
        } else {
            System.out.println("testSeparatePhrases passed");
        }
    }

    /**
     * This runs some tests on the foundQuitWord method. 
     * Test 1 checks with input: [thank you for talking, bye]
     * Test 2 checks with input: [I said hi and she said bye]
     * Test 3 checks with input: [goodbye]
     * Test 4 checks with input: [happy birthday]
     * Test 5 checks with input: [seeya tomorrow]
     * Test 6 checks with null argument.
     */

    private static void testFoundQuitWord() {
        boolean error = false;

        // 1.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("thank you for talking");
            phrases.add("bye");

            boolean quit = Eliza.foundQuitWord(phrases);
            if (!quit) {
                error = true;
                System.out.println("testFoundQuitWord 1: failed");
            }

        }

        // ---------------

        // 2.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("I said hi and she said bye");

            boolean quit = Eliza.foundQuitWord(phrases);
            if (quit) {
                error = true;
                System.out.println("testFoundQuitWord 2: failed");
            }

        }

        // --------------

        // 3.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("goodbye");

            boolean quit = Eliza.foundQuitWord(phrases);
            if (!quit) {
                error = true;
                System.out.println("testFoundQuitWord 3: failed");
            }

        }

        // --------------

        // 4.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("happy birthday");

            boolean quit = Eliza.foundQuitWord(phrases);
            if (quit) {
                error = true;
                System.out.println("testFoundQuitWord 4: failed");
            }

        }

        // --------------

        // 5.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("seeya tomorrow");

            boolean quit = Eliza.foundQuitWord(phrases);
            if (quit) {
                error = true;
                System.out.println("testFoundQuitWord 5: failed");
            }

        }

        // --------------
        
        // 6.
        
        {

            ArrayList<String> phrases = null;

            boolean quit = Eliza.foundQuitWord(phrases);
            if (quit) {
                error = true;
                System.out.println("testFoundQuitWord 6: failed");
            }

        }
        
        // ---------------

        if (error) {
            System.err.println("testFoundQuitWord failed");
        } else {
            System.out.println("testFoundQuitWord passed");
        }
    }

    /**
     * This runs some tests on the selectPhrase method. 
     * Test 1 checks with input: [no, sometimes I remember being on a boat, not often]
     * Test 2 checks with input: [Friday, is my favourite, day of the week]
     * Test 3 checks with input: [""]
     * Test 4 checks with null argument.
     */
    private static void testSelectPhrase() {
        boolean error = false;

        {

            // 1.

            // choose the longest
            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("no");
            phrases.add("sometimes I remember being on a boat");
            phrases.add("not often");
            String choice = Eliza.selectPhrase(phrases);
            if (!choice.equals("sometimes I remember being on a boat")) {
                error = true;
                System.out.println("testSelectPhrase 1: failed");
            }

        }

        // ----------------------

        // 2.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("Friday");
            phrases.add("is my favourite");
            phrases.add("day of the week");
            String choice = Eliza.selectPhrase(phrases);
            if (!choice.equals("is my favourite")) {
                error = true;
                System.out.println("testSelectPhrase 2: failed");
            }

        }

        // ----------------------

        // 3.

        {

            ArrayList<String> phrases = new ArrayList<>();
            phrases.add("");
            String choice = Eliza.selectPhrase(phrases);
            if (!choice.equals("")) {
                error = true;
                System.out.println("testSelectPhrase 3: failed");
            }

        }

        // ------------------------

        // 4.

        {

            ArrayList<String> phrases = null;
            String choice = Eliza.selectPhrase(phrases);
            if (!choice.equals("")) {
                error = true;
                System.out.println("testSelectPhrase 4: failed");
            }

        }

        // ------------------------

        if (error) {
            System.err.println("testSelectPhrase failed");
        } else {
            System.out.println("testSelectPhrase passed");
        }
    }

    /**
     * This runs some tests on the assemblePhrase method. 
     * Test 1 checks with the input: {"This", "is a", "sentence"}
     * Test 2 checks with an empty array input.
     * Test 3 checks with the input: {"This", "must", "be", "a", "sentence"}
     * Test 4 checks with a null argument.
     */
    private static void testAssemblePhrase() {
        boolean error = false;

        // 1. 
        
        {
        
        String[] words = {"This", "is a", "sentence"};
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This is a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
        }
        
        }

        // ---------------
        
        // 2.

        {
        
        String[] words = {};
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "";

        if (!expectedSentence.equals(sentence)) {
            error = true;
            System.out.println("testAssembleSentence 2 failed '" + sentence + "'");
        }
        
        }

        // ---------------
        
        // 3.
        
        {

        String[] words = {"This", "must", "be", "a", "sentence"};
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "This must be a sentence";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 3 failed '" + sentence + "'");
        }
        
        }

        // ---------------
        
        // 4.
        
        {

        String[] words = null;
        String sentence = Eliza.assemblePhrase(words);
        String expectedSentence = "";

        if (!sentence.equals(expectedSentence)) {
            error = true;
            System.out.println("testAssembleSentence 4 failed '" + sentence + "'");
        }

        }
        
        // ---------------

        if (error) {
            System.err.println("testAssemblePhrase failed");
        } else {
            System.out.println("testAssemblePhrase passed");
        }
    }

    /**
     * This runs some tests on the findKeyWordsInPhrase method.
     * Test 1 checks with the input: [computer], {"are", "you", "a", "computer"}
     * Test 2 checks with the input: [computer], {"computer", "are", "you"}
     * Test 3 checks with the input: 
     * [computer], {"does", "that", "computer", "on", "your", "desk", "work"}
     * Test 4 checks with the input: [you, me], {"why", "don't", "you", "like", "me"}
     * Test 5 checks with the input: [I, have], { "have", "I", "reached", "home" }
     * Test 6 checks with the input: [you, me], { "me", "don't", "like", "you" }
     * Test 7 checks with the input: null, { "me", "don't", "like", "you" }
     * Test 8 checks with the input: [you, me], null
     */
    private static void testFindKeyWordsInPhrase() {
        boolean error = false;

        {// block so each test has its own variable scope.
         // 1.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"are", "you", "a", "computer"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);

            if (matches == null || matches.length != 2 || !matches[0].equals("are you a")
                || !matches[1].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 1 failed.");

            }
        }

        {
            // 2.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"computer", "are", "you"};

            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("")
                || !matches[1].equals("are you")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 2 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 3.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("computer");
            String[] phrase = {"does", "that", "computer", "on", "your", "desk", "work"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 2 || !matches[0].equals("does that")
                || !matches[1].equals("on your desk work")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 3 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 4.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] phrase = {"why", "don't", "you", "like", "me"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
            if (matches == null || matches.length != 3 || !matches[0].equals("why don't")
                || !matches[1].equals("like") || !matches[2].equals("")) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 4 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 5.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("I");
            keywords.add("have");
            String[] sentence = {"have", "I", "reached", "home"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 5 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 6.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = {"me", "don't", "like", "you"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (matches != null) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 6 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 7.
            ArrayList<String> keywords = null;
            String[] sentence = {"me", "don't", "like", "you"};
            String[] expected = {"me don't like you"};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (!(matches[0].equals(expected[0]))) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 7 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        {
            // 8.
            ArrayList<String> keywords = new ArrayList<String>();
            keywords.add("you");
            keywords.add("me");
            String[] sentence = null;
            String[] expected = {""};
            String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
            if (!(matches[0].equals(expected[0]))) {
                error = true;
                System.out.println("testFindKeyWordsInPhrase 8 failed.");
                System.out.println(Arrays.toString(matches));
            }
        }

        if (error) {
            System.err.println("testFindKeyWordsInPhrase failed");
        } else {
            System.out.println("testFindKeyWordsInPhrase passed");
        }
    }
    /**
     * This runs some tests on the saveDialog method. 
     * Test 1 checks when there is a single line input.
     * Test 2 checks when multiple lines are input.
     */
    private static void testSaveDialog() {
        boolean error = false;
        final String TEST_FILENAME = "testing.txt";
        { // 1.
            ArrayList<String> list = new ArrayList<>();
            list.add("this is a single line.");
            try {
                Eliza.saveDialog(list, TEST_FILENAME);
                String readFromFile = readFile(TEST_FILENAME);
                if (!readFromFile.equals(list.get(0) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 1 failed.");
                    System.out.println("content read: " + readFromFile);
                }
                removeFile(TEST_FILENAME);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        {// 2
            final String Test_FILENAME2 = "testing1.txt";
            ArrayList<String> list1 = new ArrayList<>();
            list1.add("this is a single line");
            list1.add("this is the second line i am adding");


            try {
                Eliza.saveDialog(list1, Test_FILENAME2);

                String readFromFile = readFile(Test_FILENAME2);

                if (!readFromFile.equals(list1.get(0) + "\n" + list1.get(1) + "\n")) {
                    error = true;
                    System.out.println("testSaveDialog 2 failed.");
                    System.out.println("content read: " + readFromFile);
                }

                removeFile(Test_FILENAME2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (error) {

                System.err.println("testSaveDialog failed");

            } else {

                System.out.println("testSaveDialog passed");

            }
        }


    }

    /**
     * Supporting method for testSaveDialog
     * 
     * @param fileName name of the file to read
     * @return the contents of the file
     */
    private static String readFile(String fileName) {
        StringBuffer buf = new StringBuffer();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
            String line;
            while ((line = reader.readLine()) != null) {
                buf.append(line);
                buf.append("\n");
            }
            return buf.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Supporting method for testSaveDialog.
     * 
     * @param filename file to be removed.
     */
    private static void removeFile(String filename) {
        File file = new File(filename);
        try {
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This runs some tests on the replaceword method. 
     * Test 1 checks with the input: machine
     * Test 2 checks with the input: yourself
     * Test 3 checks with the input: mom
     * Test 4 checks with the input: same
     * Test 5 checks with the input: you
     * Test 6 checks with a null argument.
     */
    private static void testReplaceWord() {
        boolean error = false;

        { // 1.
            String word = "machine";
            String expected = "computer";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 2.
            String word = "yourself";
            String expected = "myself";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 3.
            String word = "mom";
            String expected = "mother";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 3 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 4.
            String word = "same";
            String expected = "alike";
            String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 4 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 5.
            String word = "you";
            String expected = "I";
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println("testReplaceWord 5 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        { // 6.
            String word = null;
            String expected = null;
            String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
            if (!(result == null)) {
                error = true;
                System.out.println("testReplaceWord 6 failed. result:'" + result + "' expected:'"
                    + expected + "'");
            }
        }

        if (error) {
            System.err.println("testReplaceWord failed");
        } else {
            System.out.println("testReplaceWord passed");
        }
    }

    /**
     * This runs some tests on the swapWords method.
     * Test 1 checks with the input: "i'm cant recollect"
     * Test 2 checks with the input: "i'm happy"
     * Test 3 checks with the input: "about my dog"
     * Test 4 checks with the input: "i want to sleep"
     * Test 5 checks with the input: "is that your car?"
     * Test 6 checks with the input: "i feel great today"
     * Test 7 checks with a null argument.
     */
    private static void testSwapWords() {
        boolean error = false;

        { // 1.
            String someWords = "i'm cant recollect";
            String expected = "i am cannot remember";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 1 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 2.
            String someWords = "i'm happy";
            String expected = "you are happy";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 2 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 3.
            String someWords = "about my dog";
            String expected = "about your dog";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 3 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 4.
            String someWords = "i want to sleep";
            String expected = "i desire to sleep";
            String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 4 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 5.
            String someWords = "is that your car?";
            String expected = "is that my car?";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 5 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 6.
            String someWords = "i feel great today";
            String expected = "you feel great today";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 6 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        { // 7.
            String someWords = null;
            String expected = "";
            String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
            if (result == null || !result.equals(expected)) {
                error = true;
                System.out.println(
                    "testSwapWords 7 failed. result:'" + result + "' expected:'" + expected + "'");
            }
        }

        if (error) {
            System.err.println("testSwapWords failed");
        } else {
            System.out.println("testSwapWords passed");
        }
    }

    /**
     * This runs some tests on the selectResponse method. 
     * 
     * This test checks the select response method with different arguments.
     * Each test has been numbered and has an explanation accompanied with it
     */
    
    private static void testSelectResponse() {
        boolean error = false;

        { // 1.
          // is one of the 3 strings chosen?
            Random randGen = new Random(434);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("The");
            strList.add("happy");
            strList.add("cat");
            String choice = Eliza.selectResponse(randGen, strList);

            if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
                error = true;
                System.out.println("testSelectResponse 1 failed.");
            }
        }

        { // 2.
          // if called 1000 times, are the choices approximately random?
            Random randGen = new Random(765);
            ArrayList<String> strList = new ArrayList<>();
            strList.add("this");
            strList.add("is");
            strList.add("a");
            strList.add("list");
            strList.add("to");
            strList.add("choose");
            strList.add("from");
            int[] actualCount = new int[strList.size()];
            int[] expectedCount = new int[] {156, 146, 142, 138, 160, 130, 128};
            for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
                String choice = Eliza.selectResponse(randGen, strList);
                for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
                    if (choice.equals(strList.get(wordIndex))) {
                        actualCount[wordIndex]++;
                    }
                }
            }

            // since we set a seed on the random number generator we should know the expected result

            for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
                if (actualCount[countIndex] != expectedCount[countIndex]) {
                    error = true;
                    System.out.println("testSelectResponse 2 failed.");
                    System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
                    System.out.println("  actualCount: " + Arrays.toString(actualCount));
                }
            }

        }

        {
            {   // 3.Checks whether the response returned is the expected one
                Random randGen = new Random(123);
                ArrayList<String> strList = new ArrayList<>();
                strList.add("The happy cat");

                String choice = Eliza.selectResponse(randGen, strList);

                if (!(choice.equals("The happy cat"))) {
                    error = true;
                    System.out.println("testSelectResponse 3 failed.");

                }
            }
            {
                {// 4.Checks whether it returns a null for the arguments it should.
                    Random randGen = new Random(123);
                    ArrayList<String> strList = new ArrayList<>();

                    String choice = Eliza.selectResponse(randGen, strList);

                    if (choice != null) {
                        error = true;
                        System.out.println("testSelectResponse 4 failed.");

                    }
                }
            }

        }
        if (error) {
            System.err.println("testSelectResponse failed");
        } else {
            System.out.println("testSelectResponse passed");
        }
    }
    
    /**
     * This method checks and tests the prepareInput method with various arguments
     * Each test has a corresponding number which describes how it checks and tests the 
     * methods. The test methods are as follows:
     * 1.This test checks whether a quit word put in as an argument for the method returns
     * a null character
     * 2.This test checks whether the prepareInput correctly returns the correct string 
     * array. This string also contains a quit word.
     */
    private static void testPrepareInput() {
        boolean error = false;

        { // 1.
            String input = "bye";
            String[] result = null;
            result = Eliza.prepareInput("bye");
            if (result != null) {
                error = true;
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        {// 2
            String input = "I said goodbye";
            String[] result = null;
            result = Eliza.prepareInput("I said goodbye");

            if (!(result[0].equals("i") || !result[1].equals("said") || !result.equals("goodbye"))){
                error = true;
            }
            if (error) {
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }
        {// 3
            String input = "I can't do that";
            String[] result = null;
            result = Eliza.prepareInput("I can't do that");

            if (!(result[0].equals("i") || !result[1].equals("cannot") || !result.equals("do")
                    || !result.equals("that"))) {
                error = true;
            }
            
            if (error) {
                System.out.println("testPrepareInput 1: failed");
                System.out.println("  input: " + input);
                System.out.println("  result: " + Arrays.toString(result));
            }
        }

        if (error) {
            System.err.println("testPrepareInput failed");
        } else {
            System.out.println("testPrepareInput passed");
        }
    }
    
    /**
     * 
     * This runs some tests on the loadResponseTable method. 1. TODO describe each test in your own
     * words. 2.
     * 
     * This test method tests and checks the loadResponseTable method present in Eliza
     * Each test has a corresponding number which will tell us what exactly the test does.
     * The test are as follows:
     * 1.This test checks whether the first element of the arraylist is equal to the first keyword
     * 2.This test checks whether the number is elements in the arraylist are even or not
     * 3.This test checks whether there are 6 responses for the keyword "computer"
     * 4.This test checks whether there are 2 responses for keyword "name"
     * 5.This test iterates through the arraylist and checks that there are no bank lines
     * 6.This test method checks whether last line of the Eliza.rsp has been loaded into 
     * the last index of the arraylist
     */
    private static void testLoadResponseTable() {
        boolean error = false;

        { // 1.
            ArrayList<String> expected1stRow = new ArrayList<String>();
            expected1stRow.add("computer");
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");

            if (!table.get(0).equals(expected1stRow)) {
                error = true;
                System.out.println("testLoadResponseTable 1: failed");
                System.out.println("  expected1stRow: " + expected1stRow);
                System.out.println("  table1stRow: " + table.get(0));
            }

            // 2.
            if (table.size() % 2 != 0) {
                error = true;
                System.out.println("testLoadResponseTable 2: failed");
                System.out.println(
                    "  expected an even number of elements in table but found: " + table.size());
            }
        }
        {// 3 checking whether there are 6 responses for the keyword "computer"
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            if (table.get(1).size() != 6) {
                error = true;
            }
            // 4 checking for whether there are 2 responses for keyword "name"
            if (table.get(3).size() != 2) {
                error = true;
            }

        }
        {
            // 5
            ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
            int blankLines = 0;
            for (int i = 0; i < table.size(); ++i) {
                for (int j = 0; j < table.get(i).size(); ++j) {

                    if (table.get(i).get(j).trim().equals("")) {
                        error = true;
                        ++blankLines;
                    }
                }
            }

            if (blankLines > 0) {
                System.out.println("testLoadResponseTable 3: failed");
                System.out.println(
                    " Expected the table to not to have any blank lines but found: " + blankLines);

            }

            // 6
            int i = table.size() - 1;
            int j = table.get(i).size() - 1;

            if (!table.get(i).get(j).equals("Do you feel strongly about discussing such things?")) {
                error = true;
                System.out.println("testLoadResponseTable 4: failed");
                System.out.println(
                    " Expected the table's last line to be"
                    + " \"Do you feel strongly about discussing such things?\" but found "
                        + table.get(i).get(j));

            }
        }

        if (error) {
            System.err.println("testLoadResponseTable failed");
        } else {
            System.out.println("testLoadResponseTable passed");
        }
    }

    /*
     * Supporting method for testInputAndResponse. Returns 1 if the test passed and 0 if the test
     * failed.
     */
    private static int checkResponse(String input, String expectedResponse, Random rand,
        ArrayList<ArrayList<String>> table) {

        String[] words = Eliza.prepareInput(input);
        if (words == null) {
            if (expectedResponse == null) {
                return 1;
            } else {
                System.out.println("testInputLines  checkResponse error");
                System.out.println("  input='" + input + "'");
                System.out.println("  response=null");
                System.out.println("  expected='" + expectedResponse + "'");
                return 0;
            }
        }

        String response = Eliza.prepareResponse(words, rand, table);
        if (!response.equals(expectedResponse)) {
            System.out.println("testPrepareResponse  checkResponse error");
            System.out.println("  input='" + input + "'");
            System.out.println("  response='" + response + "'");
            System.out.println("  expected='" + expectedResponse + "'");
            return 0;
        } else {
            return 1;
        }
    }

    /*
     * Runs tests on a bunch of example sentences using a Random number generator with a seed.
     */
    private static void testInputAndResponse() {
        int numPassed = 0;
        // since we are using a seed in the random number generator, the responses should be fixed
        // for the provided response table.
        Random randGen = new Random(923);
        ArrayList<ArrayList<String>> responseTable =
            Eliza.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

        numPassed += checkResponse("I like computers.",
            "What do you think machines have to do with your problem?", randGen, responseTable);

        numPassed += checkResponse("What is your name?",
            "I don't care about names -- please continue.", randGen, responseTable);
        numPassed += checkResponse("You remind me of a grumpy uncle.",
            "What other connections do you see?", randGen, responseTable);
        numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?",
            randGen, responseTable);
        numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?",
            randGen, responseTable);
        numPassed += checkResponse("I wish to believe you.",
            "Why do you think you wish to believe me?", randGen, responseTable);
        numPassed += checkResponse("I dreamed of going into space.",
            "Have you ever dreamed of going into space before?", randGen, responseTable);
        numPassed += checkResponse("I apologize for boring you.",
            "Why do you think you apologize for boring me?", randGen, responseTable);
        numPassed += checkResponse("Because it seems the polite thing to do.",
            "Does that reason seem to explain anything else?", randGen, responseTable);
        numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
        numPassed += checkResponse("I remember being in 1st grade.",
            "What is the connection between me and being in 1st grade?", randGen, responseTable);
        numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
            "Why do you recollect being on a boat just now?", randGen, responseTable);
        numPassed += checkResponse("My family liked to play cards.",
            "Tell me more about your family.", randGen, responseTable);
        numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen,
            responseTable);
        numPassed += checkResponse("Are you a Packers fan?",
            "Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
        numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?",
            randGen, responseTable);
        numPassed += checkResponse("I cannot explain this.",
            "Do you really want to be able to explain this?", randGen, responseTable);
        numPassed += checkResponse("You seem to have a different perspective than many.",
            "You're not really talking about me -- are you?", randGen, responseTable);
        numPassed += checkResponse("I'm talking to my dog.",
            "How long have you been talking to your dog?", randGen, responseTable);
        numPassed += checkResponse("goodbye", null, randGen, responseTable);

        numPassed +=
            checkResponse("", "I'm not sure I understand you fully.", randGen, responseTable);

        int expected = 21;
        if (numPassed == expected) {
            System.out.println("testInputAndResponse passed ");
        } else {
            System.err.println("testInputAndResponse failed " + (expected - numPassed));
        }
    }

}
