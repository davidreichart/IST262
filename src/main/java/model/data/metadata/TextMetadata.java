package model.data.metadata;

/**
 * The TextMetadata object is a collection of data points/statistics to be associated with a text file
 * on the user's system.
 */
public class TextMetadata extends Metadata {

    private int wordCount;
    private int characterCount;

    /**
     * Constructs a new TextMetadata object when all possible attributes are already known.
     * The TextMetadata class is a representation of contextualizing data points for any valid text file on a user's computer.
     * The TextMetadata.builder() method should be used in cases where some fields are unknown.
     * @param absoluteFilePath The absolute file path leading to this text file on the user's system.
     * @param wordCount The total number of words in this text file.
     * @param characterCount The total number of characters in this text file.
     */
    public TextMetadata(String absoluteFilePath, int wordCount, int characterCount) {
        super(absoluteFilePath); // calling the parent constructor to apply core file metadata

        // applying the word count
        if (wordCount == 0) {
            this.wordCount = 0;
        } else {
            this.wordCount = wordCount;
        }

        // applying the character count
        if (characterCount == 0) {
            this.characterCount = 0;
        } else {
            this.characterCount = characterCount;
        }
    }

    /**
     * Constructs a new TextMetadata object when only the absolute file path is known.
     * The TextMetadata class is a representation of contextualizing data points for any valid text file on a user's computer.
     * The TextMetadata.builder() method should be used in cases where some fields are unknown.
     * This constructor sets all metadata fields to default zero/zero equivalent values.
     * @param absoluteFilePath The absolute file path leading to this text file on the user's system.
     */
    public TextMetadata(String absoluteFilePath) {
        super(absoluteFilePath);
        this.wordCount = 0;
        this.characterCount = 0;
    }

    private TextMetadata(Builder builder) {
        super(builder.absoluteFilePath);
        this.wordCount = builder.wordCount;
        this.characterCount = builder.characterCount;
    }

    /**
     * Builder class for constructing new TextMetadata objects.
     * Allows for the creation of TextMetadata objects using order agnostic method-chaining.
     * Only a file path is required to create a new TextMetadata object.
     */
    public static class Builder {
        private String absoluteFilePath;
        private int wordCount;
        private int characterCount;

        /**
         * Constructs a new Builder object for creating a new TextMetadata object.
         * An absolute file path is required to create a new TextMetadata object's underlying Metadata.
         * @param absoluteFilePath The absolute file path leading to this text file on the user's system.
         */
        public Builder(String absoluteFilePath) {
            this.absoluteFilePath = absoluteFilePath;
        }

        /**
         * Sets the total number of words in this text file.
         * Words are defined as any sequence of characters separated by whitespace.
         * @param wordCount The total number of words in this text file.
         * @return An instance of the TextMetadata builder.
         */
        public Builder wordCount(int wordCount) {
            this.wordCount = wordCount;
            return this;
        }

        /**
         * Sets the total number of characters in this text file.
         * @param characterCount The total number of characters in this text file.
         * @return An instance of the TextMetadata builder.
         */
        public Builder characterCount(int characterCount) {
            this.characterCount = characterCount;
            return this;
        }

        /**
         * Completes the TextMetadata build process.
         * Any skipped attributes will be set to default zero/zero equivalent values.
         * @return A new TextMetadata object with the provided attributes.
         */
        public TextMetadata build() {
            return new TextMetadata(this);
        }
    }

    /**
     * Returns the total number of words in this text file.
     * Words are defined as any sequence of characters separated by whitespace.
     * If another definition is required, the {@link #getCharacterCount()} method should be used to calculate the desired value.
     * @return The total number of words in this text file.
     */
    public int getWordCount() {
        return wordCount;
    }

    /**
     * Returns the total number of characters in this text file.
     * @return The total number of characters in this text file.
     */
    public int getCharacterCount() {
        return characterCount;
    }
}
