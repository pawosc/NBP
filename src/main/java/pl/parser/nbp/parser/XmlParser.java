package pl.parser.nbp.parser;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.FileUtils;
import pl.parser.nbp.model.CurrencyDocument;
import pl.parser.nbp.utils.UrlUtils;

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

    UrlUtils urlUtils = new UrlUtils();

    public CurrencyDocument getCurrencyDocumentFromFileById(String id){

        URL url;
        File file = null;
        JAXBContext jaxbContext;
        Unmarshaller jaxbUnmarshaller = null;
        CurrencyDocument currencyDocument = null;

        try {
            url = urlUtils.urlBuilder(NBP_CURRENCY_FILE_URL, id, FILE_TYPE);
            file = new File(id);
            FileUtils.copyURLToFile(url, file);
            jaxbContext = JAXBContext.newInstance(CurrencyDocument.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            currencyDocument = (CurrencyDocument) jaxbUnmarshaller.unmarshal(file);

        } catch (IOException e) {
            e.printStackTrace();

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        file.delete();

        return currencyDocument;
    }
}
