package ca.benjaminnielsen.process;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoLastLoadDate.DynamoLastLoadDate;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoName.DynamoExerciseName;
import ca.benjaminnielsen.domain.ExerciseName;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.google.common.collect.Iterables;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DynamoDbHandler {
    private static DynamoDbHandler handlerInstance;
    private final DynamoDBMapper mapper;
    static final int DYNAMODB_BATCH_LIMIT = 25;

    private DynamoDbHandler() {
        mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());
    }

    // static method to create instance of Singleton class
    public static DynamoDbHandler getInstance() {
        if (handlerInstance == null)
            handlerInstance = new DynamoDbHandler();

        return handlerInstance;
    }

    public void saveExercise(DynamoExercise dynamoExercise) {
        mapper.save(dynamoExercise);
    }

    public void saveAllExercise(List<DynamoExercise> dynamoExercises) {
        Iterables.partition(dynamoExercises, DYNAMODB_BATCH_LIMIT).forEach(mapper::batchSave);
    }

    public void setLastExerciseLoad() {
        mapper.save(new Date());
    }

    public LocalDateTime getLastExerciseLoadDate() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<DynamoLastLoadDate> lastLoadDateList = mapper.scan(DynamoLastLoadDate.class, scanExpression);
        DynamoLastLoadDate lastLoadDate = lastLoadDateList.get(0); //TODO consider using stream-reduce to get latest date
        return lastLoadDate == null ? null : lastLoadDate.toLastLoadDate().getDateTime();
    }

    public List<DynamoExerciseName> getAllExerciseNames() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return mapper.scan(DynamoExerciseName.class, scanExpression);
    }

    public void setExerciseNames(List<ExerciseName> exerciseNames) {
        List<DynamoExerciseName> dynamoExerciseNames = exerciseNames.parallelStream()
                .map(ExerciseName::toDynamoExerciseName)
                .collect(Collectors.toList());
        Iterables.partition(dynamoExerciseNames, DYNAMODB_BATCH_LIMIT).forEach(mapper::batchSave);

    }

}
