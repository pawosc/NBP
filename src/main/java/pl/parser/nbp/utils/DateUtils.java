package pl.parser.nbp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DateUtils {

    public String changeFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        return date.format(formatter);
    }

    public List<LocalDate> getDaysBetween(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException(from + " can't be befor " + to);
        }

        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(from, to.plusDays(1)))
                .filter(day -> !(day.getDayOfWeek().toString().equals("SATURDAY")))
                .filter(day -> !(day.getDayOfWeek().toString().equals("SUNDAY")))
                .collect(Collectors.toList());
    }

    public Set<Integer> getYearsBetween(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException(from + " can't be befor " + to);
        }

        return Stream.iterate(from, date -> date.plusDays(1))
                .limit(ChronoUnit.DAYS.between(from, to.plusDays(1)))
                .map(date -> date.getYear())
                .collect(Collectors.toSet());
    }
}
