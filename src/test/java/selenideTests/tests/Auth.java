package selenideTests.tests;

import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.AuthPage;


public class Auth extends TestBase {

    //Авторизация по номеру контракта
    @Test
    public void authByContract() {
        AuthPage authPage = new AuthPage();

        openBase();

        authPage.clickUserBar()
                .clickLogin()
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

        authPage.clickUserBar()
                .clickLogin()
                .loginByEmailOrContract()
                .setEmail()
                .setPassword()
                .clickLoginButton();
    }

}
