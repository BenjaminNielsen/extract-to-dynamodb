
package ca.benjaminnielsen.domain.progressiveapp;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Performance {

    @SerializedName("completedSets")
    @Expose
    private List<CompletedSet> completedSets = null;

    public List<CompletedSet> getCompletedSets() {
        return completedSets;
    }

    public void setCompletedSets(List<CompletedSet> completedSets) {
        this.completedSets = completedSets;
    }

}
