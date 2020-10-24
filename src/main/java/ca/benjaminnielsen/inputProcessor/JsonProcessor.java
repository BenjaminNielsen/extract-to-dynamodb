package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;
import ca.benjaminnielsen.domain.input.formats.progressiveapp.ProgressiveAppWorkout;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class JsonProcessor extends InputProcessor {

    private final Gson GSON = new Gson();
    private final InputStreamReader streamReader;

    public JsonProcessor(InputStreamReader streamReader){
        this.streamReader = streamReader;
    }

    public Stream<ProgressiveAppWorkout> getProgressiveWorkoutStream(){
       return Arrays.stream(GSON.fromJson(streamReader, ProgressiveAppWorkout[].class));
    }

    @Override
    public Stream<DynamoExercise> getDynamoStream(){
        return getProgressiveWorkoutStream().parallel()
                .flatMap(ProgressiveAppWorkout::toDynamoDbStream);
    }

}
