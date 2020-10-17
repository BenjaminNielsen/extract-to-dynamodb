
package ca.benjaminnielsen.domain.progressiveapp;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoCardioExercise;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;
import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoMuscleExercise;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;

public class ProgressiveAppWorkout {

    @SerializedName("activities")
    @Expose
    private List<Activity> activities = null;
    @SerializedName("endTime")
    @Expose
    private Long endTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("programDayIndex")
    @Expose
    private Integer programDayIndex;
    @SerializedName("programId")
    @Expose
    private String programId;
    @SerializedName("startTime")
    @Expose
    private Long startTime;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public LocalDateTime getEndTime() {
       return LocalDateTime.ofInstant(Instant.ofEpochMilli(endTime), TimeZone.getDefault().toZoneId());
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProgramDayIndex() {
        return programDayIndex;
    }

    public void setProgramDayIndex(Integer programDayIndex) {
        this.programDayIndex = programDayIndex;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Stream<DynamoExercise> toDynamoDbStream() {
        Stream<DynamoExercise> exerciseStream = this.activities
                .parallelStream()
                .flatMap(this::activityToDynamo);
        return exerciseStream;
    }

    private Stream<DynamoExercise> activityToDynamo(Activity a){
        return a.getPerformance().getCompletedSets().parallelStream().map(completedSet -> completedSetToDynamo(a, completedSet));
    }

    DynamoExercise completedSetToDynamo(Activity activity, CompletedSet completedSet){
        return activity.isMuscleExercise()? setToMuscle(activity, completedSet) : setToCardio(activity, completedSet);
    }

    private DynamoMuscleExercise setToMuscle(Activity activity, CompletedSet completedSet){
        return new DynamoMuscleExercise(
                activity.getName(),
                completedSet.getComment(),
                this.getEndTime(),
                completedSet.getWeight(),
                 420,
                "kg",
                completedSet.getReps()
                );
    }
    private DynamoCardioExercise setToCardio(Activity activity, CompletedSet completedSet){
        //TODO: fix cardio stuff
        return new DynamoCardioExercise(
                activity.getName(),
                completedSet.getComment(),
                this.getEndTime(),
                completedSet.getDuration(),
                "km",
                completedSet.getDuration()
                );
    }
}
