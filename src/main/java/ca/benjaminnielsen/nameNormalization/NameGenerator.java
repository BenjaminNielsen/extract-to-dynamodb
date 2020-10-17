package ca.benjaminnielsen.nameNormalization;

import ca.benjaminnielsen.process.DynamoDbHandler;

public class NameGenerator {

    // static variable single_instance of type Singleton
    private static NameGenerator generator_instance = null;

    public NamesContainer namesContainer;
    private final DynamoDbHandler dynamoDbHandler;

    private NameGenerator() {
        namesContainer = new NamesContainer();
        dynamoDbHandler = new DynamoDbHandler();
    }

    // static method to create instance of Singleton class
    public static NameGenerator getInstance() {
        if (generator_instance == null)
            generator_instance = new NameGenerator();

        return generator_instance;
    }

    public String toDbName(String inputName) {
        return namesContainer.getDbName(inputName);
    }

    public void getCurrentNamesFromDb() {

    }
}
