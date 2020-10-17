package ca.benjaminnielsen.nameNormalization;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoName.DynamoExerciseName;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class ExerciseName {
    String dbName; //name that will be used as the dynamodb index
    Set<String> nameAlternatives; //names which represent the same exercise but which might be named differently depending on input
    String urlName; //how this exercise would appear in a url

    public ExerciseName(String dbName) {
        this.dbName = dbName;
        nameAlternatives = new HashSet<>(2); //As of now, there are only likely to be 2 alternatives max
        nameAlternatives.add(dbName);
        urlName = toPrettyURL(dbName);
    }

    public ExerciseName(String dbName, Set<String> nameAlternatives, String urlName) {
        this.dbName = dbName;
        this.nameAlternatives = nameAlternatives;
        this.urlName = urlName;
    }

    public static String toPrettyURL(String string) {
        return Normalizer.normalize(string.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^\\p{Alnum}]+", "-");
    }

    public Set<String> getNameAlternatives() {
        return nameAlternatives;
    }

    public boolean isAlternative(String name) {
        //keeping ugly if/else because I expect more complex logic later
        return name.contains(dbName) || dbName.contains(name);
    }

    public DynamoExerciseName toDynamoExerciseName() {
        return new DynamoExerciseName(dbName, nameAlternatives, urlName);
    }

}
