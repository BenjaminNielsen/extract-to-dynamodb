package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;

import java.util.stream.Stream;

abstract public class InputProcessor {
    public abstract Stream<DynamoExercise> getDynamoStream();
}
