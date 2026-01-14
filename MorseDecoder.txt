/**
 * MorseDecoder.java
 * Main program to decode Morse code messages using a binary tree.
 * 
 * This program constructs a binary tree based on Morse code representation
 * for each letter of the alphabet. The tree is built manually with:
 * - Root node containing no letter
 * - Left path for dots (.)
 * - Right path for dashes (-)
 * 
 * Input format:
 * - Letters separated by single space
 * - Words separated by double space
 * - Message ends with #
 * 
 * Example: ".... . .-.. . -.  --. ..-#" decodes to "HELEN GU"
 * CSCI-260 Project 2
 */

import java.util.Scanner;

public class MorseDecoder {
    
    /**
     * Main method - entry point of the program
     * Creates a binary tree and uses it to decode Morse code input
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create the Morse code binary tree
        // Tree is constructed manually as per project requirements
        System.out.println("Building Morse code tree...");
        System.out.println("- Root node created (empty)");
        System.out.println("- Building tree: left for '.', right for '-'");
        MorseTree tree = new MorseTree();
        System.out.println("- All 26 letters inserted");
        System.out.println("Tree built successfully!\n");
        
        // Create scanner for reading user input
        Scanner scanner = new Scanner(System.in);
        
        // Display instructions to the user
        System.out.println("===================================");
        System.out.println("   MORSE CODE DECODER");
        System.out.println("===================================");
        System.out.println("Instructions:");
        System.out.println("  - Use dots (.) and dashes (-)");
        System.out.println("  - Separate letters with ONE space");
        System.out.println("  - Separate words with TWO spaces");
        System.out.println("  - End your message with #");
        System.out.println("\nExample:");
        System.out.println("  Input:  .... . .-.. .-.. ---  --. ..-#");
        System.out.println("  Output: HELLO GU");
        System.out.println("===================================\n");
        
        // Get Morse code input from user
        System.out.print("Enter Morse code: ");
        String morseInput = scanner.nextLine();
        
        // Validate that input is not empty
        if (morseInput == null || morseInput.trim().isEmpty()) {
            System.out.println("\n[ERROR] No input provided!");
            scanner.close();
            return;
        }
        
        // Warn if # is missing
        if (!morseInput.contains("#")) {
            System.out.println("\n[WARNING] Input should end with # symbol");
            System.out.println("Adding # automatically...\n");
            morseInput = morseInput + "#";
        }
        
        // Decode the message using the tree
        System.out.println("\nDecoding...");
        String decodedMessage = tree.decode(morseInput);
        
        // Display the result
        System.out.println("\n===================================");
        System.out.println("Decoded message: " + decodedMessage);
        System.out.println("===================================\n");
        
        // Close the scanner
        scanner.close();
    }
}
