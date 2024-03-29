package selenideTests.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenideTests.pages.API_Step;
import selenideTests.pages.AuthPage;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class TestBase {

    public static String token = API_Step.returnToken();


    @BeforeAll
    public static void beforeAllMethod() {
        Configuration.baseUrl = System.getProperty("baseUrl", "https://kz.siberianwellness.com");
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.browserVersion = System.getProperty("browserVersion", "100.0");
        Configuration.remote = "https://user1:1234@" + System.getProperty("selenoidAddress", "selenoid.autotests.cloud/wd/hub");
        Configuration.pageLoadStrategy = "eager";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @AfterAll
    public static void tearDownAll() {
        WebDriverRunner.closeWebDriver();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
        //Attach.screenshotAs("Last screenshot");
        //Attach.pageSource();
        //Attach.browserConsoleLogs();
        //Attach.addVideo();
    }

    @BeforeEach
    public void clearCache() {
        clearBrowserCache();
        clearBrowserCookies();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Step("Открытие главной страницы магазина")
    public TestBase openBase() {
        open("https://kz.siberianwellness.com/kz-ru/");
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

    //Быстрая авторизация в оформлении заказа
    public static AuthPage getAuthPage(AuthPage authPage) {
        return authPage.
                chooseLoginFromContactDetails()
                .loginByEmailOrContract()
                .setUser()
                .clickLoginButton()
                .unionCarts();
    }

    @Step("Подставляем токен")
    public TestBase setToken(String token) {
        Cookie ck = new Cookie("token", token);
        WebDriverRunner.getWebDriver().manage().addCookie(ck);
        refresh();
        sleep(2000);
        return this;
    }

    protected void setValueForFieldWithName(String placeholder, String value) {
        $$(".sw-input__value_placeholder").filter(visible).find(name(placeholder)).scrollIntoView(false).click();
        WebElement focusedElement = getFocusedElement();
        focusedElement.sendKeys(value);
    }

    @Step("Клик по иконке пользователя")
    public TestBase clickUserBar() {
        $("[data-qa='HEADER_PROFILE']").click();
        return this;
    }

    private void loginByRest() {
        String token = TestBase.token;
        API_Step.authorize(token);
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
        refresh();
        sleep(5000);
    }

    private void wrongLoginByRest() {
        String token = TestBase.token;
        API_Step.wrongAuthorize(token);
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
        refresh();
        sleep(5000);
    }

    private void loginByRest(String token) {
        API_Step.authorize(token);
        WebDriverRunner.getWebDriver().manage().addCookie(new Cookie("token", token));
        refresh();
        sleep(5000);
    }

    @Step("Быстрая авторизация по rest")
    public TestBase wrongLoginUserByRest() {
        wrongLoginByRest();
        return this;
    }

    //Авторизация с генерацией токена внутри метода
    @Step("Быстрая авторизация по rest")
    public TestBase loginUserByRest() {
        loginByRest();
        $("[data-qa='VUSERBAR_NAME']").shouldBe(visible.because("Тест не смог авторизоваться через REST"), Duration.ofSeconds(5));
        return this;
    }

    //Авторизация без генерации
    @Step("Быстрая авторизация по rest")
    public TestBase loginUserByRest(String token) {
        loginByRest(token);
        $("[data-qa='VUSERBAR_NAME']").shouldBe(visible.because("Тест не смог авторизоваться через REST"), Duration.ofSeconds(5));
        return this;
    }

    @Step
    public TestBase addProductByRest() {
        API_Step.addProductAPI();
        refresh();
        return this;
    }

}
