package pl.parser.nbp.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "tabela_kursow")
public class CurrencyTable {

    @XmlElement(name = "data_publikacji")
    String publicationDate;


    @XmlElement(name = "pozycja")
    List<Currency> currencies;

}
