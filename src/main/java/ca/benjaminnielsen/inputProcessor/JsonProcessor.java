package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.dynamoClass.DynamoExercise;
import ca.benjaminnielsen.domain.progressiveapp.ProgressiveAppWorkout;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class JsonProcessor extends InputProcessor {

    public Gson gson =  new Gson();
    private InputStreamReader streamReader;

    public JsonProcessor(InputStreamReader streamReader){
        this.streamReader = streamReader;
    }

    public Stream<ProgressiveAppWorkout> getProgressiveWorkoutStream(){
       return Arrays.stream(gson.fromJson(streamReader, ProgressiveAppWorkout[].class));
    }

    @Override
    public Stream<DynamoExercise> getDynamoStream(){
        return getProgressiveWorkoutStream().parallel()
                .flatMap(ProgressiveAppWorkout::toDynamoDbStream);
    }

}
