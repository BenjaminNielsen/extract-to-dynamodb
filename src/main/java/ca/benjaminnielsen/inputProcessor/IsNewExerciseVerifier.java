package ca.benjaminnielsen.inputProcessor;

import ca.benjaminnielsen.domain.input.formats.CsvExercise;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;

import java.time.LocalDateTime;

public class IsNewExerciseVerifier implements BeanVerifier<CsvExercise> {
    LocalDateTime startDate;

    IsNewExerciseVerifier(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public boolean verifyBean(CsvExercise bean) throws CsvConstraintViolationException {
        return !bean.localDateTime().isBefore(startDate);
    }
}
