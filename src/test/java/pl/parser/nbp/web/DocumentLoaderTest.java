package pl.parser.nbp.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.parser.nbp.model.CurrencyDocument;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DocumentLoaderTest {

    DocumentLoader documentLoader = new DocumentLoader();

    @DisplayName("should return a list with 10 documents ")
    @Test
    void getCurrDocumentsBetweenDates() {
        //given
        LocalDate start = LocalDate.of(2018, 3, 5);
        LocalDate end = LocalDate.of(2018, 3, 16);

        //when
        List<CurrencyDocument> documents = documentLoader.getCurrDocumentsBetweenDates(start, end);

        //then
        assertEquals(10, documents.size());
        assertNotNull(documents);

    }
}