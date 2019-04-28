package pl.parser.nbp.parser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.FileUtils;
import pl.parser.nbp.model.CurrencyTable;
import pl.parser.nbp.Utils.UrlUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class XmlParser {

    final String NBP_CURRENCY_FILE_URL = "http://www.nbp.pl/kursy/xml/";
    final String FILE_TYPE = ".xml";
    final UrlUtils urlUtils = new UrlUtils();

    public CurrencyTable getCurrencyTableFromFileById(String id) throws IOException, JAXBException {

        URL url = urlUtils.urlBuilder(NBP_CURRENCY_FILE_URL, id, FILE_TYPE);
        File file = new File(id);
        FileUtils.copyURLToFile(url, file);

        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyTable.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        CurrencyTable currencyTable = (CurrencyTable) jaxbUnmarshaller.unmarshal(file);

        file.delete();

        return currencyTable;


    }


}
