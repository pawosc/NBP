package pl.parser.nbp;

import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.rate.BuyingRate;
import pl.parser.nbp.rate.SellingRate;
import pl.parser.nbp.web.DocumentLoader;
import pl.parser.nbp.web.IDLoader;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MainClass {
    public static void main(String[] args) {

        String date1 = "2019-04-21";
        String date2 = "2019-04-22";

        LocalDate first = LocalDate.parse(date1);
        LocalDate last = LocalDate.parse(date2);


        XmlParser parser = new XmlParser();
        IDLoader idLoader = new IDLoader();

        DocumentLoader rate = new DocumentLoader(idLoader, parser);


        BuyingRate big = new BuyingRate(idLoader, parser);
        SellingRate sell = new SellingRate(idLoader, parser);

        BigDecimal usd = big.getAvgBuyingRate(first, last, "eur");
        BigDecimal eee = sell.countStandardDeviation(first, last, "eur");

        System.out.println(usd);
        System.out.println(eee);

    }
}
