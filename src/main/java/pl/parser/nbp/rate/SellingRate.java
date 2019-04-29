package pl.parser.nbp.rate;

import pl.parser.nbp.model.Currency;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.IDLoader;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class SellingRate extends ExchangeRate {

    public SellingRate(IDLoader idLoader, XmlParser xmlParser) {
        super(idLoader, xmlParser);
    }

    public BigDecimal countStandardDeviation(LocalDate from, LocalDate to, String currencyCode) {
        BigDecimal variance = countVariance(from, to, currencyCode);

        return variance.sqrt(MathContext.DECIMAL32).setScale(4, RoundingMode.HALF_UP);
    }


    private BigDecimal countVariance(LocalDate from, LocalDate to, String currencyCode) {
        List<String> rates = getSellingRates(from, to, currencyCode);

        BigDecimal size = new BigDecimal(rates.size());
        BigDecimal avg = getAvgSellingRate(from, to, currencyCode);
        BigDecimal sum = rates.stream()
                .map(rate -> rate.replace(",", "."))
                .map(BigDecimal::new)
                .map(rate -> rate.subtract(avg))
                .map(rate -> rate.pow(2))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return sum.divide(size, RoundingMode.HALF_UP);
    }


    private BigDecimal getAvgSellingRate(LocalDate from, LocalDate to, String currencyCode) {

        return countAvgRate(getSellingRates(from, to, currencyCode));
    }

    private List<String> getSellingRates(LocalDate from, LocalDate to, String currencyCode) {

        return getCurrencies(from, to, currencyCode).stream()
                .map(Currency::getSellingRate)
                .collect(Collectors.toList());
    }
}
