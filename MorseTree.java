/**
 * MorseTree.java
 * Binary tree structure for decoding Morse code.
 * The tree is built such that going left represents a dot (.)
 * and going right represents a dash (-).
 * 
 * This implementation handles edge cases including:
 * - Invalid Morse code sequences
 * - Extra spaces
 * - Missing # symbol
 * - Empty input
 */
public class MorseTree {
    private MorseNode root;  // Root of the tree (empty node)
    
    /**
     * Constructor - creates the tree and builds it with all letters
     */
    public MorseTree() {
        root = new MorseNode();  // Create empty root node
        buildTree();             // Build the complete tree
    }
    
    /**
     * Builds the entire Morse code tree manually.
     * Inserts each letter of the alphabet with its Morse code.
     * The tree structure follows the pattern:
     * - Left child = dot (.)
     * - Right child = dash (-)
     * 
     * Tree construction is done manually by calling insert() for each letter.
     * This satisfies the requirement: "This part of the project should be done manually"
     */
    private void buildTree() {
        // Level 1: E and T (1 symbol each)
        insert("E", ".");      // E is just one dot - left from root
        insert("T", "-");      // T is just one dash - right from root
        
        // Level 2: Four letters (2 symbols each)
        insert("I", "..");     // I = dot-dot (left-left from root)
        insert("A", ".-");     // A = dot-dash (left-right from root)
        insert("N", "-.");     // N = dash-dot (right-left from root)
        insert("M", "--");     // M = dash-dash (right-right from root)
        
        // Level 3: Eight letters (3 symbols each)
        insert("S", "...");    // S = dot-dot-dot
        insert("U", "..-");    // U = dot-dot-dash
        insert("R", ".-.");    // R = dot-dash-dot
        insert("W", ".--");    // W = dot-dash-dash
        insert("D", "-..");    // D = dash-dot-dot
        insert("K", "-.-");    // K = dash-dot-dash
        insert("G", "--.");    // G = dash-dash-dot
        insert("O", "---");    // O = dash-dash-dash
        
        // Level 4: Remaining letters (4 symbols each)
        insert("H", "....");   // H = dot-dot-dot-dot
        insert("V", "...-");   // V = dot-dot-dot-dash
        insert("F", "..-.");   // F = dot-dot-dash-dot
        insert("L", ".-..");   // L = dot-dash-dot-dot
        insert("P", ".--.");   // P = dot-dash-dash-dot
        insert("J", ".---");   // J = dot-dash-dash-dash
        insert("B", "-...");   // B = dash-dot-dot-dot
        insert("X", "-..-");   // X = dash-dot-dot-dash
        insert("C", "-.-.");   // C = dash-dot-dash-dot
        insert("Y", "-.--");   // Y = dash-dot-dash-dash
        insert("Z", "--..");   // Z = dash-dash-dot-dot
        insert("Q", "--.-");   // Q = dash-dash-dot-dash
    }
    
    /**
     * Inserts a letter into the tree at the position specified by its Morse code.
     * This method manually constructs the tree by traversing left for dots
     * and right for dashes, creating nodes as needed.
     * 
     * @param letter The letter to insert (as a String)
     * @param code The Morse code for this letter (dots and dashes)
     */
    private void insert(String letter, String code) {
        MorseNode current = root;  // Start at the root
        
        // Traverse the tree according to the Morse code
        // Go left for "." and right for "-"
        for (int i = 0; i < code.length(); i++) {
            char symbol = code.charAt(i);
            
            if (symbol == '.') {
                // Dot means go left
                if (current.left == null) {
                    current.left = new MorseNode();  // Create node if doesn't exist
                }
                current = current.left;
            } 
            else if (symbol == '-') {
                // Dash means go right
                if (current.right == null) {
                    current.right = new MorseNode();  // Create node if doesn't exist
                }
                current = current.right;
            }
        }
        
        // At the end of the path, store the letter
        current.letter = letter.charAt(0);
    }
    
    /**
     * Decodes a single Morse code letter by traversing the tree.
     * Moves left for "." and right for "-" as per project requirements.
     * 
     * @param code The Morse code string for one letter (e.g., ".-" for A)
     * @return The decoded letter, or '?' if invalid code
     */
    private char decodeLetter(String code) {
        // Handle empty code
        if (code == null || code.isEmpty()) {
            return '?';
        }
        
        MorseNode current = root;  // Start at root
        
        // Follow the path specified by the code
        // Go left for "." and right for "-"
        for (int i = 0; i < code.length(); i++) {
            char symbol = code.charAt(i);
            
            if (symbol == '.') {
                current = current.left;   // Go left for dot
            } 
            else if (symbol == '-') {
                current = current.right;  // Go right for dash
            }
            else {
                // Invalid character in Morse code
                return '?';
            }
            
            // If we reach a null node, the code is invalid
            if (current == null) {
                return '?';
            }
        }
        
        // Return the letter at this node
        // If no letter was stored, return '?'
        if (current.letter == '\0') {
            return '?';
        }
        return current.letter;
    }
    
    /**
     * Decodes an entire Morse code message into English.
     * 
     * Format as per project requirements:
     * - Letters separated by single space
     * - Words separated by double space  
     * - Message ends with # symbol
     * 
     * Example: ".... . .-.. . -.  --. ..-#" decodes to "HELEN GU"
     * 
     * @param morseCode The full Morse code string to decode
     * @return The decoded English message
     */
    public String decode(String morseCode) {
        // Handle null or empty input
        if (morseCode == null || morseCode.trim().isEmpty()) {
            return "[ERROR: Empty input]";
        }
        
        StringBuilder result = new StringBuilder();
        
        // Remove the # symbol at the end if present
        if (morseCode.endsWith("#")) {
            morseCode = morseCode.substring(0, morseCode.length() - 1);
        }
        
        // Handle case where input was only "#"
        if (morseCode.trim().isEmpty()) {
            return "[ERROR: No Morse code provided]";
        }
        
        // Split by double space to separate words
        // Using split with limit -1 to preserve trailing empty strings
        String[] words = morseCode.split("  ", -1);
        
        // Process each word
        for (int w = 0; w < words.length; w++) {
            String word = words[w];
            
            // Skip empty words caused by extra spaces
            if (word.trim().isEmpty()) {
                continue;
            }
            
            // Split by single space to get individual letters
            String[] letters = word.split(" ");
            
            // Decode each letter
            for (String letterCode : letters) {
                // Skip empty strings from multiple spaces
                if (!letterCode.isEmpty()) {
                    char decodedChar = decodeLetter(letterCode);
                    result.append(decodedChar);
                }
            }
            
            // Add space between words (but not after the last word)
            if (w < words.length - 1 && result.length() > 0) {
                // Check if next word is not empty before adding space
                boolean hasNextWord = false;
                for (int i = w + 1; i < words.length; i++) {
                    if (!words[i].trim().isEmpty()) {
                        hasNextWord = true;
                        break;
                    }
                }
                if (hasNextWord) {
                    result.append(" ");
                }
            }
        }
        
        // Return result or error message if nothing was decoded
        if (result.length() == 0) {
            return "[ERROR: Could not decode any letters]";
        }
        
        return result.toString();
    }
}
