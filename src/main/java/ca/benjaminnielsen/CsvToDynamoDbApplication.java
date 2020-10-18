package ca.benjaminnielsen;

import ca.benjaminnielsen.inputProcessor.CsvProcessor;
import ca.benjaminnielsen.inputProcessor.InputProcessor;
import ca.benjaminnielsen.inputProcessor.JsonProcessor;
import ca.benjaminnielsen.process.DynamoDbHandler;
import ca.benjaminnielsen.process.S3BucketHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CsvToDynamoDbApplication implements RequestHandler<S3Event, String> {

    @Override
    public String handleRequest(S3Event s3event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Application started\n");

        S3BucketHandler s3BucketHandler = new S3BucketHandler(s3event);
        logger.log("Handler Initialized\n");

        DynamoDbHandler dbHandler = DynamoDbHandler.getInstance();
        logger.log("DynamoHandler Initialized\n");

        InputStreamReader streamReader;
        try {
            streamReader = new InputStreamReader(s3BucketHandler.getCsvInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return "error reading from s3";
        }
        logger.log("Reader Created\n");


        ArrayList<InputProcessor> processors = new ArrayList<>();
        processors.add(new CsvProcessor(streamReader));
        logger.log("Csv to Bean created\n");

        if (includesJsonProcessor())
            processors.add(new JsonProcessor(getJsonInputStream()));

        processors.stream().parallel().forEach(
                inputProcessor -> dbHandler.saveAllExercise(inputProcessor.getDynamoStream().collect(Collectors.toList()))
        );

        dbHandler.setLastExerciseLoad();
        return "Lambda Function completed successfully";
    }

    private boolean includesJsonProcessor(){
        return true;
    }

    private InputStreamReader getJsonInputStream(){
        File initialFile = new File("src/main/resources/fws.json");
        try{
            InputStream targetStream = new FileInputStream(initialFile);
            return new InputStreamReader(targetStream);
        }catch (Exception e){

        }
        return null;
    }
}
