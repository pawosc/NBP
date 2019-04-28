package pl.parser.nbp.rate;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.parser.nbp.model.CurrencyCode;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.DocumentLoader;
import pl.parser.nbp.web.IDLoader;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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

    protected List<String> getSellingRates(LocalDate from, LocalDate to, String currencyCode){
        if (!isCurrencyAvailable(currencyCode)) {
            throw new UnsupportedOperationException(currencyCode + " currency is not supported");
        }

        return loader.getCurrDocumentsBetweenDates(from,to).stream()
                .flatMap(cur -> cur.getCurrencies().stream())
                .distinct()
                .filter(code -> code.getCurrencyCode().equalsIgnoreCase(currencyCode))
                .map(rate -> rate.getSellingRate())
                .collect(Collectors.toList());
    }

    protected List<String> getBuyingRates(LocalDate from, LocalDate to, String currencyCode){
        if (!isCurrencyAvailable(currencyCode)) {
            throw new UnsupportedOperationException(currencyCode + " currency is not supported");
        }

        return loader.getCurrDocumentsBetweenDates(from,to).stream()
                .flatMap(cur -> cur.getCurrencies().stream())
                .distinct()
                .filter(code -> code.getCurrencyCode().equalsIgnoreCase(currencyCode))
                .map(rate -> rate.getBuyingRate())
                .collect(Collectors.toList());
    }

    private boolean isCurrencyAvailable(String currency) {
        return Arrays.asList(CurrencyCode.values())
                .stream()
                .anyMatch(cur -> cur.toString().equalsIgnoreCase(currency));
    }
}
