package ru.netology.cartdeliveryorderchangedate.utility;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import lombok.experimental.UtilityClass;
import ru.netology.cartdeliveryorderchangedate.TestChangeDate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class DataGenerator {
    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static RegistrationInfo generateInfo(String locale) {
        Faker faker = new Faker(new Locale(locale));
        Name name = faker.name();
        return new RegistrationInfo(faker.address().city(),
                name.firstName().trim() + " " + name.lastName().trim(),
                faker.phoneNumber().phoneNumber());

    }

    @Value
    public static class RegistrationInfo {
        private final String city;
        private final String name;
        private final String phoneNumber;
//        private final String date;

    }

}

