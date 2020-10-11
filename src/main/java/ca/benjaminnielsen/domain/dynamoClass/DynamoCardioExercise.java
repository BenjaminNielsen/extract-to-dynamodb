package ca.benjaminnielsen.domain.dynamoClass;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName="CardioExerciseTest")
public class DynamoCardioExercise extends DynamoExercise {

    private double distance;
    private String distanceUnit;
    private int seconds;

    public DynamoCardioExercise() {
        super();
    }

    public DynamoCardioExercise(String name, String notes, LocalDateTime exerciseDate, double distance, String distanceUnit, int seconds) {
        super(name, notes, exerciseDate);
        this.distance = distance;
        this.distanceUnit = distanceUnit;
        this.seconds = seconds;
    }

    @DynamoDBHashKey(attributeName="id")
    @DynamoDBAutoGeneratedKey
    @Override
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @DynamoDBRangeKey(attributeName="name")
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName="notes")
    @Override
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @DynamoDBAttribute(attributeName="exerciseDate")
    @Override
    public String getExerciseDate() {
        return exerciseDate.toString();
    }

    public void setExerciseDate(LocalDateTime exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    @DynamoDBRangeKey(attributeName="distance")
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @DynamoDBRangeKey(attributeName="distanceUnit")
    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    @DynamoDBRangeKey(attributeName="seconds")
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String getCreatedAt() {
        return LocalDateTime.now().toString();
    }

    @Override
    public String getUpdatedAt() {
        return LocalDateTime.now().toString();
    }

}
