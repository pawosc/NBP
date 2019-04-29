package pl.parser.nbp.web;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import pl.parser.nbp.model.CurrencyDocument;
import pl.parser.nbp.parser.XmlParser;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DocumentLoader {

    IDLoader idLoader = new IDLoader();
    XmlParser parser = new XmlParser();

    public List<CurrencyDocument> getCurrDocumentsBetweenDates(LocalDate from, LocalDate to) {
        List<String> ids = idLoader.getAllIdsByDate(from, to);

        return ids.stream()
                .map(id -> parser.getCurrencyDocumentFromFileById(id))
                .collect(Collectors.toList());
    }


}
