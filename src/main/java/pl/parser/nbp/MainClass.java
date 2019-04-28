package pl.parser.nbp;

import pl.parser.nbp.parser.XmlParser;
import pl.parser.nbp.web.IDLoader;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MainClass {
    public static void main(String[] args) throws JAXBException, IOException {

        String date1= "2018-04-15";
        String date2= "2019-04-22";

        LocalDate first = LocalDate.parse(date1);
        LocalDate last = LocalDate.parse(date2);


        XmlParser parser = new XmlParser();
        IDLoader idLoader = new IDLoader();

        List<String> allIdsByYears = idLoader.getAllIdsByDate(first, last);
        allIdsByYears.forEach(System.out::println);


     /*   CurrencyTable table = parser.getCurrencyTableFromFileById("c073z070413");

        System.out.println(table);*/

    }
}
