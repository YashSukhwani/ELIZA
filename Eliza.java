/**
 * @author yashsukhwani
 *
 */

import java.io.File;
import java.util.Arrays;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *  The Eliza class holds the user input and response formation for a system
 *  that collects user input and responds appropriately. Eliza is based off of
 *  a computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 *  uses keyword matching to respond to users in a way that displays interest
 *  in the users and continues the conversation until instructed otherwise.
 */

public class Eliza {

   /*
    * This method does input and output with the user. It calls supporting methods
    * to read and write files and process each user input.
    *  
    * @param args (unused)
    */
    
    public static void main(String[] args) {

        // Milestone 2
        // create a scanner for reading user input and a random number
        // generator with Config.SEED as the seed

        Scanner sc = new Scanner(System.in); // initializes Scanner objects
        Random rand = new Random(Config.SEED); // initializes Random error

        ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();
        ArrayList<String> savedConversation = new ArrayList<String>(); // contains the conversation

        // Milestone 3
        // How the program starts depends on the command-line arguments.
        // Command-line arguments can be names of therapists for example:
        // Eliza Joe Laura
        // If no command-line arguments then the therapists name is Eliza
        // and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
        // Example filename: Eliza.rsp
        // If only one command-line argument, then read the responses from
        // the corresponding file with Config.RESPONSE_FILE_EXTENSION.
        // If there is more than one command-line argument then offer them
        // as a list of people to talk with. For the three therapists above the prompt is
        // "Would you like to speak with Eliza, Joe, or Laura?"
        // When a user types a name then read the responses from the file which
        // is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
        // Whatever name the user types has the extension appended and
        // is read using loadResponseTable. If loadResponseTable can't load
        // the file then it will report an error.

        String therapistName = ""; // stores the name of the therapist

        if (args == null || args.length < 1) {
            therapistName = "Eliza"; // default therapist name
        }

        if (args.length == 1) { // if 1 argument entered
            therapistName = args[0]; // the bot takes that argument as its name
        }

        int i = 0;
        int j = 0;

        if (args.length > 1) { // then will have to choose which of the different bots to speak to

            String name = "";
            boolean validName = true; // whether the name entered by the user is valid

            while ((i < args.length) || validName == false) { // loops until valid name entered

                if (validName == false) {
                    System.out.println("Error! Please enter a valid name.");
                }
                if (i == 0) {
                    System.out.print("Would you like to speak to ");
                }
                if (i < args.length - 2) { // for creating the primary prompt correctly
                    System.out.print(args[i] + ", ");
                }
                if (i == args.length - 2) { // for creating the primary prompt correctly
                    System.out.print(args[i] + " or ");
                }
                if (i == args.length - 1) { // for creating the primary prompt correctly
                    System.out.println(args[i] + "?");
                }

                i++;

                if (!(i < args.length)) {
                    name = sc.nextLine(); // inputs the name entered by the user
                    validName = false;

                    for (j = 0; j < args.length; j++) {

                        if (name.equals(args[j])) {
                            validName = true; // so that it does not enter the while loop again
                            break;
                        }
                    }
                }
            }

            therapistName = name; // stores the valid name entered as the bot's name
        }

        // loads the repsonse table
        responseTable = loadResponseTable(therapistName + Config.RESPONSE_FILE_EXTENSION);

        // If no command-line arguments then the therapists name is Eliza
        // and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
        // Example filename: Eliza.rsp
        // If only one command-line argument, then read the responses from
        // the corresponding file with Config.RESPONSE_FILE_EXTENSION.
        // If there is more than one command-line argument then offer them
        // as a list of people to talk with. For the three therapists above the prompt is
        // "Would you like to speak with Eliza, Joe, or Laura?"
        // When a user types a name then read the responses from the file which
        // is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
        // Whatever name the user types has the extension appended and
        // is read using loadResponseTable. If loadResponseTable can't load
        // the file then it will report an error.


        // Milestone 2
        // name prompt
        // begin conversation loop
        // welcome prompt

        System.out.println("Hi I'm " + therapistName + ", what is your name?");

        String userName = sc.nextLine(); // stores the name of the user

        savedConversation.add("Hi I'm " + therapistName + ", what is your name?");

        System.out.println("Nice to meet you " + userName.trim() + ". What is on your mind?");

        savedConversation.add("Nice to meet you " + userName.trim() + ". What is on your mind?");

        while (true) { // Enters an infinite conversation loop that exits when quit char is entered

            String userInput = sc.nextLine();

            savedConversation.add(userInput);

            String[] input = prepareInput(userInput);

            if (input == null) { // If input is null, then a quit char is returned.
                System.out.println("Goodbye " + userName.trim() + ".");
                savedConversation.add("Goodbye " + userName.trim() + ".");
                break;
            } else {
                String preparedResponse = prepareResponse(input, rand, responseTable);
                System.out.println(preparedResponse);
                savedConversation.add(preparedResponse);
            }
        }

        // Milestone 2
        // obtain user input
        // prepareInput
        // end loop if quit word
        // ending prompt

        // Milestone 3
        // if no quit words then prepareResponse

        System.out.println("Would you like to save a record of this converstion?");

        String save = sc.next();
        while (true) { // Exited if a char is other than 'y' or 'Y' or if the file is saved

            if ((save.charAt(0) == 'y') || (save.charAt(0) == 'Y')) {
                System.out.print("Which file would you like to save this Conversation to?");
                String savedFile = sc.next();

                try { // in case an IOException is thrown
                    saveDialog(savedConversation, savedFile);

                    System.out.println("Thanks again for talking! Our conversation is saved in: "
                        + savedFile + ".");
                    break; // exiting the while loop

                } catch (IOException e) {
                    System.out.println("Unable to save conversation to: " + savedFile);
                    continue;
                }
            } else {
                break;
            }
        }

        // Milestone 3

        // Save all conversation (user and system responses) starting
        // with this program saying "Hi I'm..." and concludes with
        // "Goodbye <name>.".
        // Always prompt the user to see if they would like to save a
        // record of the conversation. If the user enters a y or Y as the
        // first non-whitespace character then prompt for filename and save,
        // otherwise don't save dialog. After successfully saving a dialog
        // print the message "Thanks again for talking! Our conversation is saved in: <filename>".
        // If saveDialog throws an IOException then catch it, print out the error:
        // "Unable to save conversation to: " <name of file>
        // Repeat the code prompting the user if they want to save the dialog.

        sc.close();
    }

    
       /**
     * This method processes the user input, returning an ArrayList containing Strings,
     * where each String is a phrase from the user's input. This is done by removing leading
     * and trailing whitespace, making the user's input all lower case, then going through 
     * each character of the user's input. When going through each character this
     * keeps all digits, alphabetic characters and ' (single quote). The characters ? ! , . 
     * signal the end of a phrase, and possibly the beginning of the next phrase,
     * but are not included in the result.
     * All other characters such as ( ) - " ] etc. should be replaced with a space. 
     * This method makes sure that every phrase has some visible characters but no
     * leading or trailing whitespace and only a single space between words of a phrase.
     * If userInput is null then return null, if no characters then return a
     * 0 length list, otherwise return a list of phrases.  Empty phrases and phrases
     * with just invalid/whitespace characters should NOT be added to the list.
     * 
     * Example userInput: "Hi,  I  am! a big-fun robot!!!"
     * Example returned: "hi", "i am", "a big fun robot"
     * 
     * @param userInput text the user typed
     * @return the phrases from the user's input
     */
    public static ArrayList<String> separatePhrases(String userInput) {

        if (userInput == null) {
            return null;
        }

        userInput = userInput.trim(); // removes leading and trailing whitespace
        userInput = userInput.toLowerCase();

        int i = 0; // will be used to control loop iterations
        String validInput = ""; // will be used to store the valid characters from userInput

        //System.out.println(userInput);
        
        // I will refers to these characters ? ! , . as delimiters for this method
        // The remaining valid characters will be called tokens
        
        for (i = 0; i < userInput.length(); i++) { 
            if (Character.isDigit(userInput.charAt(i)) || Character.isLetter(userInput.charAt(i))
                || userInput.charAt(i) == ' ' || userInput.charAt(i) == '\''
                || userInput.charAt(i) == '?' || userInput.charAt(i) == '!'
                || userInput.charAt(i) == ',' || userInput.charAt(i) == '.') {
                validInput = validInput + userInput.charAt(i); // adds valid characters to string
            } else {
                validInput = validInput + " "; // adds a space instead of the invalid character 
            }
        }

        String multipleSpaces = ""; // used in the correction of multiple spaces to a single space

        for (i = 0; i < validInput.length(); i++) {
            multipleSpaces = multipleSpaces + " "; // builds a string with increasing  spaces
            validInput = validInput.replaceAll(multipleSpaces, " "); // many spaces --> one space
        }

        validInput = validInput.replaceAll("  ", " "); // corrects double space from the loop

        ArrayList<String> phrases = new ArrayList<String>();

        String part = ""; // used to concatenate the string until hitting a separation word

        for (i = 0; i < validInput.length(); i++) { // iterating through all the valid characters

            part = ""; // resets the concatenated string to an empty one

            while (Character.isDigit(validInput.charAt(i))
                || Character.isLetter(validInput.charAt(i)) || validInput.charAt(i) == ' '
                || validInput.charAt(i) == '\'') { // checks for tokens
                part = part + validInput.charAt(i); // builds a string until a delimiter is hit
                i++;

                if (i == validInput.length()) {
                    break; // prevents the string index from going out of bounds
                }
            }

            part = part.trim();

            if (!(part.equals(""))) {
                phrases.add(part); // adds the concatenated string to the end of the ArrayList
            }
        }

        return phrases;
    }

    /**
     * Checks whether any of the phrases in the parameter match
     * a quit word from Config.QUIT_WORDS.  Note: complete phrases
     * are matched, not individual words within a phrase.
     * 
     * @param phrases List of user phrases
     * @return true if any phrase matches a quit word, otherwise false
     */
    public static boolean foundQuitWord(ArrayList<String> phrases) {

        if (phrases == null) {
            return false;
        }
        
        int i = 0; 
        int k = 0; 

        for (i = 0; i < phrases.size(); i++) { // iterating through phrases array
            for (k = 0; k < Config.QUIT_WORDS.length; k++) { // iterating through QUIT_WORDS
                if (phrases.get(i).equals(Config.QUIT_WORDS[k])) {
                    return true; // true returned if phrases contains a quit word
                }
            }
        }

        return false;
    }

    /**
     * Iterates through the phrases of the user's input, finding
     * the longest phrase to which to respond. If two phrases are the same 
     * length, returns whichever has the lower index in the list. 
     * If phrases parameter is null or size 0 then return "" (Update 11/15/18).
     * 
     * @param phrases List of user phrases
     * @return the selected phrase
     */
    public static String selectPhrase(ArrayList<String> phrases) {

        if (phrases == null || phrases.size() == 0) {
            return "";
        }

        int i = 0; 
        int maxLength = phrases.get(0).length(); // stores the length of the longest phrase
        int[] length = new int[phrases.size()]; // stores the length of each phrase
        int maxPosition = 0; // will represent the position of the longest phrase

        for (i = 0; i < phrases.size(); i++) { 
            length[i] = phrases.get(i).length(); // length of each phrase is stored into size []
        }

        for (i = 0; i < length.length; i++) { 
            if (length[i] > maxLength) {
                maxLength = length[i];
                maxPosition = i; // maxPosition updated to hold position of longest phrase
            }
        }

        return phrases.get(maxPosition); // uses indicator as an index to return the longest phrase
    }

    /** 
     * Looks for a replacement word for the word parameter and if found,
     * returns the replacement word. Otherwise if the word parameter is not
     * found then the word parameter itself is returned. 
     * The wordMap parameter contains rows of match and replacement strings.
     * On a row, the element at the 0 index is the word to match and if it 
     * matches return the string at index 1 in the same row.  Some example
     * word maps that will be passed in are Config.INPUT_WORD_MAP and 
     * Config.PRONOUN_MAP. 
     * 
     * If word is null return null. If wordMap is null or wordMap length is 
     * 0 simply return word parameter. For this implementation it is reasonable to 
     * assume that if wordMap length is >= 1 then the number of elements in 
     * each row is at least 2.
     * 
     * @param word The word to look for in the map
     * @param wordMap  The map of words to look in
     * @return the replacement string if the word parameter is found in the 
     * wordMap otherwise the word parameter itself.
     */
    public static String replaceWord(String word, String[][] wordMap) {

        if (word == null) {
            return null;
        }

        if (wordMap == null || wordMap.length == 0) {
            return word;
        }

        int i = 0; // i will be used as a counter for a loop


        for (i = 0; i < wordMap.length; i++) { // iterates through wordMap
            if (wordMap[i][0].equals(word)) { // checks whether word equals the first word of a row
                return wordMap[i][1]; // returns the second word of the row where word matches
            }
        }

        return word;
    }

    /**
     * Concatenates the elements in words parameter into a string with
     * a single space between each array element. Does not change any 
     * of the strings in the words array. There are no leading or trailing 
     * spaces in the returned string.
     * 
     * @param words a list of words
     * @return a string containing all the words with a space between each.
     */
    public static String assemblePhrase(String[] words) {

        if (words == null || words.length < 1) {
            return "";
        }
        
        String completePhrase = ""; // the phrase that is assembled
        int i = 0;

        for (i = 0; i < words.length; i++) { 
            completePhrase = completePhrase + words[i] + " "; // concatenates all the words
        }

        completePhrase = completePhrase.trim(); // removes leading and trailing whitespaces
        return completePhrase;
    }

    /**
     * Replaces words in phrase parameter if matching words are found
     * in the mapWord parameter. A word at a time from phrase parameter
     * is looked for in wordMap which may result in more than one word.
     * For example: i'm => i am
     * Uses the replaceWord and assemblePhrase methods.
     * Example wordMaps are Config.PRONOUN_MAP and Config.INPUT_WORD_MAP.
     * If wordMap is null then phrase parameter is returned.
     * Note: there will Not be a case where a mapping will itself
     * be a key to another entry. In other words, only one pass
     * through swapWords will ever be necessary.
     * 
     * @param phrase The given phrase which contains words to swap
     * @param wordMap Pairs of corresponding match & replacement words
     * @return The reassembled phrase
     */
    public static String swapWords(String phrase, String[][] wordMap) {

        if (wordMap == null || wordMap.length < 1) {
            return phrase;
        }
        
        if (phrase == null || phrase.length() < 1) {
            return "";
        }

        String[] phrasesArray = phrase.split(" ");

        int i = 0; 

        for (i = 0; i < phrasesArray.length; i++) { 
            phrasesArray[i] = replaceWord(phrasesArray[i], wordMap); // replaceWord applied 
        }

        String completePhrase = assemblePhrase(phrasesArray); // creates a string from the array
        return completePhrase;
    }

    /**
     * This prepares the user input. First, it separates input into phrases
     * (using separatePhrases). If a phrase is a quit word (foundQuitWord) 
     * then return null.  Otherwise, select a phrase (selectPhrase), swap input 
     * words (swapWords with Config.INPUT_WORD_MAP) and return an array with
     * each word its own element in the array. 
     * 
     * @param input The input from the user
     * @return  words from the selected phrase
     */
    public static String[] prepareInput(String input) {

        ArrayList<String> processed = separatePhrases(input); // processed stores returned ArrayList

        boolean quit = foundQuitWord(processed); // quit stores result of foundQuitWord
        String currentPhrase = ""; // stores the phrase being processed
        String[] outputPhrase; // stores the phrase to output

        if (quit) {
            return null;
        } else {
            currentPhrase = selectPhrase(processed); 
            currentPhrase = swapWords(currentPhrase, Config.INPUT_WORD_MAP); 
            outputPhrase = currentPhrase.split(" "); 
            return outputPhrase; 
        }

    }
    
    /**
     * Reads a file that contains keywords and responses. A line contains either a list of keywords
     * or response, any blank lines are ignored. All leading and trailing whitespace on a line is
     * ignored. A keyword line begins with "keywords" with all the following tokens on the line, the
     * keywords. Each line that follows a keyword line that is not blank is a possible response for
     * the keywords. For example (the numbers are for our description purposes here and are not in
     * the file):
     * 
     * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers? 4 5 keywords i
     * dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while you were awake? 8 9 Have you ever
     * dreamed <3> before?
     *
     * In line 1 is a single keyword "computer" followed by two possible responses on lines 2 and 3.
     * Line 4 and 8 are ignored since they are blank (contain only whitespace). Line 5 begins new
     * keywords that are the words "i" and "dreamed". This keyword list is followed by three
     * possible responses on lines 6, 7 and 9.
     * 
     * The keywords and associated responses are each stored in their own ArrayList. The response
     * table is an ArrayList of the keyword and responses lists. For every keywords list there is an
     * associated response list. They are added in pairs into the list that is returned. There will
     * always be an even number of items in the returned list.
     * 
     * Note that in the event an IOException occurs when trying to read the file then an error
     * message "Error reading <fileName>", where <fileName> is the parameter, is printed and a
     * non-null reference is returned, which may or may not have any elements in it.
     * 
     * @param fileName The name of the file to read
     * @return The response table
     */
    public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
        ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>();
        String line;
        int counter = -1; // counter for iterating through response

        File file = new File(fileName);  // new File object created
        try {
            Scanner sc = new Scanner(file); // new Scanner object created to read through file

            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.contains("keywords")) { // Checking whether keywords are present
                    responseTable.add(new ArrayList<String>()); // adds an empty list for keywords
                    responseTable.add(new ArrayList<String>()); // adds an empty list for responses
                    counter++; // increment counter to input the keywords and responses
                    String[] words = line.split(" ");
                    for (int i = 1; i < words.length; i++) { // i begins at 1 to ignore "keywords"
                        responseTable.get(counter).add(words[i].trim()); // adds keywords to list
                    }
                    counter++;
                } else { // this means it is not a keyword line
                    if (line.length() > 0) { // checking whether line is empty
                        responseTable.get(counter).add(line.trim()); // adding response line to list
                    }
                }
            }
            sc.close();
        } catch (IOException e) { // catching IO exception
            System.out.println("Error reading " + fileName);
        }
        return responseTable;
    }
        
                
                 
    /**
     * Checks to see if the keywords match the sentence. In other words, checks to see that all the
     * words in the keyword list are in the sentence and in the same order. If all the keywords
     * match then this method returns an array with the unmatched words before, between and after
     * the keywords. If the keywords do not match then null is returned.
     * 
     * When the phrase contains elements before, between, and after the keywords, each set of the
     * three is returned in its own element String[] keywords = {"i", "dreamed"}; String[] phrase =
     * {"do", "you", "know", that", "i", "have", "dreamed", "of", "being", "an", "astronaut"};
     * 
     * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being an astronaut"
     * 
     * In an example where there is a single keyword, the resulting List's first element will be the
     * the pre-sequence element and the second element will be everything after the keyword, in the
     * phrase String[] keywords = {"always"}; String[] phrase = {"I", "always", "knew"};
     * 
     * toReturn[0] = "I" toReturn[1] = "knew"
     * 
     * In an example where a keyword is not in the phrase in the correct order, null is returned.
     * String[] keywords = {"computer"}; String[] phrase = {"My","dog", "is", "lost"};
     * 
     * return null
     * 
     * @param keywords The words to match, in order, in the sentence.
     * @param phrase Each word in the sentence.
     * @return The unmatched words before, between and after the keywords or null if the keywords
     *         are not all matched in order in the phrase.
     */
    public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
        // see the algorithm presentation linked in Eliza.pdf.

        if (phrase == null) {
            String [] emptyReturn = {""};
            return emptyReturn;
        }
        
        int i = 0; // used to control loop iterations
        int keywordCounter = 0; // counts the number of keywords found so far

        if (keywords == null || keywords.size() < 1) {
            String[] nullReturn = {""}; // creates a single element 1-d array
            for (i = 0; i < phrase.length; i++) { 
                nullReturn[0] = nullReturn[0] + phrase[i] + " "; // concatenates words from phrase
            }
            nullReturn[0] = nullReturn[0].trim(); // removes leading and trailing whitespaces


            return nullReturn; // returns the single element 1-d array
        }

        int[] keywordIndex = new int[keywords.size()]; // stores keywords' index in phrase

        boolean toAdjust = false; // adjusts keywordCounter if all keywords are found prematurely

        for (i = 0; i < phrase.length; i++) { // iterating through each element in phrase

            if (keywordCounter == keywords.size()) { // checks if all keywords found
                keywordCounter--; // this allow the words after last keyword to be input correctly
                toAdjust = true; // if keywordCounter has to be adjusted back to its correct value
            }

            if (phrase[i].equals(keywords.get(keywordCounter))) {
                keywordIndex[keywordCounter] = i; // stores the index at which the keyword was found
                keywordCounter++;
                toAdjust = false; // since the above statement already re-adjusted keywordCounter
            }
        }

        if (toAdjust) { // checks if keywordCounter needs to be re-adjusted
            keywordCounter++; // re-adjusting keywordCounter 
        }

        ArrayList<String> unmatchedList = new ArrayList<>();


        if (keywordCounter != keywords.size()) {
            return null;
        }

        int j = 0; // iterates through keywordIndex array
        int x = 0; // iterates through phrases array

        String partUnmatched = ""; // stores unmatched string between keywords

        while (x < phrase.length) {

            while (j < keywordIndex.length && x < phrase.length) {

                while (x < keywordIndex[j] && x < phrase.length) {
                    partUnmatched = partUnmatched + " " + phrase[x]; // builds the unmatched string
                    x++;
                }

                j++;
                x++; // to skip over the keyword
                partUnmatched = partUnmatched.trim();

                unmatchedList.add(partUnmatched);
                partUnmatched = ""; // resetting it before next unmatched string is built

            }

            if (x < phrase.length) { // enters this if the last word in phrase is not a keyword
                partUnmatched = partUnmatched + " " + phrase[x];
                x++;
            }

        }

        partUnmatched = partUnmatched.trim();
        unmatchedList.add(partUnmatched); // for adding the words in phrase after the last keyword

        String[] toReturn = new String[unmatchedList.size()];

        for (i = 0; i < unmatchedList.size(); i++) {
            toReturn[i] = unmatchedList.get(i); // copying elements from arraylist to array
        }

        return toReturn;

    }

    /**
     * Selects a randomly generated response within the list of possible responses
     * using the provided random number generator where the number generated corresponds
     * to the index of the selected response. Use Random nextInt( responseList.size())
     * to generate the random number.  If responseList is null or 0 length then
     * return null.
     * 
     * @param rand  A random number generator.
     * @param responseList  A list of responses to choose from.
     * @return A randomly selected response
     */
    public static String selectResponse(Random rand, ArrayList<String> responseList) {

        if (responseList == null || responseList.size() == 0) {
            return null;
        }

        int randNum = rand.nextInt(responseList.size()); // generates a random integer

        return responseList.get(randNum);

    }

    /**
     * This method takes processed user input and forms a response.
     * This looks through the response table in order checking to
     * see if each keyword pattern matches the userWords. The first matching 
     * keyword pattern found determines the list of responses to choose from. 
     * A keyword pattern matches the userWords, if all the keywords are found, 
     * in order, but not necessarily contiguous. This keyword matching is done 
     * by findKeyWordsInPhrase method.  See the findKeyWordsInPhrase algorithm
     * in the Eliza.pdf.  
     * 
     * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
     * Otherwise one of possible responses for the matched keywords is selected
     * with selectResponse method. The response selected is checked for the 
     * replacement symbol <n> where n is 1 to the length of unmatchedWords array
     * returned by findKeyWordsInPhrase.  For each replacement symbol the 
     * corresponding unmatched words element (index 0 for <1>, 1 for <2> etc.)
     * has its pronouns swapped with swapWords using Config.PRONOUN_MAP and then
     * replaces the replacement symbol in the response.
     * 
     * @param userWords using input after preparing.
     * @param rand A random number generator.
     * @param responseTable  A table containing a list of keywords and response pairs.  
     * @return The generated response
     */
    public static String prepareResponse( String [] userWords, Random rand, 
            ArrayList<ArrayList<String>> responseTable) {

        //Iterate through the response table.
        //The response table has paired rows.  The first row is a list of key 
        //words, the next a list of corresponding responses. The 3rd row another 
        //list of keywords and 4th row the corresponding responses.
            
            // checks to see if the current keywords match the user's words 
            // using findKeyWordsInPhrase.

        // if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
        // else, select a response using the appropriate list of responses for the keywords

            // Look for <1>, <2> etc in the chosen response.  The number starts with 1 and
            // there won't be more than the number of elements in unmatchedWords returned by 
            // findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
            // 1 more than the number of keywords.
            // For each <n> found, find the corresponding unmatchedWords phrase (n-1) and swap
            // its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
            // result to replace the <n> in the chosen response. 
                    
                    //in the selected echo, swap pronouns
                    
                    // inserts the new phrase with pronouns swapped, into the response
        
        int i = 0;

        String[] findReturn = null; // stores the output of findKeWordsInPhrase

        String noMatch = ""; // builds the findKeWordsInPhrase return for a null keywords parameter


        for (i = 0; i < userWords.length; i++) {
            noMatch = noMatch + " " + userWords[i];
        }

        noMatch = noMatch.trim();
        
        if (noMatch.equals("")) {
            noMatch = null; 
        }

        i = 0;

        while (i < responseTable.size()) { // loop looks for a correct match case

            findReturn = findKeyWordsInPhrase(responseTable.get(i), userWords);

            if (findReturn != null && findReturn.length > 0 && !(findReturn[0].equals(noMatch))) {
                break; // when a correct match case is found
            }

            i = i + 2; // going to the next set of keywords
        }

        if (findReturn == null || findReturn.length < 1 || findReturn[0].equals(noMatch)) {
            return Config.NO_MATCH_RESPONSE;
        }

        String response = selectResponse(rand, responseTable.get(i + 1)); // stores method output

        i = 0;

        if (response.contains("<")) { // checks if any replacement is required in the response

            String[] responseArray = response.split("<");
            int splitLength = responseArray.length; // stores length of the array created with split

            int[] swapIndex = new int[splitLength - 1]; // stores the index of the word to swap
            String[] swapString = new String[splitLength - 1];

            while (i < splitLength - 1) { // -1 to account for change from absolute value to index

                swapIndex[i] = Integer.parseInt(responseArray[i + 1].substring(0, 1)) - 1;

                swapString[i] = findReturn[swapIndex[i]]; // gets the string to be swapped

                swapString[i] = swapWords(swapString[i], Config.PRONOUN_MAP); // swaps the string

                i++;

            }

            i = 0;

            response = responseArray[0]; // first step to build the output string. Variable reused.

            while (i < splitLength - 1) { // loop to complete the output string

                response = response + swapString[i] + responseArray[i + 1].substring(2);

                i++;
            }
            
            return response;
        } else {
            return response;
        }

    }

    /**
     * Creates a file with the given name, and fills that file
     * line-by-line with the tracked conversation. Every line ends
     * with a newline. Throws an IOException if a writing error occurs.
     * 
     * @param dialog the complete conversation
     * @param fileName The file in which to write the conversation
     * @throws IOException
     */
    public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {

        PrintWriter writer = null; // initializing writing stream

        try {
            writer = new PrintWriter(new File(fileName));
            for (int i = 0; i < dialog.size(); i++) { // iterating through possible dialogs
                String currLine = dialog.get(i);
                writer.write(currLine + "\n"); // writing line
                writer.flush(); // flushing contents or remaining contents of writer stream
            }
            writer.close(); // close writer to avoid file not closed errors
        } catch (IOException e) {
            writer.close();
            throw e;
        }


    }
}
