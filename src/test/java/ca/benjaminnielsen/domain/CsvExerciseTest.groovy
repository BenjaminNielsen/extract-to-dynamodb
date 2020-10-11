package ca.benjaminnielsen.domain

import ca.benjaminnielsen.domain.dynamoClass.DynamoCardioExercise
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

class CsvExerciseTest extends Specification {

    @Shared
    CsvExercise sharedCardioExercise = getCsvCardioExercise()
    @Shared
    DynamoCardioExercise dynamoCardioExercise = getCardioDynamoExercise()

    //CsvExercise muscleExercise

    def "CsvExercises are properly converted"() {
        given:
        def poop = 1
        when:
        def generatedExercise = sharedCardioExercise.toDynamoDbObject()
        then:
        generatedExercise == dynamoCardioExercise
    }

    CsvExercise getCsvCardioExercise() {
        CsvExercise exercise = new CsvExercise()
        exercise.date = "2020-10-03 19:21:08"
        exercise.exerciseName = "Running"
        exercise.distance = 5
        exercise.distanceUnit = "km"
        exercise.seconds = 1200

        exercise
    }

    DynamoCardioExercise getCardioDynamoExercise() {
        new DynamoCardioExercise("Running",
                                        "",
                                                LocalDateTime.parse("2020-10-03 19:21:08".replace( " " , "T" )),
                                        5,
                                    "km",
                                        1200)
    }
}
