package ca.benjaminnielsen.nameNormalization

import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Unroll

class ExerciseNameTest extends Specification {

    @Unroll
    @Ignore
    def "Exercise name with #dbName returns #expectedResult when compared with #inputString"() {
        given:
        ExerciseName exerciseName = new ExerciseName(dbName)

        expect:
        exerciseName.isAlternative(inputString) == expectedResult

        where:
        dbName                | inputString                       | expectedResult
        'BARBELL BENCH PRESS' | 'Bench Press (Barbell)'           | true
        'BARBELL BENCH PRESS' | 'Bench Press Barbell'             | true
        'BARBELL BENCH PRESS' | 'Barbell Bench Press'             | true
        'BARBELL BENCH PRESS' | 'Strict Military Press (Barbell)' | false
        'BARBELL BENCH PRESS' | 'Dumbbell Bench Press'            | false
        'BARBELL BENCH PRESS' | 'Bench Press (Dumbbell)'          | false

    }
}
