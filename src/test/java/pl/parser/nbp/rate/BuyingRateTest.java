package pl.parser.nbp.rate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuyingRateTest {

    BuyingRate rate = new BuyingRate();

    @DisplayName("should return the averange for specific date and currency")
    @Test
    void getAvgBuyingRate() {
        LocalDate sampleStartDate1 = LocalDate.of(2013, 1, 28);
        LocalDate sampleEndDate1 = LocalDate.of(2013, 1, 31);
        String sampleCurrencyCode1 = "eur";

        //when
        BigDecimal sample1 = rate.getAvgBuyingRate(sampleStartDate1, sampleEndDate1, sampleCurrencyCode1);

        //then
        assertEquals("4.1505", sample1.toString());
    }
}