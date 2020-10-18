package ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoName;

import ca.benjaminnielsen.nameNormalization.ExerciseName;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "ExerciseNames")
public class DynamoExerciseName {
    @DynamoDBHashKey(attributeName = "name")
    String name;

    @DynamoDBAttribute(attributeName = "alternatives")
    Set<String> nameAlternatives;

    @DynamoDBAttribute(attributeName = "url")
    String urlName;

    public DynamoExerciseName() {
    }

    public DynamoExerciseName(String name, Set<String> nameAlternatives, String urlName) {
        this.name = name;
        this.nameAlternatives = nameAlternatives;
        this.urlName = urlName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getNameAlternatives() {
        return nameAlternatives;
    }

    public void setNameAlternatives(Set<String> nameAlternatives) {
        this.nameAlternatives = nameAlternatives;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public ExerciseName toExerciseName() {
        return new ExerciseName(name, nameAlternatives, urlName);
    }
}
