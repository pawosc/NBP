package pl.parser.nbp.rate;

import pl.parser.nbp.model.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BuyingRate extends ExchangeRate {

    public BigDecimal getAvgBuyingRate(LocalDate from, LocalDate to, String currencyCode) {

        return countAvgRate(getBuyingRates(from, to, currencyCode));
    }

    private List<String> getBuyingRates(LocalDate from, LocalDate to, String currencyCode) {

        return getCurrencies(from, to, currencyCode).stream()
                .map(Currency::getBuyingRate)
                .collect(Collectors.toList());
    }
}
