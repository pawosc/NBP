package pl.parser.nbp;

import pl.parser.nbp.model.CurrencyDocument;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.DocumentLoader;
import pl.parser.nbp.web.IDLoader;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) throws JAXBException, IOException {

        String date1= "2019-04-15";
        String date2= "2019-04-23";

        LocalDate first = LocalDate.parse(date1);
        LocalDate last = LocalDate.parse(date2);


        XmlParser parser = new XmlParser();
        IDLoader idLoader = new IDLoader();

        List<String> allIdsByYears = idLoader.getAllIdsByDate(first, last);
       // allIdsByYears.forEach(System.out::println);

        /*DocumentLoader rate = new DocumentLoader(idLoader, parser);

        List<CurrencyDocument> currDocumentsBetweenDates = rate.getCurrDocumentsBetweenDates(first, last);
        List<String> usd = currDocumentsBetweenDates.stream()
                .flatMap(cur -> cur.getCurrencies().stream())
                .distinct()
                .filter(code -> code.getCurrencyCode().contains("USD"))
                .map(y -> y.getSellingRate())
                .collect(Collectors.toList());

        usd.forEach(System.out::println);*/

    }
}
