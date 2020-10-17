package ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.time.LocalDateTime;
import java.util.UUID;

@DynamoDBTable(tableName="MuscleExerciseTest")
public class DynamoMuscleExercise extends DynamoExercise {

    double weight;
    int setOrder;
    String weightUnit;
    int reps;

    public DynamoMuscleExercise() {
        super();
    }

    public DynamoMuscleExercise(String name, String notes, LocalDateTime exerciseDate, double weight, int setOrder, String weightUnit, int reps) {
        super(name, notes, exerciseDate);
        this.weight = weight;
        this.setOrder = setOrder;
        this.weightUnit = weightUnit;
        this.reps = reps;
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

    @DynamoDBAttribute(attributeName="weight")
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @DynamoDBAttribute(attributeName="setOrder")
    public int getSetOrder() {
        return setOrder;
    }

    public void setSetOrder(int setOrder) {
        this.setOrder = setOrder;
    }

    @DynamoDBAttribute(attributeName="weightUnit")
    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    @DynamoDBAttribute(attributeName="reps")
    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    @Override
    public String getCreatedAt() {
        return LocalDateTime.now().toString();
    }

    @Override
    public String getUpdatedAt() {
        return LocalDateTime.now().toString();
    }

    @Override
    public String toString() {
        return "DynamoMuscleExercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", notes='" + notes + '\'' +
                ", exerciseDate=" + exerciseDate +
                '}';
    }
}