
package ca.benjaminnielsen.domain.progressiveapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PerformanceTarget {

    @SerializedName("groupIndex")
    @Expose
    private Integer groupIndex;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("parameters")
    @Expose
    private List<Parameter> parameters = null;
    @SerializedName("restPerSet")
    @Expose
    private Integer restPerSet;

    public Integer getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(Integer groupIndex) {
        this.groupIndex = groupIndex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public Integer getRestPerSet() {
        return restPerSet;
    }

    public void setRestPerSet(Integer restPerSet) {
        this.restPerSet = restPerSet;
    }

}
