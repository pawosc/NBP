package pl.parser.nbp.web;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.io.IOUtils;
import pl.parser.nbp.utils.DateUtils;
import pl.parser.nbp.utils.UrlUtils;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class IDLoader {

    final String NBP_FILE_NAMES_URL = "https://www.nbp.pl/kursy/xml/dir";
    final String FILE_TYPE = ".txt";
    final UrlUtils urlUtils = new UrlUtils();
    final DateUtils dateUtils = new DateUtils();


    public List<String> getAllIdsByDate(LocalDate from, LocalDate to) {
        Set<Integer> yearsBetween = dateUtils.getYearsBetween(from, to);

        return yearsBetween.stream()
                .map(year -> loadFileWithIdsByYear(year))
                .map(this::seperateIds)
                .flatMap(id -> id.stream())
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> seperateIds(String ids) {

        return Arrays.stream(ids.split("\\n"))
                .filter(name -> name.startsWith("c"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private String loadFileWithIdsByYear(int year) {
        String content = null;
        String fileId = Integer.toString(year);

        if (year == LocalDate.now().getYear()) {
            fileId = "";
        }
        try {
            URL url = urlUtils.urlBuilder(NBP_FILE_NAMES_URL, fileId, FILE_TYPE);
            content = IOUtils.toString(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
