package selenideTests.pages;

import io.qameta.allure.Step;
import selenideTests.common.TestBase;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static selenideTests.common.Constants.*;

public class AuthPage extends TestBase {


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
        $$(".sw-input__value_placeholder").first().setValue(CONTRACT);
        return this;
    }

    public AuthPage setPassword() {
        $$(".sw-input__value_placeholder").get(1).setValue(PASSWORD);
        return this;
    }

    public AuthPage clickLoginButton() {
        $(".sign-in__button").click();
        sleep(1000);
        $("[data-qa='VUSERBAR_NAME']").shouldBe(visible);
        return this;
    }

    public AuthPage setEmail() {
        $$(".sw-input__value_placeholder").first().setValue(EMAIL);
        return this;
    }

    @Step("Выбор Войти из контактных данных")
    public AuthPage chooseLoginFromContactDetails() {
        $("[data-qa='ORDER_CARD_TITLE_LOGIN']").click();
        sleep(1000);
        return this;
    }

    @Step("Ввод контракта и пароля при быстрой авторизации ")
    public AuthPage setUser() {
        setValueForFieldWithName("contractOrEmail", CONTRACT);
        setValueForFieldWithName("password", PASSWORD);
        return this;
    }

    @Step("Объединение корзин при авторизации")
    public AuthPage unionCarts() {
        sleep(2000);
        if ($(".merge-carts-modal").isDisplayed()) {
            $$(".merge-carts-modal__remove-button").first().click();
        }
        return this;
    }
}

