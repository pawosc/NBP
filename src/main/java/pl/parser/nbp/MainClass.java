package pl.parser.nbp;

import pl.parser.nbp.rate.BuyingRate;
import pl.parser.nbp.rate.SellingRate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        System.out.println("choose currency code and dates");
        System.out.println("example input EUR 2013-01-28 2013-01-31");

        String currency = reader.next();
        String date1 = reader.next();
        String date2 = reader.next();

        LocalDate first = LocalDate.parse(date1);
        LocalDate last = LocalDate.parse(date2);

        BuyingRate big = new BuyingRate();
        SellingRate sell = new SellingRate();

        BigDecimal avg = big.getAvgBuyingRate(first, last, currency);
        BigDecimal sd = sell.countStandardDeviation(first, last, currency);

        System.out.println("avg buying rate: " + avg);
        System.out.println("standard deviation: " + sd);
    }
}
