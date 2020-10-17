package ca.benjaminnielsen.process

import spock.lang.Specification

import java.time.LocalDateTime

class DynamoDbHandlerTest extends Specification {
    def "last load date returns a date"() {
        given:
        DynamoDbHandler handler = new DynamoDbHandler()

        when:
        def result = handler.getLastExerciseLoadDate()

        then:
        result instanceof LocalDateTime
        result.isBefore(LocalDateTime.now())
        result.isAfter(LocalDateTime.MIN)
    }
}
