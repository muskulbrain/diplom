package selenideTests.pages;

import io.qameta.allure.Step;
import selenideTests.common.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage extends TestBase {

    @Step("Открытие главной страницы магазина")
    public AuthPage openBase() {
        open(baseUrl);
        sleep(1000);
        closeCookiesPopup();
        closeLocationPopup("Да, всё верно");
        return this;
    }

    @Step("Закрытие окна с cookies")
    public AuthPage closeCookiesPopup() {
        $(".cookies-overlay__text").is(visible);
        $(".cookies-overlay__button").click();
        return this;
    }

    @Step("Закрытие окна геолокации")
    public AuthPage closeLocationPopup(String text) {
        if ($(".location-overlay__buttons-wrapper").is(visible)) {
            $(".location-overlay__button").shouldHave(text(text)).click();
        }
        return this;
    }

    @Step("Клик по иконке неавторизованного пользователя")
    public AuthPage clickUserBar() {
        $("[data-qa='HEADER_PROFILE']").click();
        return this;
    }

    @Step("Клик на Вход")
    public AuthPage clickLogin() {
        $("[data-qa='VLOGIN_REGISTRATION_BAR_LOGIN_LINK']").click();
        return this;
    }

    @Step("Вход по почте/контракту")
    public AuthPage loginByEmailOrContract() {
        sleep(2000);
        $("[data-qa='SIGN_IN_BY_CONTRACT_OR_EMAIL']").shouldBe(visible).click();
        return this;
    }

    public AuthPage setContract() {
        $$(".sw-input__value_placeholder").first().setValue("2599649622");
        return this;
    }

    public AuthPage setPassword() {
        $$(".sw-input__value_placeholder").get(1).setValue("76773");
        return this;
    }

    public AuthPage clickLoginButton() {
        $(".sign-in__button").click();
        sleep(1000);
        $("[data-qa='VUSERBAR_NAME']").shouldBe(visible);
        return this;
    }

    public AuthPage setEmail() {
        $$(".sw-input__value_placeholder").first().setValue("poydeciyde@gufum.com");
        return this;
    }
}

