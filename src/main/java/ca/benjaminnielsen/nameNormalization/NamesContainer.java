package ca.benjaminnielsen.nameNormalization;

import java.util.ArrayList;
import java.util.Optional;

public class NamesContainer {
    ArrayList<ExerciseName> exerciseNameArrayList = new ArrayList<>();

    public String getDbName(String inputName) {
        Optional<ExerciseName> optionalExerciseName = exerciseNameArrayList.stream()
                .parallel()
                .filter(exerciseName -> exerciseName.isAlternative(inputName))
                .findAny();

        if (optionalExerciseName.isEmpty()) {
            exerciseNameArrayList.add(new ExerciseName(inputName));
            return inputName;
        }
        return optionalExerciseName.get().dbName;
    }

    public void setExerciseNameArrayList(ArrayList<ExerciseName> exerciseNameArrayList) {
        this.exerciseNameArrayList = exerciseNameArrayList;
    }
}
