package pl.parser.nbp;

import pl.parser.nbp.model.CurrencyCode;
import pl.parser.nbp.model.CurrencyDocument;
import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.rate.BuyingRate;
import pl.parser.nbp.web.DocumentLoader;
import pl.parser.nbp.web.IDLoader;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {
    public static void main(String[] args) throws JAXBException, IOException {

        String date1= "2013-01-28";
        String date2= "2013-01-31";

        LocalDate first = LocalDate.parse(date1);
        LocalDate last = LocalDate.parse(date2);


        XmlParser parser = new XmlParser();
        IDLoader idLoader = new IDLoader();

        List<String> allIdsByYears = idLoader.getAllIdsByDate(first, last);
       allIdsByYears.forEach(System.out::println);

        DocumentLoader rate = new DocumentLoader(idLoader, parser);

        BuyingRate big = new BuyingRate(idLoader,parser);

        BigDecimal usd = big.getAvgBuyingRate(first, last, "usd");

        System.out.println(usd);

    }
}
