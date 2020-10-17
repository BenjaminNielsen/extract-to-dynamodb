package ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoLastLoadDate;

import ca.benjaminnielsen.domain.LastLoadDate;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@DynamoDBTable(tableName = "LastExerciseLoad")
public class DynamoLastLoadDate {
    @DynamoDBHashKey(attributeName = "Date")
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LastLoadDate toLastLoadDate() {
        LastLoadDate lastLoadDate = new LastLoadDate();
        lastLoadDate.setDateTime(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
        return lastLoadDate;
    }
}
