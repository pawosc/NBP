package pl.parser.nbp.rate;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.parser.nbp.model.Currency;
import pl.parser.nbp.model.CurrencyCode;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.DocumentLoader;
import pl.parser.nbp.web.IDLoader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExchangeRate {


    IDLoader idLoader;
    XmlParser xmlParser;
    DocumentLoader loader ;

    public ExchangeRate(IDLoader idLoader, XmlParser xmlParser) {
        this.idLoader = idLoader;
        this.xmlParser = xmlParser;
        this.loader = new DocumentLoader(idLoader,xmlParser);
    }

    protected BigDecimal countAvgRate(List<String> rates) {
        List<String> buyingRates = rates;

        BigDecimal sum = buyingRates.stream()
                .map(rate -> rate.replace(",", "."))
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal size = new BigDecimal(Integer.toString(buyingRates.size()));
        BigDecimal avg = sum.divide(size, 4, RoundingMode.HALF_UP);

        return avg;
    }

    protected List<Currency> getCurrencies(LocalDate from, LocalDate to, String currencyCode){
        if (!isCurrencyAvailable(currencyCode)) {
            throw new UnsupportedOperationException(currencyCode + " currency is not supported");
        }

        List<Currency> currencies = loader.getCurrDocumentsBetweenDates(from, to).stream()
                .flatMap(currencyDocument -> currencyDocument.getCurrencies().stream())
                .distinct()
                .filter(currency -> currency.getCurrencyCode().equalsIgnoreCase(currencyCode))
                .collect(Collectors.toList());

        if(currencies.size()==0){
            throw new NoSuchElementException("There aren't any rates between "+ from + " to " + to );
        }

        return currencies;

    }

    private boolean isCurrencyAvailable(String currency) {
        return Arrays.stream(CurrencyCode.values())
                .anyMatch(currencyCode -> currencyCode.toString().equalsIgnoreCase(currency));
    }
}
