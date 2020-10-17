package ca.benjaminnielsen.domain;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoCardioExercise;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoMuscleExercise;
import com.opencsv.bean.CsvBindByName;

import java.time.LocalDateTime;

public class CsvExercise {
    @CsvBindByName(column = "Date", required = true)
    public String date;

    @CsvBindByName(column = "Workout Name")
    public String workoutName;

    @CsvBindByName(column = "Exercise Name", required = true)
    public String exerciseName;

    @CsvBindByName(column = "Set Order")
    public int setOrder;

    @CsvBindByName(column = "Weight")
    public double weight;

    @CsvBindByName(column = "Weight Unit")
    public String weightUnit;

    @CsvBindByName(column = "Reps")
    public int reps;

    @CsvBindByName(column = "RPE")
    public String rpe;

    @CsvBindByName(column = "distance")
    public double distance;

    @CsvBindByName(column = "Distance Unit")
    public String distanceUnit;

    @CsvBindByName(column = "Seconds", required = true)
    public int seconds;

    @CsvBindByName(column = "Notes")
    public String notes;

    @CsvBindByName(column = "Workout Notes")
    public String workoutNotes;

    public boolean isMuscleExercise(){
        return reps>0;
    }

    public DynamoExercise toDynamoDbObject(){
        return isMuscleExercise() ? toDynamoMuscle() : toDynamoCardio();
    }

    private DynamoMuscleExercise toDynamoMuscle(){
        LocalDateTime localDateTime = LocalDateTime.parse(date.replace( " " , "T" ));

        return new DynamoMuscleExercise(exerciseName, notes, localDateTime, weight, setOrder, weightUnit, reps);
    }

    private DynamoCardioExercise toDynamoCardio(){
        LocalDateTime localDateTime = LocalDateTime.parse(date.replace( " " , "T" ));

        return new DynamoCardioExercise(exerciseName, notes, localDateTime, distance, distanceUnit, seconds);
    }
}
