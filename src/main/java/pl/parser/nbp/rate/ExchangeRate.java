package pl.parser.nbp.rate;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.DocumentLoader;
import pl.parser.nbp.web.IDLoader;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class ExchangeRate {

    DocumentLoader loader = new DocumentLoader(new IDLoader(), new XmlParser());

    /*public BigDecimal calculateAvgSellRate(LocalDate from, LocalDate to){
        List<CurrencyDocument> currDocuments = loader.getCurrDocumentsBetweenDates(from, to);


    }*/
}
