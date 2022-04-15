package ru.netology.cartdeliveryorderchangedate.utility;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;
import ru.netology.cartdeliveryorderchangedate.TestChangeDate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class DataGenerator {
    private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("dd.MM.yyyy");

    public static RegistrationInfo generateInfo(String locale, int dayCount) {
        Faker faker = new Faker(new Locale(locale));
        Name name = faker.name();
        return new RegistrationInfo(faker.address().city(),
                name.firstName().trim() + " " + name.lastName().trim(),
                faker.phoneNumber().phoneNumber(),
                dateTimeFormatter.format(faker.date().between(Date.from(LocalDate.now().plusDays(3)
                        .atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()), Date.from(LocalDate.now().plusDays(dayCount)
                        .atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))));
    }

    public static class RegistrationInfo {
        private final String city;
        private final String name;
        private final String phoneNumber;
        private final String date;

        public RegistrationInfo(String city, String name, String phoneNumber, String date) {
            this.city = city;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.date = date;
        }

        public String getCity() {
            return city;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getDate() {
            return date;
        }
    }

}

