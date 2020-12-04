package ca.benjaminnielsen;

import ca.benjaminnielsen.awsio.DynamoDbHandler;
import ca.benjaminnielsen.inputProcessor.CsvProcessor;
import ca.benjaminnielsen.inputProcessor.InputProcessor;

import ca.benjaminnielsen.process.S3BucketHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import java.io.*;
import java.time.LocalDateTime;
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

        CsvProcessor csvProcessor = new CsvProcessor(streamReader);

        LocalDateTime lastLoadDate = dbHandler.getLastExerciseLoadDate();
        if(lastLoadDate!=null)
            csvProcessor.setLastLoadDate(lastLoadDate);
        logger.log("Csv to Bean created\n");

        dbHandler.saveAllExercise(csvProcessor.getDynamoStream().collect(Collectors.toList()));

        dbHandler.setLastExerciseLoad();
        return "Lambda Function completed successfully";
    }
}
