package selenideTests.tests;

import org.junit.jupiter.api.Test;
import selenideTests.common.TestBase;
import selenideTests.pages.AuthPage;
import selenideTests.pages.UserPage;


public class UserProfile extends TestBase {

    //Добавление адреса доставки в ЛК
    @Test
    public void addNewAddress() {
        UserPage userPage = new UserPage();
        AuthPage authPage = new AuthPage();

        openBase();
        clickUserBar();

        authPage.clickLogin()
                .loginByEmailOrContract()
                .setContract()
                .setPassword()
                .clickLoginButton();

        clickUserBar();

        userPage
                .clickUserAddressMenu()
                .clickCreateNewAddress()
                .fillingAddressFields()
                .checkAddAddress()
                .deleteAddress()
                .checkDeleteAddress();
    }

    //Выход из аккаунта в ЛК
    @Test
    public void logoutFromUserMenu() {
        UserPage userPage = new UserPage();
        AuthPage authPage = new AuthPage();

        openBase();
        clickUserBar();

        authPage.clickLogin()
                .loginByEmailOrContract()
                .setContract()
                .setPassword()
                .clickLoginButton();

        clickUserBar();

        userPage
                .clickLogout();

    }
}
