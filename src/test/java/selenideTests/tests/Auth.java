package selenideTests.tests;

import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.AuthPage;


public class Auth extends TestBase {

    @Test
    public void authByContract() {
        AuthPage authPage = new AuthPage();

        authPage
                .openBase()
                .clickUserBar()
                .clickLogin()
                .loginByEmailOrContract()
                .setContract()
                .setPassword()
                .clickLoginButton();
    }

    @Test
    public void authByEmail() {
        AuthPage authPage = new AuthPage();

        authPage.openBase()
                .clickUserBar()
                .clickLogin()
                .loginByEmailOrContract()
                .setEmail()
                .setPassword()
                .clickLoginButton();
    }

}
