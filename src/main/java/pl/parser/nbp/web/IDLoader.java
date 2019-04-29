package pl.parser.nbp.web;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.IOUtils;
import pl.parser.nbp.utils.DateUtils;
import pl.parser.nbp.utils.UrlUtils;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class IDLoader {

    final String NBP_FILE_NAMES_URL = "https://www.nbp.pl/kursy/xml/dir";
    final String FILE_TYPE = ".txt";
    final UrlUtils urlUtils = new UrlUtils();
    final DateUtils dateUtils = new DateUtils();

    public List<String> getAllIdsByDate(LocalDate from, LocalDate to) {
        Set<Integer> yearsBetween = dateUtils.getYearsBetween(from, to);
        List<LocalDate> dates = dateUtils.getDaysBetween(from, to);

        List<String> ids = yearsBetween.stream()
                .map(year -> loadFileWithIdsByYear(year))
                .map(this::separateIds)
                .flatMap(Collection::stream)
                .distinct()
                .filter(id -> isIdForDateExist(id, dates))
                .collect(Collectors.toList());

        if (ids.size() == 0) {
            throw new NoSuchElementException("There aren't any rates between " + from + " to " + to);
        }

        return ids;
    }

    private boolean isIdForDateExist(String id, List<LocalDate> dates) {
        return dates.stream()
                .anyMatch(date -> id.contains(dateUtils.transformDate(date)));
    }

    private List<String> separateIds(String ids) {

        return Arrays.stream(ids.split("\\n"))
                .filter(name -> name.startsWith("c"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private String loadFileWithIdsByYear(int year) {
        String ids = null;
        String fileId = Integer.toString(year);

        if (year == LocalDate.now().getYear()) {
            fileId = "";
        }
        try {
            URL url = urlUtils.urlBuilder(NBP_FILE_NAMES_URL, fileId, FILE_TYPE);
            ids = IOUtils.toString(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ids;
    }
}
