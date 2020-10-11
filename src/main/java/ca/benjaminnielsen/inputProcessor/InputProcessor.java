package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.dynamoClass.DynamoExercise;

import java.util.stream.Stream;

abstract public class InputProcessor {
    public abstract Stream<DynamoExercise> getDynamoStream();
}
