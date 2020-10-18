package ca.benjaminnielsen.process

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoName.DynamoExerciseName
import ca.benjaminnielsen.nameNormalization.NameGenerator
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

class DynamoDbHandlerTest extends Specification {
    @Shared
    DynamoDbHandler dbHandler = DynamoDbHandler.getInstance()


    def "last load date returns a date"() {
        given:
        def result = dbHandler.getLastExerciseLoadDate()

        expect:
        result instanceof LocalDateTime
        result.isBefore(LocalDateTime.now())
        result.isAfter(LocalDateTime.MIN)
    }

    def "name generator can add to dynamodb"() {
        given:
        final String NON_EXERCISE_NAME = 'askdjnasdk'
        DynamoExerciseName newName = new DynamoExerciseName()
        newName.name = NON_EXERCISE_NAME
        NameGenerator nameGenerator

        when:
        nameGenerator = NameGenerator.getInstance()
        List<DynamoExerciseName> originalExerciseNames = dbHandler.getAllExerciseNames()
        String wackName = nameGenerator.toDbName(NON_EXERCISE_NAME)
        nameGenerator.uploadCurrentNames()
        List<DynamoExerciseName> newExerciseNames = dbHandler.getAllExerciseNames()

        then:
        newExerciseNames.size() == ++originalExerciseNames.size()
        wackName == NON_EXERCISE_NAME

        cleanup:
        dbHandler.mapper.delete(newName)
    }
}
