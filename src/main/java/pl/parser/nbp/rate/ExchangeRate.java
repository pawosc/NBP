package pl.parser.nbp.rate;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.parser.nbp.model.CurrencyDocument;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.utils.DateUtils;
import pl.parser.nbp.web.IDLoader;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExchangeRate {

    DateUtils dateUtils = new DateUtils();
    IDLoader idLoader;
    XmlParser parser;

    public ExchangeRate(IDLoader idLoader, XmlParser parser) {
        this.idLoader = idLoader;
        this.parser = parser;
    }

    public List<CurrencyDocument> getCurrDocumentsBetweenDates(LocalDate from, LocalDate to) {
        List<String> ids = idLoader.getAllIdsByDate(from,to);
        List<LocalDate> dates =  dateUtils.getDaysBetween(from,to);

        return ids
                .stream()
                .filter(id -> isIdForDateExist(id,dates))
                .map(id -> parser.getCurrencyTableFromFileById(id))
                .collect(Collectors.toList());

    }

    private boolean isIdForDateExist(String id, List<LocalDate> dates){
        return dates
                .stream()
                .anyMatch(date -> id.contains(dateUtils.changeFormat(date)));
    }
}
