package pl.parser.nbp.model;

import lombok.Data;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@XmlRootElement(name = "pozycja")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {

    @XmlElement(name = "kod_waluty")
    String currencyCode;

    @XmlElement(name = "kurs_kupna")
    String buyingRate;

    @XmlElement(name = "kurs_sprzedazy")
    String sellingRate;

}
