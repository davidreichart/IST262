package model.data.metadata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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

    /**
     * Content type metadata is specific to each sub-metadata class.
     * This method returns a string of text metadata attributes.
     * @return A string representing the TextMetadata attributes for this file.
     */
    @Override
    public String getContentTypeMetadata() {
        return "\u001b[34m\"TextMetadata\": {\n" +
                "  \"wordCount\": " + this.wordCount + ",\n" +
                "  \"characterCount\": " + this.characterCount + "\n" +
                "}\u001B[0m";
    }

    /**
     * Counts the total number of characters in this text file.
     * @return The total number of characters in this text file.
     */
    public int countCharactersInFile() {
        try {
            int characterCount = 0;
            Scanner scanner = new Scanner(this.getFile());
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                characterCount += line.length();
            }
            scanner.close();
            return characterCount;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Counts the total number of words in this text file.
     * Words are defined as any sequence of characters separated by whitespace.
     * @return The total number of words in this text file.
     */
    public int countWordsInFile() {
        int wordCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.getFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                wordCount += words.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordCount;
    }

    /**
     * Constructor used by the TextMetadata builder.
     * Attempts to calculate relevant metadata fields if they are not provided.
     * Default zero/zero equivalent values are placed where data is not provided and not calculable.
     * @param builder The builder instance used to create a new TextMetadata object.
     */
    private TextMetadata(Builder builder) {
        super(builder.absoluteFilePath);
        if (builder.wordCount == 0) {
            this.wordCount = countWordsInFile();
        } else {
            this.wordCount = builder.wordCount;
        }

        if (builder.characterCount == 0) {
            this.characterCount = countCharactersInFile();
        } else {
            this.characterCount = builder.characterCount;
        }
    }

    /**
     * Initializes the build process for creating a new TextMetadata object.
     * Allows for the creation of TextMetadata objects using order agnostic method-chaining.
     * Only a file path is required to create a new TextMetadata object.
     * Default zero/zero equivalent values are placed where data is not provided.
     * @return An instance of the TextMetadata builder.
     */
    public static Builder builder(String absoluteFilePath) {
        return new Builder(absoluteFilePath);
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
