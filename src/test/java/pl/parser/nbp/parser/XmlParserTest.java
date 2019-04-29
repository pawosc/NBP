package pl.parser.nbp.parser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.parser.nbp.model.CurrencyDocument;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlParserTest {

    XmlParser parser = new XmlParser();

    @DisplayName("Should return currency document with the specific publication date.")
    @Test
    void getCurrencyDocumentFromFileById() {
        //given
        String id = "c073z070413";

        //when
        CurrencyDocument currencyDocument = parser.getCurrencyDocumentFromFileById(id);
        String publicationDate = currencyDocument.getPublicationDate();

        //then
        assertEquals("2007-04-13", publicationDate);
    }
}