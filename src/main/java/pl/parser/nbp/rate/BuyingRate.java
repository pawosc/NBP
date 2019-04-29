package pl.parser.nbp.rate;

import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.IDLoader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class BuyingRate extends ExchangeRate {


    public BuyingRate(IDLoader idLoader, XmlParser xmlParser) {
        super(idLoader, xmlParser);
    }

    public BigDecimal getAvgBuyingRate(LocalDate from, LocalDate to, String currency) {
        List<String> buyingRates = getBuyingRates(from, to, currency);

        BigDecimal sum = buyingRates.stream()
                .map(rate -> rate.replace(",", "."))
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal size = new BigDecimal(Integer.toString(buyingRates.size()));
        BigDecimal avg = sum.divide(size, 4, RoundingMode.HALF_UP);

        return avg;
    }
}
