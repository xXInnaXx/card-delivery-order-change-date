package ru.netology.cartdeliveryorderchangedate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.cartdeliveryorderchangedate.utility.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestChangeDate {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @Test
    public void shouldSendForm() throws InterruptedException {
        DataGenerator.RegistrationInfo registrationInfo = DataGenerator.generateInfo("ru");
        $("[placeholder='Город']").setValue(registrationInfo.getCity());
        $("[name='name']").setValue(registrationInfo.getName());
        $("[name='phone']").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $((".checkbox__text")).click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        $(byXpath("//div[@class='notification__content']")).shouldBe(visible)
                .shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
    }
    @Test
    public void shouldSendFormWithOfferAnotherDate() throws InterruptedException {
        DataGenerator.RegistrationInfo registrationInfo = DataGenerator.generateInfo("ru");
        $("[placeholder='Город']").setValue(registrationInfo.getCity());
        $("[name='name']").setValue(registrationInfo.getName());
        $("[name='phone']").setValue(registrationInfo.getPhoneNumber());
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $((".checkbox__text")).click();
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        $(byXpath("//div[@class='notification__content']")).shouldBe(visible)
                .shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        $$("button").find(exactText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(5));
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(visible, Duration.ofSeconds(5));
        $$("button").find(exactText("Перепланировать")).click();
        $(withText("Успешно")).shouldBe(visible, Duration.ofSeconds(15));
        $(byXpath("//div[@class='notification__content']")).shouldBe(visible)
                .shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(5)))
                .shouldBe(visible);
    }
}
