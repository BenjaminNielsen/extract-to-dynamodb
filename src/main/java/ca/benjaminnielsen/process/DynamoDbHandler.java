package ca.benjaminnielsen.process;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoLastLoadDate.DynamoLastLoadDate;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoName.DynamoExerciseName;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import java.time.LocalDateTime;
import java.util.List;

public class DynamoDbHandler {
    private final DynamoDBMapper mapper;

    public DynamoDbHandler() {
        mapper = new DynamoDBMapper(AmazonDynamoDBClientBuilder.standard().build());
    }

    public void saveExercise(DynamoExercise dynamoExercise) {
        mapper.save(dynamoExercise);
    }

    public void setLastExerciseLoad() {
        //TODO: Complete this
    }

    public LocalDateTime getLastExerciseLoadDate() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<DynamoLastLoadDate> lastLoadDateList = mapper.scan(DynamoLastLoadDate.class, scanExpression);
        DynamoLastLoadDate lastLoadDate = lastLoadDateList.get(0); //TODO consider using stream-reduce to get latest date
        return lastLoadDate == null ? null : lastLoadDate.toLastLoadDate().getDateTime();
    }

    public List<DynamoExerciseName> getAllExerciseNames() {
        DynamoDBQueryExpression<DynamoExerciseName> queryExpression = new DynamoDBQueryExpression<>();
        return mapper.query(DynamoExerciseName.class, queryExpression);
    }

    public void setExerciseName() {

    }

}
