package selenideTests.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    @BeforeAll
    public static void beforeAllMethod() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://kz.siberianwellness.com/kz-ru/";
    }

    @Step("Открытие главной страницы магазина")
    public TestBase openBase() {
        open(baseUrl);
        sleep(1000);
        closeCookiesPopup();
        closeLocationPopup("Да, всё верно");
        return this;
    }

    @Step("Закрытие окна геолокации")
    public TestBase closeLocationPopup(String text) {
        if ($(".location-overlay__buttons-wrapper").is(visible)) {
            $(".location-overlay__button").shouldHave(text(text)).click();
        }
        return this;
    }

    @Step("Закрытие окна с cookies")
    public TestBase closeCookiesPopup() {
        $(".cookies-overlay__text").is(visible);
        $(".cookies-overlay__button").click();
        return this;
    }

    @Step("Подставляем токен")
    public TestBase setToken() {
        Cookie ck = new Cookie("token", Constants.TOKEN);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
        refresh();
        sleep(2000);
        return this;
    }


}
