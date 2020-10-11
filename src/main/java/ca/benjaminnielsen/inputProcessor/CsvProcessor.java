package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.CsvExercise;
import ca.benjaminnielsen.domain.dynamoClass.DynamoExercise;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class CsvProcessor extends InputProcessor {
    private CsvToBean<CsvExercise> csvToBean;

    public CsvProcessor(InputStreamReader streamReader){
        csvToBean = new CsvToBeanBuilder<CsvExercise>(streamReader)
                .withSeparator(';')
                .withType(CsvExercise.class)
                .withOrderedResults(false)
                .build();
    }

    public CsvProcessor(InputStreamReader streamReader, LocalDateTime startingDate){

    }

    public Stream<CsvExercise> getCsvExerciseStream() {
        return csvToBean.stream().parallel();
    }

    @Override
    public Stream<DynamoExercise> getDynamoStream() {
        return getCsvExerciseStream().map(CsvExercise::toDynamoDbObject);
    }
}
