package pl.parser.nbp.rate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SellingRateTest {

    SellingRate rate = new SellingRate();

    @DisplayName("should calculate the standard deviation for specific date and currency")
    @Test
    void countStandardDeviation() {
        //given
        LocalDate sampleStartDate1 = LocalDate.of(2013, 1, 28);
        LocalDate sampleEndDate1 = LocalDate.of(2013, 1, 31);
        String sampleCurrencyCode1 = "eur";

        //when
        BigDecimal sample1 = rate.countStandardDeviation(sampleStartDate1, sampleEndDate1, sampleCurrencyCode1);

        //then
        assertEquals("0.0125", sample1.toString());


    }
}