package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.DynamoAccessObjects.dynamoExercise.DynamoExercise;
import ca.benjaminnielsen.domain.input.formats.CsvExercise;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class CsvProcessor extends InputProcessor {
    private CsvToBean<CsvExercise> csvToBean;
    private LocalDateTime startingDate;

    public CsvProcessor(InputStreamReader streamReader){
        csvToBean = new CsvToBeanBuilder<CsvExercise>(streamReader)
                .withSeparator(';')
                .withType(CsvExercise.class)
                .withOrderedResults(false)
                .build();
    }

    public Stream<CsvExercise> getCsvExerciseStream() {
        if(startingDate!=null) {
            csvToBean.setVerifiers(Collections.singletonList(new IsNewExerciseVerifier(startingDate)));
        }
        return csvToBean.stream().parallel();
    }

    @Override
    public Stream<DynamoExercise> getDynamoStream() {
        return getCsvExerciseStream().map(CsvExercise::toDynamoDbObject);
    }

    public void setLastLoadDate(LocalDateTime lastLoadDate) {
        this.startingDate = lastLoadDate;
    }
}
