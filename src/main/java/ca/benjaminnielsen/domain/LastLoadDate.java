package ca.benjaminnielsen.domain;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoLastLoadDate.DynamoLastLoadDate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LastLoadDate {
    LocalDateTime dateTime;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    DynamoLastLoadDate toDynamoLastLoadDate() {
        DynamoLastLoadDate dynamoLastLoadDate = new DynamoLastLoadDate();
        dynamoLastLoadDate.setDate(Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
        return dynamoLastLoadDate;
    }
}
