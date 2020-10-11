package ca.benjaminnielsen.process;

import ca.benjaminnielsen.domain.dynamoClass.DynamoExercise;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDbHandler {
    private final AmazonDynamoDB client;
    private final DynamoDBMapper mapper;

    public DynamoDbHandler(){
        client = AmazonDynamoDBClientBuilder.standard().build();
        mapper = new DynamoDBMapper(client);
    }

    public void saveExercise(DynamoExercise dynamoExercise){
        mapper.save(dynamoExercise);
    }
}
