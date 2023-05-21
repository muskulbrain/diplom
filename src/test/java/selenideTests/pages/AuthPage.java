package selenideTests.pages;

import io.qameta.allure.Step;
import selenideTests.common.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class AuthPage extends TestBase {

    @Step("Открытие главной страницы магазина")
    public static void openBase() {
        open(baseUrl);
        sleep(1000);
        closeCookiesPopup();
        closeLocationPopup("Да, всё верно");
    }

    @Step("Закрытие окна с cookies")
    public static void closeCookiesPopup() {
        $(".cookies-overlay__text").is(visible);
        $(".cookies-overlay__button").click();
    }

    @Step("Закрытие окна геолокации")
    public static void closeLocationPopup(String text) {
        if ($(".location-overlay__buttons-wrapper").is(visible)) {
            $(".location-overlay__button").shouldHave(text(text)).click();
        }
    }

    @Step("Клик по иконке неавторизованного пользователя")
    public static void clickUserBar() {
        $("[data-qa='HEADER_PROFILE']").click();
    }

    @Step("Клик на Вход")
    public static void clickLogin() {
        $("[data-qa='VLOGIN_REGISTRATION_BAR_LOGIN_LINK']").click();
    }

    @Step("Вход по почте/контракту")
    public static void loginByEmailOrContract() {
        sleep(2000);
        $("[data-qa='SIGN_IN_BY_CONTRACT_OR_EMAIL']").shouldBe(visible).click();
    }

    public static void setContract() {
        $$(".sw-input__value_placeholder").first().setValue("2599649622");
    }

    public static void setPassword() {
        $$(".sw-input__value_placeholder").get(1).setValue("76773");
    }

    public static void clickLoginButton() {
        $(".sign-in__button").click();
        sleep(1000);
        $("[data-qa='VUSERBAR_NAME']").shouldBe(visible);
    }

    public static void setEmail() {
        $$(".sw-input__value_placeholder").first().setValue("poydeciyde@gufum.com");
    }
}

