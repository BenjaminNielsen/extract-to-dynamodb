package ca.benjaminnielsen.domain.progressiveapp

import com.google.gson.Gson
import spock.lang.Specification

class ProgressiveAppExerciseTest extends Specification {

    def "JSON can be converted into Objects"() {
        given:
        File initialFile = new File("src/test/resources/fws.json")
        InputStream targetStream = new FileInputStream(initialFile)
        InputStreamReader streamReader = new InputStreamReader(targetStream)
        Gson gson =  new Gson()
        LIbrary lIbrary = new LIbrary()
        when:
        ProgressiveAppWorkout[] exercises = gson.fromJson(streamReader, ProgressiveAppWorkout[].class)
        then:
        exercises.size() == 490
    }
}
