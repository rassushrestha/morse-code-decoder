/**
 * MorseNode.java
 * Represents a single node in the Morse code binary tree.
 * Each node can contain a letter and has left/right children.
 */
public class MorseNode {
    // Instance variables
    char letter;        // The letter stored at this node
    MorseNode left;     // Left child - represents dot (.)
    MorseNode right;    // Right child - represents dash (-)
    
    /**
     * Constructor for nodes with a letter
     * @param letter The character to store in this node
     */
    public MorseNode(char letter) {
        this.letter = letter;
        this.left = null;
        this.right = null;
    }
    
    /**
     * Constructor for empty nodes (like the root)
     */
    public MorseNode() {
        this.letter = '\0';  // Null character indicates no letter
        this.left = null;
        this.right = null;
    }
}

