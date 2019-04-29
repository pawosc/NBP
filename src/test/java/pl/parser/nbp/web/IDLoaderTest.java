package pl.parser.nbp.web;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IDLoaderTest {

    IDLoader loader = new IDLoader();

    @DisplayName("Should return a list with 21 ids")
    @Test
    void test1() {
        //given
        LocalDate start = LocalDate.of(2019, 3, 1);
        LocalDate end = LocalDate.of(2019, 3, 31);

        //when
        List<String> allIdsByDate = loader.getAllIdsByDate(start, end);

        //then
        assertEquals(21, allIdsByDate.size());
    }

    @DisplayName("Should throw an exception when there aren't any ids between dates")
    @Test
    void test2() {
        //when
        LocalDate start = LocalDate.of(2019, 4, 20);
        LocalDate end = LocalDate.of(2019, 4, 22);

        //then
        assertThrows(NoSuchElementException.class, () -> {
            loader.getAllIdsByDate(start, end);
        });
    }
}