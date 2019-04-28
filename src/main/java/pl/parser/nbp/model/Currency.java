package pl.parser.nbp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Currency {

    String currency;
    LocalDate publicationDate;
    String buyingRate;
    String sellingRate;

}
