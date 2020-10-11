package ca.benjaminnielsen.domain.progressiveapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activity {

    @SerializedName("@type")
    @Expose
    private String type;
    @SerializedName("custom")
    @Expose
    private Boolean custom;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("performance")
    @Expose
    private Performance performance;
    @SerializedName("performanceTarget")
    @Expose
    private PerformanceTarget performanceTarget;
    @SerializedName("equipment")
    @Expose
    private Integer equipment;
    @SerializedName("mainTargetMuscle")
    @Expose
    private Integer mainTargetMuscle;
    @SerializedName("secondaryTargetMuscles")
    @Expose
    private List<Integer> secondaryTargetMuscles = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCustom() {
        return custom;
    }

    public void setCustom(Boolean custom) {
        this.custom = custom;
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

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }

    public PerformanceTarget getPerformanceTarget() {
        return performanceTarget;
    }

    public void setPerformanceTarget(PerformanceTarget performanceTarget) {
        this.performanceTarget = performanceTarget;
    }

    public Integer getEquipment() {
        return equipment;
    }

    public void setEquipment(Integer equipment) {
        this.equipment = equipment;
    }

    public Integer getMainTargetMuscle() {
        return mainTargetMuscle;
    }

    public void setMainTargetMuscle(Integer mainTargetMuscle) {
        this.mainTargetMuscle = mainTargetMuscle;
    }

    public List<Integer> getSecondaryTargetMuscles() {
        return secondaryTargetMuscles;
    }

    public void setSecondaryTargetMuscles(List<Integer> secondaryTargetMuscles) {
        this.secondaryTargetMuscles = secondaryTargetMuscles;
    }

    public boolean isMuscleExercise(){
        return type.equals("MuscleActivity");
    }

}