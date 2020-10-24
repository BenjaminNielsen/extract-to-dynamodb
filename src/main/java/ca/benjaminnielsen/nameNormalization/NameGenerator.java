package ca.benjaminnielsen.nameNormalization;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoName.DynamoExerciseName;
import ca.benjaminnielsen.domain.ExerciseName;
import ca.benjaminnielsen.process.DynamoDbHandler;

import java.util.List;
import java.util.stream.Collectors;

public class NameGenerator {

    // static variable single_instance of type Singleton
    private static NameGenerator generator_instance = null;

    private final NamesContainer namesContainer;
    private final DynamoDbHandler dynamoDbHandler;

    private NameGenerator() {
        namesContainer = new NamesContainer();
        dynamoDbHandler = DynamoDbHandler.getInstance();
        loadCurrentNames();
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

    private void loadCurrentNames() {
        List<ExerciseName> exerciseNameList = dynamoDbHandler.getAllExerciseNames()
                .parallelStream()
                .map(DynamoExerciseName::toExerciseName)
                .collect(Collectors.toList());
        namesContainer.setExerciseNameList(exerciseNameList);
    }

    public void uploadCurrentNames() {
        dynamoDbHandler.setExerciseNames(namesContainer.getExerciseNameList());
    }

}
