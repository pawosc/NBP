package pl.parser.nbp;

import pl.parser.nbp.model.CurrencyTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;

public class MainClass {
    public static void main(String[] args) throws JAXBException, MalformedURLException {

        File file = new File("C:\\Users\\oscik\\Desktop\\c073z070413.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyTable.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        CurrencyTable currencyTable = (CurrencyTable) jaxbUnmarshaller.unmarshal(file);

        System.out.println(currencyTable);
        currencyTable.getCurrencies().forEach(System.out::println);

    }
}
