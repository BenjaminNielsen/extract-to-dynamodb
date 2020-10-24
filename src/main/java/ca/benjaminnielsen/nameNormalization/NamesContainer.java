package ca.benjaminnielsen.nameNormalization;

import ca.benjaminnielsen.domain.ExerciseName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NamesContainer {
    List<ExerciseName> exerciseNameList = new ArrayList<>();

    public String getDbName(String inputName) {
        Optional<ExerciseName> optionalExerciseName = exerciseNameList.stream()
                .parallel()
                .filter(exerciseName -> exerciseName.isAlternative(inputName))
                .findAny();

        if (optionalExerciseName.isEmpty()) {
            exerciseNameList.add(new ExerciseName(inputName));
            return inputName;
        }
        return optionalExerciseName.get().getDbName();
    }

    public void setExerciseNameList(List<ExerciseName> exerciseNameList) {
        this.exerciseNameList = exerciseNameList;
    }

    public List<ExerciseName> getExerciseNameList() {
        return exerciseNameList;
    }
}
