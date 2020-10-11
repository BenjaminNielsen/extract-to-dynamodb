
package ca.benjaminnielsen.domain.progressiveapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompletedSet {

    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("completedAt")
    @Expose
    private Long completedAt;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("reps")
    @Expose
    private Integer reps;
    @SerializedName("weight")
    @Expose
    private Double weight;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Long completedAt) {
        this.completedAt = completedAt;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
