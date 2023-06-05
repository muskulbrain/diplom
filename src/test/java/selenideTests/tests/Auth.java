package selenideTests.tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.AuthPage;


@Feature("Авторизация")
@Tag("Auth")
@Tag("All")
public class Auth extends TestBase {

    //Авторизация по номеру контракта
    @Test
    public void authByContract() {
        AuthPage authPage = new AuthPage();

        openBase();
        clickUserBar();

        authPage.clickLogin()
                .loginByEmailOrContract()
                .setContract()
                .setPassword()
                .clickLoginButton();
    }

    //Авторизация по почте
    @Test
    public void authByEmail() {
        AuthPage authPage = new AuthPage();

        openBase();
        clickUserBar();

        authPage.clickLogin()
                .loginByEmailOrContract()
                .setEmail()
                .setPassword()
                .clickLoginButton();
    }

}


