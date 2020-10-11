
package ca.benjaminnielsen.domain.progressiveapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Parameter {

    @SerializedName("allOut")
    @Expose
    private Boolean allOut;
    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("mark")
    @Expose
    private Integer mark;
    @SerializedName("maxReps")
    @Expose
    private Integer maxReps;
    @SerializedName("minReps")
    @Expose
    private Integer minReps;

    public Boolean getAllOut() {
        return allOut;
    }

    public void setAllOut(Boolean allOut) {
        this.allOut = allOut;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public Integer getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(Integer maxReps) {
        this.maxReps = maxReps;
    }

    public Integer getMinReps() {
        return minReps;
    }

    public void setMinReps(Integer minReps) {
        this.minReps = minReps;
    }

}
