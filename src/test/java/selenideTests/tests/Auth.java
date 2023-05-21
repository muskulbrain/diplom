package selenideTests.tests;

import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.AuthPage;



public class Auth extends TestBase {

    @Test
    public void authByContract() {

        AuthPage.openBase();
        AuthPage.clickUserBar();
        AuthPage.clickLogin();
        AuthPage.loginByEmailOrContract();
        AuthPage.setContract();
        AuthPage.setPassword();
        AuthPage.clickLoginButton();
    }

    @Test
    public void authByEmail() {
        AuthPage.openBase();
        AuthPage.clickUserBar();
        AuthPage.clickLogin();
        AuthPage.loginByEmailOrContract();
        AuthPage.setEmail();
        AuthPage.setPassword();
        AuthPage.clickLoginButton();
    }

}
