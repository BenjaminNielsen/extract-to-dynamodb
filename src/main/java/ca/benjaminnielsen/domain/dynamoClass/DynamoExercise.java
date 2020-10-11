package ca.benjaminnielsen.domain.dynamoClass;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DynamoExercise {

    protected UUID id;
    protected String name;
    protected String notes;
    protected LocalDateTime exerciseDate;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    DynamoExercise(String name, String notes, LocalDateTime exerciseDate){
        this.name = name;
        this.notes = notes;
        this.exerciseDate = exerciseDate;
    }

    public DynamoExercise() { }

    public abstract UUID getId();

    public abstract String getName();

    public abstract String getNotes();

    public abstract String getExerciseDate();

    public abstract String getCreatedAt();

    public abstract String getUpdatedAt();

}
