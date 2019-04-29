package pl.parser.nbp.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsTest {

    DateUtils utils = new DateUtils();

    @DisplayName("Should transform date fromat from yyyy-MM-dd to yyMMdd")
    @Test
    void transformDate() {
        //given
        LocalDate date = LocalDate.of(2019, 4, 3);

        //when
        String afterFromat = utils.transformDate(date);

        //then
        assertEquals(afterFromat, "190403");
    }

    @DisplayName("Should return list of LocalDate from specific range")
    @Test
    void getDaysBetween() { //given
        LocalDate start = LocalDate.of(2019, 4, 2);
        LocalDate end = LocalDate.of(2019, 4, 12);

        //when
        List<LocalDate> listOfDate = utils.getDaysBetween(start, end);

        //then
        assertEquals(11, listOfDate.size());
    }

    @DisplayName("Should return list of years from between two dates")
    @Test
    void getYearsBetween() {
        //given
        LocalDate sampleStartDate1 = LocalDate.of(2018, 4, 2);
        LocalDate sampleEndDate1 = LocalDate.of(2019, 3, 12);
        LocalDate sampleStartDate2 = LocalDate.of(2019, 3, 2);
        LocalDate sampleEndDate2 = LocalDate.of(2019, 3, 12);

        //when
        Set<Integer> years1 = utils.getYearsBetween(sampleStartDate1, sampleEndDate1);
        Set<Integer> years2 = utils.getYearsBetween(sampleStartDate2, sampleEndDate2);

        //then
        assertEquals(2, years1.size());
        assertEquals(1, years2.size());
    }
}